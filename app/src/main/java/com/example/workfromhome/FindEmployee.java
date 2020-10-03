package com.example.workfromhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FindEmployee extends AppCompatActivity {

    private static final String TAG = "FindEmployee";
    public static final String ID = "";

    EditText empID;
    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_employee);

        empID = findViewById(R.id.etEmpID);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String employeeID = empID.getText().toString().trim();
                Log.i(TAG, "onClick: empID "+employeeID);

                Intent intent = new Intent(FindEmployee.this, MainActivity.class);
                intent.putExtra(ID,employeeID);
                startActivity(intent);
            }
        });

    }
}