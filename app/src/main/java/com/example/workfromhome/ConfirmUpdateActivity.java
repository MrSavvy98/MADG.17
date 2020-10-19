package com.example.workfromhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ConfirmUpdateActivity extends AppCompatActivity {

    private static final String TAG = "TasksActivity";
    String update_id;
    EditText u_eid, u_date, u_task;
    Button confirm_update;
    DatabaseReference dbRef1;
    Tasks_model tasksupdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_update);

        Intent intent = getIntent();
        update_id = intent.getStringExtra(UpdateTaskActivity.update_eid);


        final DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Add_Task").child(update_id);


        u_eid = findViewById(R.id.u_eid2);
        u_task = findViewById(R.id.u_task);
        u_date = findViewById(R.id.dupdate);
        confirm_update = findViewById(R.id.c_update);


        tasksupdate = new Tasks_model();

        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnap) {

                if (dataSnap.hasChildren()) {
                    tasksupdate.setEid(dataSnap.child("eid").getValue().toString());
                    tasksupdate.setDate(dataSnap.child("date").getValue().toString());
                    tasksupdate.setTask(dataSnap.child("task").getValue().toString());

                    u_eid.setText(tasksupdate.getEid());
                    u_task.setText(tasksupdate.getTask());
                    u_date.setText(tasksupdate.getDate());

                    Toast.makeText(getApplicationContext(), tasksupdate.getTask(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });





        confirm_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnap) {

                        if (dataSnap.hasChildren()) {
                            tasksupdate.setEid(u_eid.getText().toString().trim());
                            tasksupdate.setDate(u_date.getText().toString().trim());
                            tasksupdate.setTask(u_task.getText().toString().trim());

                            readRef.setValue(tasksupdate);

                            Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });

            }
        });
    }


    public void home(View view10) {
        Intent intent11 = new Intent(this, Home.class);
        startActivity(intent11);
    }

}