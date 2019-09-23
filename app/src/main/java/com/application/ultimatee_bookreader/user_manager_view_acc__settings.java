package com.application.ultimatee_bookreader;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class user_manager_view_acc__settings extends AppCompatActivity {

    Button save,back,edit;
    EditText name,email,password;
    User user;
    String userName;




        DatabaseReference Dbupdate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager_view_acc__settings);

        Intent i = getIntent();
        userName = i.getStringExtra("userName");
        String msg = "Welcome "+userName;
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();;




        save = findViewById(R.id.btn_save);
        back = findViewById(R.id.btn_back);
        edit = findViewById(R.id.btn_edit);
        name = findViewById(R.id.text_edit_name);
        email = findViewById(R.id.text_edit_email);
        password = findViewById(R.id.txt_edit_pass);




        DatabaseReference Dbupdate = FirebaseDatabase.getInstance().getReference("Sign up").child(userName);
        Dbupdate.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){
                    name.setText(dataSnapshot.child("userName").getValue().toString());
                    email.setText(dataSnapshot.child("email").getValue().toString());
                    password.setText(dataSnapshot.child("password").getValue().toString());

                }else{
                    Toast.makeText(getApplicationContext(),"No Source",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference("Sign up").child(userName);
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()){


                            try {

                                user.setUserName(name.getText().toString().trim());
                                user.setEmail(email.getText().toString().trim());
                                user.setPassword(password.getText().toString().trim());

                                DatabaseReference updateDB = FirebaseDatabase.getInstance().getReference("Sign up").child("q");
                                updateDB.setValue(user);

                                Toast.makeText(getApplicationContext(), "Data  updated successfully ", Toast.LENGTH_LONG).show();
                            }

                            catch (NumberFormatException e ){

                                Toast.makeText(getApplicationContext(),"Invalid contact Number ",Toast.LENGTH_LONG).show();

                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"No source  to update ",Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent bac = new Intent(getApplicationContext(),DashBoard.class);
                startActivity(bac);
            }
        });

    }


}




