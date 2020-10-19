package com.example.workfromhome;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class EmployeeActivity extends AppCompatActivity {

    EditText emp_id, emp_fname, emp_lname, emp_dob, emp_adrs, emp_email, emp_phone, emp_department, emp_basicsalary, emp_payincentives,emp_workinghours;
    Button btndelete,btnupdate;
    ImageButton btnsearch;
    DatabaseReference myRef;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        initComponents();

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(emp_id.getText().toString())){
                    Toast.makeText(EmployeeActivity.this,"Please Enter Employee ID!",Toast.LENGTH_SHORT).show();
                }else{
                    final String id = emp_id.getText().toString();

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    myRef = FirebaseDatabase.getInstance().getReference().child("Employees").child(id);


                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()){
                                HashMap<String,String> data = (HashMap<String,String>) dataSnapshot.getValue();

                                emp_id.setText(data.get("id"));
                                emp_fname.setText(data.get("fname"));
                                emp_lname.setText(data.get("lname"));
                                emp_dob.setText(data.get("dob"));
                                emp_adrs.setText(data.get("address"));
                                emp_email.setText(data.get("email"));
                                emp_phone.setText(data.get("phone"));
                                emp_department.setText(data.get("department"));
                                emp_basicsalary.setText(data.get("basic"));
                                emp_payincentives.setText(data.get("payinc"));
                                emp_workinghours.setText(data.get("hours"));



                                Toast.makeText(EmployeeActivity.this,"Employee Found!",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(EmployeeActivity.this,"No Employees!",Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            Toast.makeText(EmployeeActivity.this,"Search Error!",Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = emp_id.getText().toString();
                String fname = emp_fname.getText().toString();
                String lname = emp_lname.getText().toString();
                String dob = emp_dob.getText().toString();
                String address = emp_adrs.getText().toString();
                String email = emp_email.getText().toString();
                String phone = emp_phone.getText().toString();
                String department = emp_department.getText().toString();
                String basic = emp_basicsalary.getText().toString();
                String payinc = emp_payincentives.getText().toString();
                String hours = emp_workinghours.getText().toString();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                myRef = FirebaseDatabase.getInstance().getReference().child("Employees");

                HashMap<String,String> emp_data = new HashMap<>();
                emp_data.put("id",id);
                emp_data.put("fname",fname);
                emp_data.put("lname",lname);
                emp_data.put("dob",dob);
                emp_data.put("address",address);
                emp_data.put("email",email);
                emp_data.put("phone",phone);
                emp_data.put("department",department);
                emp_data.put("basic",basic);
                emp_data.put("payinc",payinc);
                emp_data.put("hours",hours);

                myRef.child(id).setValue(emp_data).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EmployeeActivity.this,"Update Success!",Toast.LENGTH_SHORT).show();
                        clearFields();
                    }
                });
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = emp_id.getText().toString();

               myRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EmployeeActivity.this,"Employee Deleted!",Toast.LENGTH_SHORT).show();
                        clearFields();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EmployeeActivity.this,"Employee Delete Failed!",Toast.LENGTH_SHORT).show();
                        clearFields();
                    }
                });
            }
        });

    }

    private void clearFields() {
        emp_id.setText(null);
        emp_fname.setText(null);
        emp_lname.setText(null);
        emp_dob.setText(null);
        emp_adrs.setText(null);
        emp_email.setText(null);
        emp_phone.setText(null);
        emp_department.setText(null);
        emp_basicsalary.setText(null);
        emp_payincentives.setText(null);
        emp_workinghours.setText(null);
    }

    private void initComponents() {

        emp_id = findViewById(R.id.emp_idx);
        emp_fname = findViewById(R.id.emp_fnamex);
        emp_lname = findViewById(R.id.emp_lnamex);
        emp_dob = findViewById(R.id.emp_dobx);
        emp_adrs = findViewById(R.id.emp_adrsx);
        emp_email = findViewById(R.id.emp_emailx);
        emp_phone = findViewById(R.id.emp_phonex);
        emp_department = findViewById(R.id.emp_departmentx);
        emp_basicsalary = findViewById(R.id.emp_basicsalaryx);
        emp_payincentives = findViewById(R.id.emp_payincentivesx);
        emp_workinghours = findViewById(R.id.emp_workinghoursx);
        btndelete = findViewById(R.id.btndelete);
        btnsearch = findViewById(R.id.search);
        btnupdate = findViewById(R.id.btnupdate);

    }

}