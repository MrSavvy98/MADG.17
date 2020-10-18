package com.example.workfromhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Leave extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //variable declaration
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);

        Button b1 = (Button) findViewById(R.id.btn_add_leave);
        Button b2 = (Button) findViewById(R.id.btn_view_leave);
        Button b3 = (Button) findViewById(R.id.btn_edit_leave);
        Button b4 = (Button) findViewById(R.id.btn_delete_leave);

        //hooks

        drawerLayout = findViewById(R.id.drawer_layout1);
        navigationView = findViewById(R.id.nav_view1);
        toolbar = findViewById(R.id.toolbar1);

        //toolbar
        setSupportActionBar(toolbar);

        //navigation Drawer menu

        //Hide or show items

        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_login).setVisible(false);
        menu.findItem(R.id.nav_profile).setVisible(true);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //make the  menu icons  clickable

        navigationView.setNavigationItemSelectedListener(this);

        //default home from the navigation menu

        navigationView.setCheckedItem(R.id.nav_leave);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Leave.this, ApplyLeave.class);
                startActivity(i1);
            }
        });


    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(Leave.this, Home.class);
        startActivity(i);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.nav_logout:
                Intent login = new Intent(Leave.this, login.class);
                Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show();
                startActivity(login);
                break;

            case R.id.nav_viewTasks:
                Intent task = new Intent(Leave.this, WorkCompleteFormActivity.class);
                startActivity(task);
                break;

            case R.id.nav_attendance:
                Intent att = new Intent(Leave.this, AttendanceM.class);
                startActivity(att);
                break;

            case R.id.nav_home:
                Intent intent = new Intent(Leave.this, Home.class);
                startActivity(intent);
                break;

            case R.id.nav_leave:

                break;

            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}