package com.application.ultimatee_bookreader.Avinash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.application.ultimatee_bookreader.R;

public class user_manager_view_acc__settings extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager_view_acc__settings);



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



    }



    }
