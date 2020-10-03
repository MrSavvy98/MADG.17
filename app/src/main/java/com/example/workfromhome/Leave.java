package com.example.workfromhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Leave extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);

        Button b1 = (Button) findViewById(R.id.btn_add_leave);
        Button b2 = (Button) findViewById(R.id.btn_view_leave);
        Button b3 = (Button) findViewById(R.id.btn_edit_leave);
        Button b4 = (Button) findViewById(R.id.btn_delete_leave);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Leave.this, ApplyLeave.class);
                startActivity(i1);
            }
        });




    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(Leave.this, Home.class);
        startActivity(i);
    }
}