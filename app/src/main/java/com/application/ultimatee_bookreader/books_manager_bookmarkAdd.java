package com.application.ultimatee_bookreader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class books_manager_bookmarkAdd extends AppCompatActivity {

    Button submit,edit,delete;
    EditText bookName,note1;


    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_manager_bookmark_add);

        bookName = (EditText) findViewById(R.id.txtbookName);
        note1= (EditText) findViewById(R.id.txtNote);
        submit = (Button) findViewById(R.id.btnSubmit);
        edit = (Button) findViewById(R.id.btnUpdate);
        delete = (Button) findViewById(R.id.btnDelete);

        Intent i = getIntent();
        final String url = i.getStringExtra("url");
        final String book_Name = i.getStringExtra("bookName");
        final String status = i.getStringExtra("status");
        final String note = i.getStringExtra("note");


        edit.setVisibility(View.GONE);
        delete.setVisibility(View.GONE);

        Toast.makeText(this, note, Toast.LENGTH_SHORT).show();
        if(status.equals("view")) // check whether intent coming from bookmark listview or bookmark add layout
        {
            note1.setText(note);
            edit.setVisibility(View.VISIBLE);
            delete.setVisibility(View.VISIBLE);
            submit.setVisibility(View.GONE);
        }

        bookName.setText(book_Name);
        note1.setText(note);

        database = FirebaseDatabase.getInstance().getReference().child("Bookmarks");



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bookmarks  bookmarks = new Bookmarks();
                bookmarks.setBookName(book_Name);
                bookmarks.setDownUrl(url);
                final String eNote = note1.getText().toString();
                bookmarks.setNote(eNote);

                Toast.makeText(books_manager_bookmarkAdd.this, book_Name + " name: " + url + "url : " + note1.getText().toString() + "note : ", Toast.LENGTH_SHORT).show();
                
                database.child(book_Name).setValue(bookmarks).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(books_manager_bookmarkAdd.this, "Added Successfully", Toast.LENGTH_SHORT).show();

                        onBackPressed();
                    }
                });
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference upref = FirebaseDatabase.getInstance().getReference().child("Bookmarks");
                upref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(!dataSnapshot.hasChild(book_Name)){
                            Bookmarks bm = new Bookmarks();
                            bm.setBookName(book_Name);
                            bm.setNote(note1.getText().toString());
                            bm.setDownUrl(url);

                            DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Bookmarks").child(book_Name);
                            dbref.setValue(bm);
                            Toast.makeText(books_manager_bookmarkAdd.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
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
            public void onClick(View view) {

                DatabaseReference upref = FirebaseDatabase.getInstance().getReference().child("Bookmarks");
                upref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(!dataSnapshot.hasChild(book_Name)){
                            DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Bookmarks").child(book_Name);
                            dbref.removeValue();
                            Toast.makeText(books_manager_bookmarkAdd.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }
                        else{
                            Toast.makeText(books_manager_bookmarkAdd.this, "Can not Deleted", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });            }
        });

    }
}
