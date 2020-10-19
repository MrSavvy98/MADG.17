package com.example.workfromhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class viewAssigned1 extends AppCompatActivity {

    Button check_assigned;
    public static  String employeeid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_assigned1);

        check_assigned = findViewById(R.id.check_assigned);
        check_assigned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewAssigned1.this, tasks.class);
                EditText editText1 = (EditText) findViewById(R.id.tveid);
                String empid = editText1.getText().toString();

                intent.putExtra( employeeid, empid);
                startActivity(intent);

            }
        });



    }

    public void tasks(View view4) {

        Intent intent4 = new Intent(this, tasks.class);
        startActivity(intent4);
    }

    public void home(View view100) {
        Intent intent11 = new Intent(this, Home.class);
        startActivity(intent11);
    }
}