package com.application.ultimatee_bookreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class user_manager extends AppCompatActivity {

    Button signupbtn;
    EditText txtUN,txtEmail,txtPass;

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

        user = new User();

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbRef = FirebaseDatabase.getInstance().getReference().child("User");

               // user.setUserID("US0001");
                user.setUserName(txtUN.getText().toString().trim());
                user.setEmail(txtEmail.getText().toString().trim());
                user.setPassword(txtPass.getText().toString().trim());

                dbRef.push().setValue(user);
                //dbRef.child("us0001").setValue(user);
                Toast.makeText(getApplicationContext(), "Registered Successfully",Toast.LENGTH_LONG).show();

                txtUN.setText(null);
                txtPass.setText(null);
                txtEmail.setText(null);

            }
        });
    }



}
