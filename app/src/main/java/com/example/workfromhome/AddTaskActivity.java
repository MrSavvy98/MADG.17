package com.example.workfromhome;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTaskActivity extends AppCompatActivity {

    EditText eid, date, task;
    Button assign;
    DatabaseReference dbRef;
    Tasks_model tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        eid = findViewById(R.id.tveid);
        date = findViewById(R.id.date);
        task = findViewById(R.id.task);
        assign = findViewById(R.id.assign);

        tasks = new Tasks_model();

        assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Add_Task");
                try {
                    if (TextUtils.isEmpty(eid.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Please enter ID", Toast.LENGTH_SHORT).show();
                    }
                    else if (TextUtils.isEmpty(date.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Please enter Date", Toast.LENGTH_SHORT).show();
                    }
                    else if (TextUtils.isEmpty(task.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Please enter task", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        tasks.setEid(eid.getText().toString().trim());
                        tasks.setDate(date.getText().toString().trim());
                        tasks.setTask(task.getText().toString().trim());

                        dbRef.child(eid.getText().toString()).setValue(tasks);

                        Toast.makeText(getApplicationContext(), "Successfully added", Toast.LENGTH_SHORT).show();
                        clearControls();
                        Intent intent11 = new Intent(AddTaskActivity.this, Home.class);
                        startActivity(intent11);
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "INVALID", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void clearControls() {
        eid.setText("");
        date.setText("");
        task.setText("");
    }

//    public void home(View view10) {
//
//    }

}