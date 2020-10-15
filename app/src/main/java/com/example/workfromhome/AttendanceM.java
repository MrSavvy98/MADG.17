package com.example.workfromhome;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AttendanceM extends AppCompatActivity {

    EditText in,out;
    TextView result;
    Button b1;
    DatabaseReference ref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_m);

        in = findViewById(R.id.intime);
        out = findViewById(R.id.Outtime);
        b1 = findViewById(R.id.time);
        result = findViewById(R.id.result);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String time1 = in.getText().toString();
                String time2 = out.getText().toString();




                ref = FirebaseDatabase.getInstance().getReference().child("Attendance");

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

                Date d1 = null;
                Date d2 = null;

                try {

                    d1 = simpleDateFormat.parse(time1);
                    d2 = simpleDateFormat.parse(time2);

                    long dif = d2.getTime() - d1.getTime();
                    long difHrs = dif / (3600 * 1000) ;
                    long min = dif % (3600 * 1000);
                    long difMin = min / (60 * 1000);

                    //String hrs = Long.toString(difHrs);
                    result.setText("Hours:"+Long.toString(difHrs) + "  " + "Minutes:" + Long.toString(difMin));


                }
                catch (ParseException e){

                    e.printStackTrace();
                }


//                long difSec = dif / 1000;
//
//                long min = difSec % 3600;
//                long difMin = min / 60;
//
//


            }
        });






    }
}