package com.example.workfromhome;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EmployeeRegistrationActivity extends AppCompatActivity {

    EditText emp_id, emp_fname, emp_lname, emp_dob, emp_adrs, emp_email, emp_phone, emp_department, emp_basicsalary, emp_payincentives,emp_workinghours;
    Button btnRegister;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_registration);
        initComponents();

        btnRegister.setOnClickListener(new View.OnClickListener() {


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
                dbRef = FirebaseDatabase.getInstance().getReference().child("Employees");

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

                dbRef.child(id).setValue(emp_data).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EmployeeRegistrationActivity.this,"Registration Success!",Toast.LENGTH_SHORT).show();
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

        emp_id = findViewById(R.id.emp_id);
        emp_fname = findViewById(R.id.emp_fname);
        emp_lname = findViewById(R.id.emp_lname);
        emp_dob = findViewById(R.id.emp_dob);
        emp_adrs = findViewById(R.id.emp_adrs);
        emp_email = findViewById(R.id.emp_email);
        emp_phone = findViewById(R.id.emp_phone);
        emp_department = findViewById(R.id.emp_department);
        emp_basicsalary = findViewById(R.id.emp_basicsalary);
        emp_payincentives = findViewById(R.id.emp_payincentives);
        emp_workinghours = findViewById(R.id.emp_workinghours);
        btnRegister = findViewById(R.id.btnRegister);

    }
}