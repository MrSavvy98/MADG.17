package com.example.workfromhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText edEmpID, edDelay, edWork;
    CalendarView date;
    Button btnSubmit;
    Work work;
    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edEmpID = findViewById(R.id.empID);
        edWork = findViewById(R.id.work);
        edDelay = findViewById(R.id.delay);
        date = findViewById(R.id.date);
        btnSubmit = findViewById(R.id.submit);

        work = new Work();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Work-Complete");

                if(TextUtils.isEmpty(edEmpID.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please enter ID",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(edWork.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please enter work",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(edDelay.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please enter reason for delay",Toast.LENGTH_SHORT).show();
                }
                else{
                    work.setEmployeeID(edEmpID.getText().toString().trim());
                    work.setWork(edWork.getText().toString().trim());
                    work.setDelay(edDelay.getText().toString().trim());
                    work.setDate(new Date(date.getDate()));

                    //insert in to database
                    dbRef.push().setValue(work);

                    //feedback
                    Toast.makeText(getApplicationContext(),"Data saved successfully",Toast.LENGTH_SHORT).show();

                    //move next page
                    Intent intent = new Intent(MainActivity.this, WorkViewActivity.class);
                    startActivity(intent);

                }
            }
        });


    }
}