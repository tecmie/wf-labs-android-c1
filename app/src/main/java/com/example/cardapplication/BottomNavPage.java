package com.example.cardapplication;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Fragment;
import android.os.Bundle;


import com.example.cardapplication.DatabaseModels.UserDao;

import com.example.cardapplication.Fragments.AnalyticsFragment;
import com.example.cardapplication.Fragments.HomeFragment;
import com.example.cardapplication.Fragments.PaymentsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;


import java.util.Objects;

public class BottomNavPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav_page);



        UserDao userDao = MainApp.getAppDatabase().userDao();
        userDao.getAll();



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        _replaceFragment(new HomeFragment());

        bottomNavigationView.setOnItemSelectedListener(item -> {

            int selectedItemId = item.getItemId();
            Fragment fragment;

            if (selectedItemId == R.id.menu_home) {
                fragment = new HomeFragment();
            } else if (selectedItemId == R.id.menu_analytics) {
                fragment = new AnalyticsFragment();
            } else {

                fragment = new PaymentsFragment();

            }
            return _replaceFragment(fragment);


        });
    }

    private boolean _replaceFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment, "homeTR").commit();
        return true;
    }


    private void testIonRequest() {

        JsonObject body = new JsonObject();

        body.addProperty("username", "my username");
        body.addProperty("password", "my password");


        Ion.with(this)
                .load("POST", "https://google.com")
                .setJsonObjectBody(body)
                .asJsonObject()
                .withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> result) {

                    }
                });
    }

}