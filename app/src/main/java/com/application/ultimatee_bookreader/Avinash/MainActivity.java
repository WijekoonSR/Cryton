package com.application.ultimatee_bookreader.Avinash;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.application.ultimatee_bookreader.DashBoard;
import com.application.ultimatee_bookreader.R;


public class MainActivity extends AppCompatActivity {

    Button loginbtn , signupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginbtn = findViewById(R.id.btn_login);
        signupbtn = findViewById(R.id.btn_sign_up);

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




    }
}
