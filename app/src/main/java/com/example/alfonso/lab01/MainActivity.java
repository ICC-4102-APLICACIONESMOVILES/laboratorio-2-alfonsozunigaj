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
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    static final int LOG_IN_REQUEST = 1;
    static String EMAIL;
    static String PASSWORD;
    static SPManager spManager = new SPManager();
    static SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = getPreferences(Context.MODE_PRIVATE);
        mDrawerLayout = findViewById(R.id.drawer_layout);

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
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);

                        if(menuItem.getItemId() == R.id.nav_credentials) {
                            CredentialsFragment fragment = new CredentialsFragment();
                            fragment.setUpInfo(EMAIL, PASSWORD);
                            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                                    fragment).commit();
                        }

                        else if(menuItem.getItemId() == R.id.nav_log_out) {
                            sharedPref.edit().clear().commit();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivityForResult(intent, LOG_IN_REQUEST);
                        }

                        else if(menuItem.getItemId() == R.id.nav_form) {
                            FormFragment fragment = new FormFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                                    fragment).commit();
                        }

                        else if(menuItem.getItemId() == R.id.nav_resume) {
                            ResumeFragment fragment = new ResumeFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                                    fragment).commit();
                        }

                        else if(menuItem.getItemId() == R.id.nav_list) {
                            ListFragment fragment = new ListFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                                    fragment).commit();
                        }


                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        EMAIL = spManager.getEmail(this);
        PASSWORD = spManager.getPassword(this);
        if (EMAIL == null && PASSWORD == null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivityForResult(intent, LOG_IN_REQUEST);
        }

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOG_IN_REQUEST) {
            if (resultCode == RESULT_OK) {
                EMAIL = data.getStringExtra("email");
                PASSWORD = data.getStringExtra("password");
                spManager.addData(this, EMAIL, PASSWORD);
            }
        }
    }
}

