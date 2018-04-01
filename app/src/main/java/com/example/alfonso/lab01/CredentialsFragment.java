package com.example.alfonso.lab01;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CredentialsFragment extends Fragment {

    private String email;
    private String password;


    public CredentialsFragment() {
        // Required empty public constructor
    }


    public void setUpInfo(String email, String password) {
        this.email = email;
        this.password = password;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //configureLogoutButton();
        View view =  inflater.inflate(R.layout.fragment_credentials, container, false);

        TextView email_txtv = (TextView) view.findViewById(R.id.text_email);
        email_txtv.setText(email);
        TextView password_txtv = (TextView) view.findViewById(R.id.text_password);
        password_txtv.setText(password);
        return view;
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
                Intent intent = new Intent(CredentialsFragment.this, LoginActivity.class);
                startActivityForResult(intent, LOG_IN_REQUEST);
            }
        });
    }*/

}
