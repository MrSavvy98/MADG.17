package com.example.workfromhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class taskTypeActivity extends AppCompatActivity {


    Button btnAssignTask, btnWorkComplete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_type);

        btnAssignTask = findViewById(R.id.assignwork1);
        btnWorkComplete = findViewById(R.id.workComplete);

        btnAssignTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(taskTypeActivity.this, assign_task.class);
                startActivity(intent3);
            }
        });

        btnWorkComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(taskTypeActivity.this, FindEmployee.class);
                startActivity(intent);
            }
        });

    }

}