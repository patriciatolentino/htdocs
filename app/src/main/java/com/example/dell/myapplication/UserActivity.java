package com.example.dell.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dell.myapplication.login.WelcomeFragment;

public class UserActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;

    WelcomeFragment.OnLogoutListener logoutListener;
    public interface  OnLogoutListener {
        public void logoutPerformed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);

        dl = (DrawerLayout)findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this,dl, R.string.Open, R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(abdt);
        abdt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nav_view=(NavigationView)findViewById(R.id.nav_view);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id =  item.getItemId();

                if(id == R.id.home) {
                    Toast.makeText(UserActivity.this,"Home",Toast.LENGTH_SHORT).show();
                } else if (id == R.id.optmsg) {
                    Intent view = new Intent (UserActivity.this, OptionalMessage.class);
                    startActivity(view);
                } else if (id == R.id.logout) {
                    logoutListener.logoutPerformed();
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);

    }

    public void onAttach(Context context) {


        Activity activity = (Activity) context;
        logoutListener = (WelcomeFragment.OnLogoutListener) activity;
    }
}
