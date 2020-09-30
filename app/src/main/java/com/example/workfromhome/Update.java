package com.example.workfromhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class Update extends AppCompatActivity {

    String upId;
    TextInputLayout mp_id, dept, lType, lDuration, from, to, reason;
    Button btnUpdate;
    AddLeaveHelper updateLeave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent =  getIntent();
        upId = intent.getStringExtra();
    }
}