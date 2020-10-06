package com.example.workfromhome;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class  EditActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String ID = "";
    EditText edDelay, edWork;
    CalendarView date;
    Button btnSubmit;
    Work work;
    DatabaseReference dbRef;
    String employeeID;
    int position;

    Employee employee;
    Date selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        employeeID = intent.getStringExtra(FindEmployee.ID);
        position = intent.getIntExtra(FindEmployee.POSITION, -1);
        Log.i(TAG, "onCreate: employeeID "+employeeID+" position = "+position);

        // edEmpID = findViewById(R.id.empID);
        edWork = findViewById(R.id.work);
        edDelay = findViewById(R.id.delay);
        date = findViewById(R.id.date);
        date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int date) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, date);
                selectedDate = calendar.getTime();
            }
        });
        btnSubmit = findViewById(R.id.submit);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Add_Task").child(employeeID);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i(TAG, "onDataChange: ");
                if(dataSnapshot.hasChildren()){

                    employee = dataSnapshot.getValue(Employee.class);

                    Log.i(TAG, "onDataChange: Employee hasChildren");
                    if (employee != null) {
                        btnSubmit.setEnabled(true);

                        if(position >= 0) {
                            work = employee.getWorks().get(position);
                            edWork.setText(work.getWork());
                            edDelay.setText(work.getDelay());

                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(work.getDate());
                            date.setDate(calendar.getTimeInMillis());
                            selectedDate = work.getDate();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i(TAG, "onCancelled: ");
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                employeeID = edEmpID.getText().toString();
                Log.i(TAG, "onCreate: employeeID = "+employeeID);

                Log.i(TAG, "onClick: ");


//                if(TextUtils.isEmpty(edEmpID.getText().toString())){
//                    Toast.makeText(getApplicationContext(),"Please enter ID",Toast.LENGTH_SHORT).show();
//                }
               if(TextUtils.isEmpty(edWork.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please enter work",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(edDelay.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please enter reason for delay",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(work != null) {
                        work.setWork(edWork.getText().toString().trim());
                        work.setDelay(edDelay.getText().toString().trim());
                        work.setDate(selectedDate);

                        //insert in to database
                        dbRef.setValue(employee);

                        //feedback
                        Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();

                        //move next page
                        finish();
                    }

                }
            }
        });


    }
}