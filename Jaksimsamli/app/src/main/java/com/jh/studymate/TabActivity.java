package com.jh.studymate;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class TabActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String EXTRA_MESSAGE = "com.studymate.MESSAGE";

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer. addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FriendsFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_friends);
        }    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                break;

            case R.id.nav_friends:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FriendsFragment()).commit();
                break;

            case R.id.action_add_task:
                Intent todoIntent = new Intent(this, TodoActivity.class);
                startActivity(todoIntent);
                break;

            case R.id.board:
                Intent ListIntent = new Intent(this, ListActivity.class);
                startActivity(ListIntent);
                break;

            case R.id.nav_calendar:
                Intent calendarIntent = new Intent(this, CalendarActivity.class);
                startActivity(calendarIntent);
                break;

            case R.id.nav_alarm:
                Intent alarmIntent = new Intent(this, AlarmActivity.class);
                startActivity(alarmIntent);
                break;

            case R.id.nav_share:
                Intent CalendarSampleIntent = new Intent(this, CalendarSampleActivity.class);
                startActivity(CalendarSampleIntent);
                break;


            /*
            case R.id.nav_send:
                break;

            case R.id.nav_get:
                break;
            */

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
    }





}
