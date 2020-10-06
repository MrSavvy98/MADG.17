package com.example.workfromhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    WorkAdapter workAdapter;
    Button btnAddWork;
    TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_view);

        btnAddWork = findViewById(R.id.btnAddWork);
        txtTitle = findViewById(R.id.txt_title);

        Intent intent = getIntent();
        employeeID = intent.getStringExtra(FindEmployee.ID);
        employeeID = "19111";
        Log.i(TAG, "onCreate: employeeID = "+employeeID);
        workAdapter = new WorkAdapter(this);
        workAdapter.setOnItemClickListener(new WorkAdapter.OnItemClickListener() {
            @Override
            public void onItemDeleted(int position) {
                Log.i(TAG, "onItemDeleted: position = "+position);
                works.remove(position);
                workAdapter.notifyDataSetChanged();
                employee.setWorks(works);
                readRef.setValue(employee);
            }

            @Override
            public void onItemEdited(int position){
                Log.i(TAG, "onItemEdited: position = "+position);
                Intent intent = new Intent(WorkViewActivity.this, EditActivity.class);
                intent.putExtra(FindEmployee.ID, employeeID);
                intent.putExtra(FindEmployee.POSITION, position);
                startActivity(intent);
            }
        });
        workAdapter.setWorks(works);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(WorkViewActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);

        recyclerView.setAdapter(workAdapter);

        btnAddWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkViewActivity.this, MainActivity.class);
                intent.putExtra(FindEmployee.ID, employeeID);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume: ");
        super.onResume();
        if(employeeID != null) {
            Log.i(TAG, "onResume: employeeID = "+employeeID);
            readRef = FirebaseDatabase.getInstance().getReference().child("Add_Task").child(employeeID);
            readRef.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Log.i(TAG, "onDataChange: ");
                    employee = dataSnapshot.getValue(Employee.class);
                    Log.i(TAG, "onDataChange: ");
        //            txtTitle.setText(employee.getEid());
                    if (employee != null) {
                        works.clear();
                        works.addAll(employee.getWorks());
                        Log.i(TAG, "onDataChange: works.size = "+works.size());
                        for(Work work:works){
                            Log.i(TAG, "onDataChange: "+work);
                        }
                        workAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.i(TAG, "onCancelled: ");
                }
            });
        }
    }
}