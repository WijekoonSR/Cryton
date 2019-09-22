package com.application.ultimatee_bookreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class user_manager_view_acc extends AppCompatActivity {

    TextView accSettings;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager_view_acc);

        Intent i = getIntent();
        userName = i.getStringExtra("userName");
        String msg = "Welcome "+userName;
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();;

        accSettings = findViewById(R.id.view_account);


        accSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent se = new Intent(getApplicationContext(),user_manager_view_acc__settings.class);
                se.putExtra("userName",userName);
                startActivity(se);
            }
        });






    }


}
