package com.application.ultimatee_bookreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class DashBoard extends AppCompatActivity {

    ImageButton b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();

//        b1 = findViewById(R.id.imgbtnUser);
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(DashBoard.this,user_manager_view_acc.class);
//                startActivity(i);
//            }
//        });

        b1 = findViewById(R.id.imgbtnViewBooks);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashBoard.this,BooksManager.class);
                startActivity(i);
            }
        });



//        b1 = findViewById(R.id.imgbtnAddBook);
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(DashBoard.this,ViewBooks.class);
//                startActivity(i);
//            }
//        });

        b1 = findViewById(R.id.imgbtnRequestBooks);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashBoard.this,request_books_manager.class);
                startActivity(i);
            }
        });
        b1 = findViewById(R.id.imgbtnFeedBacks);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashBoard.this,feedback_manager.class);
                startActivity(i);
            }
        });

//        b1 = findViewById(R.id.imgbtnBookmarks);
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(DashBoard.this,ViewBooks.class);
//                startActivity(i);
//            }
//        });

//        b1 = findViewById(R.id.imgbtnPrivacy);
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(DashBoard.this,user_manager_view_privacy.class);
//                startActivity(i);
//            }
//        });
//
//        b1 = findViewById(R.id.imgbtnLogOut);
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(DashBoard.this,MainActivity.class);
//                startActivity(i);
//            }
//        });

        b1 = findViewById(R.id.imgbtnExit);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.exit(0);
            }
        });

    }

//    public void startActivity(View v){
//        switch (v.getId()){
//            case R.id.imgbtnBiography :
//                Intent i = new Intent(DashBoard.this,BooksManager.class);
//                startActivity(i);
//                break;
//            case R.id.imgbtnFantasy :
//                break;
//            case R.id.imgbtnHorror :
//                break;
//            case R.id.imgbtnMystery :
//                break;
//            case R.id.imgbtnRomance :
//                break;
//            case R.id.imgbtnScience :
//                break;
//            case R.id.imgbtnTextile :
//                break;
//            case R.id.imgbtnWestern :
//                break;
//        }

//    }
}
