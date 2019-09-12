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

    Button loginbtn , signupbtn;
    EditText usernametxt, password;

    DatabaseReference dbRef;


    User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginbtn = findViewById(R.id.btn_login);
        signupbtn = findViewById(R.id.btn_sign_up);
        loginbtn = findViewById(R.id.btn_login);
        usernametxt = findViewById(R.id.txt_name);
        password = findViewById(R.id.txt_password);



        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DashBoard.class);
                startActivity(i);
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,user_manager.class);
                startActivity(i);
            }
        });


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("User");

                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChildren()){

                            usernametxt.setText(dataSnapshot.child("userName").getValue().toString());
                            password.setText(dataSnapshot.child("password").getValue().toString());


                        }else{
                            Toast.makeText(getApplicationContext(),"No Source",Toast.LENGTH_LONG).show();
                        }
                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });


    }
}
