package com.example.workfromhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class assign_task extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_task);
    }

    public void addTasks(View view2) {
        Intent intent2 = new Intent(this, add_task.class);
        startActivity(intent2);
    }

    public void viewAssigned1(View view3) {
        Intent intent3 = new Intent(this, viewAssigned1.class);
        startActivity(intent3);
    }


    public void update(View view5) {
        Intent intent5 = new Intent(this, update.class);
        startActivity(intent5);
    }

    public void home(View view10) {
        Intent intent11 = new Intent(this, Home.class);
        startActivity(intent11);
    }

    public void delete(View view) {
        Intent del = new Intent(this, Home.class);
        startActivity(del);
    }
}