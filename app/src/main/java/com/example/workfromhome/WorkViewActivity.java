package com.example.workfromhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WorkViewActivity extends AppCompatActivity {

    private static final String TAG = "WorkViewActivity";
    private List<Work> works = new ArrayList<>();
    String employeeID;
    Employee employee;
    DatabaseReference readRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_view);

        Intent intent = getIntent();
        employeeID = intent.getStringExtra(MainActivity.ID);
        employeeID = "19111";

        final WorkAdapter workAdapter = new WorkAdapter(this);
        workAdapter.setOnItemClickListener(new WorkAdapter.OnItemClickListener() {
            @Override
            public void onItemDeleted(int position) {
                Log.i(TAG, "onItemDeleted: position = "+position);
                works.remove(position);
                workAdapter.notifyDataSetChanged();
                employee.setWorks(works);
                readRef.setValue(employee);
            }
        });
        workAdapter.setWorks(works);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(WorkViewActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);

        recyclerView.setAdapter(workAdapter);

        if(employeeID != null) {
            readRef = FirebaseDatabase.getInstance().getReference().child("Add_Task").child(employeeID);
            readRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    employee = dataSnapshot.getValue(Employee.class);
                    if (employee != null) {
                        works.clear();
                        works.addAll(employee.getWorks());
                        workAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }


}