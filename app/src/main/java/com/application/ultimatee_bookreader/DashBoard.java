package com.application.ultimatee_bookreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;



public class DashBoard extends AppCompatActivity {

    ImageButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        button = findViewById(R.id.imgbtnViewBooks);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashBoard.this,BooksManager.class);
                startActivity(i);
                //hhh
            }
        });

        button = findViewById(R.id.imgbtnFeedBacks);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashBoard.this,feedback_manager.class);
                startActivity(i);
                //hhh
            }
        });

        button = findViewById(R.id.imgbtnAddBooks);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashBoard.this, books_manager_add.class);
                startActivity(i);
            }
        });


        button = findViewById(R.id.imgbtnUser);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashBoard.this, user_manager_view_acc.class);
                startActivity(i);
                //hhh
            }
        });

        button = findViewById(R.id.imgbtnRequestBooks);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashBoard.this,request_books_manager.class);
                startActivity(i);
                //hhh
            }
        });

        button = findViewById(R.id.imgbtnPrivacy);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashBoard.this, user_manager_view_privacy.class);
                startActivity(i);
                //hhh
            }
        });
//
//        button = (Button)findViewById(R.id.btnSecurity);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(DashBoard.this, user_manager_view_securtity.class);
//                startActivity(i);
//                //hhh
//            }
//        });
//

//
        button = findViewById(R.id.imgbtnLogOut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashBoard.this,MainActivity.class);
                startActivity(i);
                //hhh
            }
        });

    }
}
