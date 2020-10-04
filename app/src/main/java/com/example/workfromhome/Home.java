package com.example.workfromhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //variable declaration

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    CardView leave,profile,attendance,viewtasks,progress,aboutus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //hooks

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        //clickable cardView
        profile = findViewById(R.id.profileCard);
        attendance = findViewById(R.id.attendanceCard);
        leave = findViewById(R.id.leaveCard);
        viewtasks = findViewById(R.id.taskCard);
        progress = findViewById(R.id.progressCard);
        aboutus = findViewById(R.id.aboutusCard);

        //toolbar
        setSupportActionBar(toolbar);

        //navigation Drawer menu

        //Hide or show items

        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_login).setVisible(false);
        menu.findItem(R.id.nav_profile).setVisible(false);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //make the  menu icons  clickable

        navigationView.setNavigationItemSelectedListener(this);


        //default home from the navigation menu

        navigationView.setCheckedItem(R.id.nav_home);


        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, Leave.class);
                startActivity(i);
            }
        });

        viewtasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, MainActivity.class);
                startActivity(i);
            }
        });



        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(Home.this, .class);
//                startActivity(i);
            }
        });

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, Attendance.class);
                startActivity(i);
            }
        });



        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(Home.this, .class);
//                startActivity(i);
            }
        });

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(Home.this, .class);
//                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){

            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{

            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.nav_logout:
                Intent login = new Intent(Home.this, login.class );
                Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show();
                startActivity(login);
                break;

            case R.id.nav_viewTasks:
                Intent task = new Intent(Home.this, MainActivity.class );
                startActivity(task);
                break;

            case R.id.nav_home:
            break;

            case R.id.nav_leave:
                Intent intent = new Intent(Home.this, Leave.class );
                startActivity(intent);
                break;

            case  R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }


}