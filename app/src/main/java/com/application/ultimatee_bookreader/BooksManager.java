package com.application.ultimatee_bookreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BooksManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_manager);


    }

    private void getIntent(View v){
        switch(v.getId()){
//            case R.id.imgbtnBiography : startActivity(new Intent(BooksManager.this,books_manager_view.class ));

        }

    }
}
