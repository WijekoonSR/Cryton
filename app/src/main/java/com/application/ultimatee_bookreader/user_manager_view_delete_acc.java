package com.application.ultimatee_bookreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.application.ultimatee_bookreader.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class user_manager_view_delete_acc extends AppCompatActivity {

    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager_view_delete_acc);

        delete = findViewById(R.id.btn_yes);

        Intent i = getIntent();
        final String userName = i.getStringExtra("userName");
        i.putExtra("userName",userName);
        String msg = "Welcome "+userName;
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();;


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference("Sign up").child("q");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("q")){
                           DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Sign up").child("q");
                            dbRef.removeValue();
                            Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"No Source to delete",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }


}
