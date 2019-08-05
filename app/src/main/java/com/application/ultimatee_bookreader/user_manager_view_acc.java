package com.application.ultimatee_bookreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class user_manager_view_acc extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager_view_acc);

        textView = findViewById(R.id.view_account);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(user_manager_view_acc.this,user_manager_view_acc__settings.class);
                startActivity(i);

            }
        });










    }


}
