package com.application.ultimatee_bookreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashBoard extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        button = (Button)findViewById(R.id.btnBooks);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashBoard.this,BooksManager.class);
                startActivity(i);
                //hhh
            }
        });

        button = (Button)findViewById(R.id.btnAddFeedbacks);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashBoard.this,feedback_manager.class);
                startActivity(i);
                //hhh
            }
        });

        button = (Button)findViewById(R.id.btnViewfeedbacks);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashBoard.this,feedback_manager.class);
                startActivity(i);
                //hhh
            }
        });

        button = (Button)findViewById(R.id.btnUserAcc);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashBoard.this,user_manager_view_acc.class);
                startActivity(i);
                //hhh
            }
        });

        button = (Button)findViewById(R.id.btnReqBooks);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashBoard.this,request_books_manager.class);
                startActivity(i);
                //hhh
            }
        });

        button = (Button)findViewById(R.id.btnPrivacy);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashBoard.this,user_manager_view_privacy.class);
                startActivity(i);
                //hhh
            }
        });

        button = (Button)findViewById(R.id.btnSecurity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashBoard.this,user_manager_view_securtity.class);
                startActivity(i);
                //hhh
            }
        });

        button = (Button)findViewById(R.id.btnReqBooks);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashBoard.this,request_books_manager.class);
                startActivity(i);
                //hhh
            }
        });

        button = (Button)findViewById(R.id.btnViewfeedbacks);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashBoard.this,feedback_manager.class);
                startActivity(i);
                //hhh
            }
        });

    }
}
