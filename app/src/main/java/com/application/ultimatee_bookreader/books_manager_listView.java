package com.application.ultimatee_bookreader;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class books_manager_listView extends AppCompatActivity {

    ListView LV;
 //   Fire
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_manager_listview);

    }
}
