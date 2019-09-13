package com.application.ultimatee_bookreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class user_manager_view_acc__settings extends AppCompatActivity {

    Button button,save;
    EditText edit_name,edit_email,edit_number,edit_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager_view_acc__settings);

        edit_name = findViewById(R.id.text_edit_name);
        edit_email = findViewById(R.id.text_edit_email);
        edit_number = findViewById(R.id.text_edit_number);
        edit_address = findViewById(R.id.text_edit_address);
        save = findViewById(R.id.btn_save);

//button save
        button = (Button)findViewById(R.id.btn_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t = new Intent(user_manager_view_acc__settings.this,user_manager_view_acc__settings.class);
                startActivity(t);

            }
        });

        //button back


        button = findViewById(R.id.btn_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(user_manager_view_acc__settings.this,user_manager_view_acc.class);
                startActivity(a);

            }
        });




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Sign up");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()){
                            edit_name.setText(dataSnapshot.child("username").getValue().toString());
                            edit_email.setText(dataSnapshot.child("email").getValue().toString());
                            edit_number.setText(dataSnapshot.child("password").getValue().toString());
                            edit_address.setText(dataSnapshot.child("conPassword").getValue().toString());
                        }else{
                            Toast.makeText(getApplicationContext(),"No Source",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });






    }



    }
