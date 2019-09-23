package com.application.ultimatee_bookreader;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class user_manager_view_acc__settings extends AppCompatActivity {

    Button save,back,edit,delete;
    EditText name,email,password;

    String userName;
    String un,pw,em;

User us;

        DatabaseReference Dbupdate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager_view_acc__settings);

        Intent i = getIntent();
        userName = i.getStringExtra("userName");
        String msg = "Welcome "+userName;
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();;




        save = findViewById(R.id.btn_save);
        back = findViewById(R.id.btn_back);
        edit = findViewById(R.id.btn_edit);
        name = findViewById(R.id.text_edit_name);
        email = findViewById(R.id.text_edit_email);
        password = findViewById(R.id.txt_edit_pass);
        delete = findViewById(R.id.btn_delete);



        DatabaseReference Dbupdate = FirebaseDatabase.getInstance().getReference("Sign up").child(userName);
        Dbupdate.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){

                    un = dataSnapshot.child("userName").getValue().toString();
                    pw = dataSnapshot.child("password").getValue().toString();
                    em = dataSnapshot.child("password").getValue().toString();

                    name.setText(dataSnapshot.child("userName").getValue().toString());
                    email.setText(dataSnapshot.child("email").getValue().toString());
                    password.setText(dataSnapshot.child("password").getValue().toString());

                }else{
                    Toast.makeText(getApplicationContext(),"No Source",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });






        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference upref = FirebaseDatabase.getInstance().getReference("Sign up");
                upref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(un)){

                        try {
                         us.setUserName(un);
                          us.setEmail(em);
                            us.setPassword(pw);

                            

                            DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("Sign up").child(un);
                            dbref.setValue(us);

                            Toast.makeText(user_manager_view_acc__settings.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                        }

                        catch (NumberFormatException e){

                            Toast.makeText(user_manager_view_acc__settings.this, "Successfully huttooossoo", Toast.LENGTH_SHORT).show();
                        }
                        }


                        else {
                            Toast.makeText(user_manager_view_acc__settings.this, "Successfully huttooooo", Toast.LENGTH_SHORT).show();

                       }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference("Sign up").child(userName);
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (!dataSnapshot.hasChild(userName)){
                            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Sign up").child(userName);
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



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent bac = new Intent(getApplicationContext(),DashBoard.class);
                startActivity(bac);
            }
        });

    }


}




