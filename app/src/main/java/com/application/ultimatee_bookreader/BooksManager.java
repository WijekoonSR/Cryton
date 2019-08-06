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

        Button  button;
        button = (Button)findViewById(R.id.btnReqBooks);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BooksManager.this, books_manager_display.class);
                startActivity(i);
                //hhh
            }
        });
    }
}
