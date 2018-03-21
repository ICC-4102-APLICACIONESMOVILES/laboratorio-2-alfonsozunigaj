package com.example.alfonso.lab01;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    static final int LOG_IN_REQUEST = 1;
    static String EMAIL;
    static String PASSWORD;
    static SPManager spManager = new SPManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureLogoutButton();

        String current_email = spManager.getEmail(this);
        String current_password = spManager.getPassword(this);
        if (current_password == null && current_email == null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivityForResult(intent, LOG_IN_REQUEST);
        }
        else {
            TextView txt_email = (TextView) findViewById(R.id.text_email);
            txt_email.setText("E-mail: " + current_email);
            TextView txt_password = (TextView) findViewById(R.id.text_password);
            txt_password.setText("Password: " + current_password);
        }
    }

    private void configureLogoutButton() {
        final Button logoutButton = (Button) findViewById(R.id.button_logout);
        final SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //spManager.removeData(this);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.remove("email");
                editor.remove("password");
                editor.commit();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(intent, LOG_IN_REQUEST);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOG_IN_REQUEST) {
            if (resultCode == RESULT_OK) {
                EMAIL = data.getStringExtra("email");
                PASSWORD = data.getStringExtra("password");
                TextView ttxt = (TextView) findViewById(R.id.text_email);
                ttxt.setText("E-mail: " + EMAIL);
                TextView passw = (TextView) findViewById(R.id.text_password);
                passw.setText("Password: " + PASSWORD);
                spManager.addData(this, EMAIL, PASSWORD);
            }
        }
    }
}

