package com.example.workfromhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TasksActivity extends AppCompatActivity {

    private static final String TAG = "TasksActivity";
    String employeeID;
    TextView eid, date, task;
    Button assign,deleteBtn;
    DatabaseReference dbRef;
    Tasks_model tasks;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        Intent intent = getIntent();
        employeeID = intent.getStringExtra(viewAssigned1.employeeid);

        eid = findViewById(R.id.tveid);
        date = findViewById(R.id.date);
        task = findViewById(R.id.tasks1);
        deleteBtn = findViewById(R.id.deleteBtn1);

        final DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Add_Task").child(employeeID);
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //   Log.i(TAG, "onDataChange: ");
                Log.i(TAG, "eid: "+dataSnapshot.child("eid").getValue());
                Log.i(TAG, "date: "+dataSnapshot.child("date").getValue());
                Log.i(TAG, "task: "+dataSnapshot.child("task").getValue());
                if(dataSnapshot.hasChildren()){
                    eid.setText(dataSnapshot.child("eid").getValue().toString());
                    Log.i(TAG, "eid: "+eid);

                    date.setText(dataSnapshot.child("date").getValue().toString());
                    task.setText(dataSnapshot.child("task").getValue().toString());
                }
                else
                    Toast.makeText(getApplicationContext(),"No Source to display", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view10) {
                //   DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Add_Task").child(employeeID);
                readRef.removeValue();
            }

        });
    }


    public void home(View view10) {
        Intent intent11 = new Intent(this, Home.class);
        startActivity(intent11);
    }


}