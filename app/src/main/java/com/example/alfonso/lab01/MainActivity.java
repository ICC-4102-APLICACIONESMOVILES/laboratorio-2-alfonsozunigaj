package com.example.alfonso.lab01;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    static DrawerLayout mDrawerLayout;

    static final int LOG_IN_REQUEST = 1;
    static String EMAIL;
    static String PASSWORD;
    static SPManager spManager = new SPManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        FormFragment fragment = null;

                        if(menuItem.getItemId() == R.id.nav_form) {
                            fragment = new FormFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                                    fragment).commit();
                        }
                        else if (menuItem.getItemId() == R.id.nav_option2) {
                            ListFragment list_fragment = new ListFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                                    list_fragment).commit();
                        }
                        else if (menuItem.getItemId() == R.id.nav_option3) {
                            ResumeFragment resume_fragment = new ResumeFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                                    resume_fragment).commit();
                        }
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );

        //configureLogoutButton();

        /*String current_email = spManager.getEmail(this);
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
        }*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*private void configureLogoutButton() {
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
    }*/

    /*@Override
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
    }*/
}

