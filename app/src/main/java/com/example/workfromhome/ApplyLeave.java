package com.example.workfromhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ApplyLeave extends AppCompatActivity {

    //variable declaration
    TextInputLayout emp_id, dept, lType, lDuration, from, to, rsn;
    Button btn_next;
    public static String eid;

    FirebaseDatabase rootNode;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_leave);

        emp_id = findViewById(R.id.emp);
        dept = findViewById(R.id.dpt);
        lType = findViewById(R.id.type);
        lDuration = findViewById(R.id.duration);
        from = findViewById(R.id.from);
        to = findViewById(R.id.to);
        rsn = findViewById(R.id.reason);
        btn_next = findViewById(R.id.next);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                reference = FirebaseDatabase.getInstance().getReference().child("Add_Leave");

                try {

                    if ((validateEmpID() == false) && (validateDepart() == false)) {


                        return;
                    } else {


                        //get all the values

                        String id = emp_id.getEditText().getText().toString();
                        String depart = dept.getEditText().getText().toString();
                        String type = lType.getEditText().getText().toString();
                        String duration = lDuration.getEditText().getText().toString();
                        String frm = from.getEditText().getText().toString();
                        String to1 = to.getEditText().getText().toString();
                        String reason1 = rsn.getEditText().getText().toString();

                        AddLeaveHelper hel = new AddLeaveHelper(id, depart, type, duration, frm, to1, reason1);

                        reference.child(id).setValue(hel);

                        Toast.makeText(getApplicationContext(), "Leave Added", Toast.LENGTH_SHORT).show();
                        Intent next = new Intent(ApplyLeave.this, UpdateLeave.class);

                        next.putExtra(eid, id);
                        startActivity(next);
                    }
                } catch (NumberFormatException e) {

                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    private Boolean validateEmpID() {

        String value = emp_id.getEditText().getText().toString();


        if (value.isEmpty()) {

            emp_id.setError("Field cannot be empty");
            return false;
        } else if (value.length() >= 5) {

            emp_id.setError("Employee id too long, should be 4 numbers");
            return false;

        } else {

            emp_id.setError(null);
            emp_id.setErrorEnabled(false);
            return true;
        }


    }

    private Boolean validateDepart() {

        String value = dept.getEditText().getText().toString();


        if (value.isEmpty()) {

            dept.setError("Feild cannot be empty");
            return false;
        } else if (value.length() >= 15) {

            dept.setError("Employee id too long, should be 14 numbers");
            return false;

        } else {

            dept.setError(null);
            dept.setErrorEnabled(false);
            return true;
        }


    }
}