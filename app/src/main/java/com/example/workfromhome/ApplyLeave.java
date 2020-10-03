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

//                rootNode = FirebaseDatabase.getInstance();
//                reference = rootNode.getReference("Leave");

                reference = FirebaseDatabase.getInstance().getReference().child("Add_Leave");

                try{

                    if(TextUtils.isEmpty(emp_id.getEditText().toString())){

                        Toast.makeText(getApplicationContext(), "Please Enter Employee ID", Toast.LENGTH_SHORT).show();
                    }
                    else {


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
                }
                catch (NumberFormatException e){

                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }


}