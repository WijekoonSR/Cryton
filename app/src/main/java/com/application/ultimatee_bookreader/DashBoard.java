package com.application.ultimatee_bookreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class DashBoard extends AppCompatActivity {

    ImageButton button,view_acc;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        Intent i = getIntent();
        userName = i.getStringExtra("userName");
        String msg = "Welcome "+userName;
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();

       view_acc =findViewById(R.id.imgbtnUser);
        view_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sett = new Intent(getApplicationContext(),user_manager_view_acc.class);
                sett.putExtra("userName",userName);
                startActivity(sett);
            }
        });


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

        button = findViewById(R.id.imgbtnBookmarks);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashBoard.this, books_manager_bookmark_listview.class);
                startActivity(i);
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

//        button = (Button)findViewById(R.id.btnSecurity);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(DashBoard.this, user_manager_view_securtity.class);
//                startActivity(i);
//                //hhh
//            }
//        });



        button = findViewById(R.id.imgbtnLogOut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashBoard.this,user_manager_view_acc__settings.class);
                startActivity(i);
                //hhh
            }
        });

    }
}
