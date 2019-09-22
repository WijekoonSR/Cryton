package com.application.ultimatee_bookreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class user_manager_view_acc extends AppCompatActivity {

    TextView accSettings,accDele;
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
        accDele = findViewById(R.id.view_dele_acc);


        accSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent se = new Intent(getApplicationContext(),user_manager_view_acc__settings.class);
                se.putExtra("userName",userName);
                startActivity(se);
            }
        });

       accDele.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               DatabaseReference delRef = FirebaseDatabase.getInstance().getReference("Sign up").child("no");
               delRef.addListenerForSingleValueEvent(new ValueEventListener() {

                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       if (dataSnapshot.hasChildren()){
                           DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Sign up").child("no");
                           dbRef.removeValue();
                           Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_LONG).show();

                           Intent i = new Intent(getApplicationContext(),MainActivity.class);
                           startActivity(i);
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
