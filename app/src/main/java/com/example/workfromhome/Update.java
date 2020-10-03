package com.example.workfromhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Update extends AppCompatActivity {

    String upId;
    TextInputLayout emp_id, dept, lType, lDuration, from, to, reason;
    Button btnUpdate,rmv;
    AddLeaveHelper updateLeave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent =  getIntent();
        upId = intent.getStringExtra(ApplyLeave.eid);

        final DatabaseReference read = FirebaseDatabase.getInstance().getReference().child("Add_Leave").child(upId);

        emp_id = findViewById(R.id.view_emp);
        dept = findViewById(R.id.view_dpt);
        lType = findViewById(R.id.view_type);
        lDuration = findViewById(R.id.view_duration);
        from = findViewById(R.id.view_from);
        to = findViewById(R.id.view_to);
        reason = findViewById(R.id.view_reason);
        btnUpdate = findViewById(R.id.update);
        rmv = findViewById(R.id.remove);

        updateLeave = new AddLeaveHelper();

        read.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){

                    updateLeave.setId(snapshot.child("id").getValue().toString());
                    updateLeave.setDepart(snapshot.child("depart").getValue().toString());
                    updateLeave.setLeaveType(snapshot.child("leaveType").getValue().toString());
                    updateLeave.setDuration(snapshot.child("duration").getValue().toString());
                    updateLeave.setFrm(snapshot.child("frm").getValue().toString());
                    updateLeave.setTo(snapshot.child("to").getValue().toString());
                    updateLeave.setReason(snapshot.child("reason").getValue().toString());

                    emp_id.getEditText().setText(updateLeave.getId());
                    dept.getEditText().setText(updateLeave.getDepart());
                    lType.getEditText().setText(updateLeave.getLeaveType());
                    lDuration.getEditText().setText(updateLeave.getDuration());
                    from.getEditText().setText(updateLeave.getFrm());
                    to.getEditText().setText(updateLeave.getTo());
                    reason.getEditText().setText(updateLeave.getReason());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                read.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(snapshot.hasChildren()){

                            updateLeave.setId(emp_id.getEditText().getText().toString().trim());
                            updateLeave.setDepart(dept.getEditText().getText().toString().trim());
                            updateLeave.setLeaveType(lType.getEditText().getText().toString().trim());
                            updateLeave.setDuration(lDuration.getEditText().getText().toString().trim());
                            updateLeave.setFrm(from.getEditText().getText().toString().trim());
                            updateLeave.setTo(to.getEditText().getText().toString().trim());
                            updateLeave.setReason(reason.getEditText().getText().toString().trim());

                            read.setValue(updateLeave);
                            Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });




      rmv.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              read.removeValue();
              Toast.makeText(getApplicationContext(),"Removing",Toast.LENGTH_SHORT).show();
              Intent ii = new Intent(Update.this, Leave.class);
              startActivity(ii);
          }
      });


    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Update.this, Leave.class);
        startActivity(i);
    }
}