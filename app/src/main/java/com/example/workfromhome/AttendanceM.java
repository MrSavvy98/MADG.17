package com.example.workfromhome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AttendanceM extends AppCompatActivity {

    TextView in, out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_m);

        in.findViewById(R.id.intime);
        out.findViewById(R.id.Outtime);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        String current = simpleDateFormat.format(calendar.getTime());
        out.setText(current);

        try {

                Date date = simpleDateFormat.parse(current);
                calendar.setTime(date);
                calendar.add(calendar.MINUTE, 15);

                String result = simpleDateFormat.format(calendar.getTime());

                


        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
}