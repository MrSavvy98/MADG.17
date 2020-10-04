package com.example.workfromhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.FocusFinder;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {


    TextInputLayout username, password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        Button b1 =(Button) findViewById(R.id.btn_signUp);
        Button b2 = (Button) findViewById(R.id.btn_login);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, SignUp.class);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent1 = new Intent(login.this, Home.class);
                startActivity(intent1);


                finish();
            }
        });

    }

    private boolean validateUsername(){

        String value = username.getEditText().getText().toString();
        String whitespace = "\\A\\w{4,20}\\Z";

        if(value.isEmpty()) {

            username.setError("Field cannot be empty");
            return false;
        }
        else if(value.matches(whitespace)){

            username.setError("Do not use whitespaces");
            return false;
        }
        else{

            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword(){

        String value = password.getEditText().getText().toString();

        if(value.isEmpty()) {

            password.setError("Field cannot be empty");
            return false;
        }
        else{

            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }

    }



    public void loginUser(View view){

        if(!validateUsername() | !validatePassword()){

            return;
        }
        else{

            isUser();
        }
    }

    public void isUser(){

        final String enUsername = username.getEditText().getText().toString().trim();
        final String enPassword = password.getEditText().getText().toString().trim();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");

        Query checkUser = ref.orderByChild("username").equalTo(enUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    username.setError(null);
                    username.setErrorEnabled(false);

                    String getPass = snapshot.child(enUsername).child("password").getValue(String.class);

                    if (getPass.equals(enPassword)){

                        username.setError(null);
                        username.setErrorEnabled(false);

                        Intent intent = new Intent(login.this, Home.class);
                        startActivity(intent);
                    }
                    else{
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                }
                else{

                    username.setError("Wrong Username");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}