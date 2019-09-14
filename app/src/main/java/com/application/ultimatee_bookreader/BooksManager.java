package com.application.ultimatee_bookreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class BooksManager extends AppCompatActivity {

    ImageButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_manager);

        button = findViewById(R.id.imgbtnBiography);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BooksManager.this, DashBoard.class ));
            }
        });
    }

    private void getIntent(View v){
        switch(v.getId()){
//            case R.id.imgbtnBiography : startActivity(new Intent(BooksManager.this,books_manager_view.class ));

        }

    }
}
