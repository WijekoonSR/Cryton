package com.application.ultimatee_bookreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    Button loginbtn, signupbtn;
    EditText username, password;

    DatabaseReference databaseReference, readRef, dbRef;

    User user;

    user_manager us;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signupbtn = findViewById(R.id.btn_sign_up);
        loginbtn = findViewById(R.id.btn_login);
        //password = findViewById(R.id.txt_password);
        username = findViewById(R.id.txt_name);

        username = (EditText) findViewById(R.id.txt_name);
        password = (EditText) findViewById(R.id.txtpw);
        loginbtn = findViewById(R.id.btn_login);


        databaseReference = FirebaseDatabase.getInstance().getReference("Sign up");

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = null;
                try {
                    pwd = (password.getText().toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                logIn(username.getText().toString(), pwd);
            }
        });
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intphto = new Intent(getApplicationContext(), user_manager.class);
                startActivity(intphto);
            }
        });
    }


//        loginbtn.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Intent i = new Intent(MainActivity.this, DashBoard.class);
//            startActivity(i);
//        }
//    });
//
//        signupbtn.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Intent i = new Intent(MainActivity.this,user_manager.class);
//            startActivity(i);
//        }
//    });


//        loginbtn.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View view) {
//
//                readRef = FirebaseDatabase.getInstance().getReference().child(String.valueOf(username));
//


//            }
//        });


    private void logIn(final String userName, final String password) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(userName).exists()) {
                    if (!userName.isEmpty()) {
                        User user = dataSnapshot.child(userName).getValue(User.class);// assign retieved values from user to User class

                        Toast.makeText(MainActivity.this, password + " " +user.getPassword() , Toast.LENGTH_LONG).show();

                        if (user.getPassword().equals(password)) {
                            Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_LONG).show();
                            Intent intphto = new Intent(getApplicationContext(), DashBoard.class);
                            startActivity(intphto);
                        } else {
                            Toast.makeText(MainActivity.this, "Password Incorrect", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "User is not register", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "User is not register", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
    }
}
