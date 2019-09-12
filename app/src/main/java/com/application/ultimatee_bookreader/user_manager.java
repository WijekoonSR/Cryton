package com.application.ultimatee_bookreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class user_manager extends AppCompatActivity {

    Button signupbtn,btnBack;
    EditText txtUN,txtEmail,txtPass,txtConPass;
    CheckBox terms;


    DatabaseReference dbRef;

    User user;




    databaseHelper helper = new databaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager);

        signupbtn = findViewById(R.id.btn_sign_up2);
//        txtID = findViewById(R.id.etID);
        txtUN = findViewById(R.id.txt_first_name);
        txtEmail = findViewById(R.id.txt_email);
        txtPass = findViewById(R.id.txt_pass);
        txtConPass = findViewById(R.id.txt_con_pass);
        btnBack = findViewById(R.id.btn_Back2);
        terms = (CheckBox) findViewById(R.id.check_box_terms);






        user = new User();





            signupbtn.setOnClickListener(new View.OnClickListener() {





                public void onClick(View v) {


                    //terms and condtions check

                    if (terms.isChecked() == true) {

                        dbRef = FirebaseDatabase.getInstance().getReference().child("User");

                        // user.setUserID("US0001");
                        user.setUserName(txtUN.getText().toString().trim());
                        user.setEmail(txtEmail.getText().toString().trim());
                        user.setPassword(txtPass.getText().toString().trim());
                        user.setConPassword(txtConPass.getText().toString().trim());




                            //check confirm pass and entered pass
                            if (user.getConPassword().equals(user.getPassword())) {

                                dbRef.push().setValue(user);

                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();


                                txtUN.setText(null);
                                txtEmail.setText(null);
                                txtPass.setText(null);
                                txtConPass.setText(null);

                            } else {

                                Toast.makeText(getApplicationContext(), "RE enter the password ! ", Toast.LENGTH_LONG).show();

                            }

                        } else {


                            Toast.makeText(getApplicationContext(), "Accept the terms and conditions ! ", Toast.LENGTH_SHORT).show();


                        }


                    }



            });







        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent back = new Intent(user_manager.this, MainActivity.class);
                startActivity(back);

            }
        });

    }



}
