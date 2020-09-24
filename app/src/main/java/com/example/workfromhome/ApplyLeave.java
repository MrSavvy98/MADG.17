package com.example.workfromhome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ApplyLeave extends AppCompatActivity {

    TextInputLayout emp_id, dept, lType, lDuration, from, to, reason;
    Button btn_next;

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
        reason = findViewById(R.id.reason);
        btn_next = findViewById(R.id.next);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Leave");

                //get all the values

                String id = emp_id.getEditText().getText().toString();
                String depart = dept.getEditText().getText().toString();
                String type = lType.getEditText().getText().toString();
                String duration = lDuration.getEditText().getText().toString();
                String frm = from.getEditText().getText().toString();
                String to1 = to.getEditText().getText().toString();
                String reason1 = reason.getEditText().getText().toString();

                AddLeaveHelper hel = new AddLeaveHelper(id, depart, type, duration,frm, to1, reason1);

                reference.setValue(hel);
            }
        });

    }
}