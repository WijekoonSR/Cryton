package com.application.ultimatee_bookreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class request_books_manager extends AppCompatActivity {

    Button submit;
    String bType,bAuthor,bName;
    EditText bookType, author, bookName,bookName1;
    DatabaseReference firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_books_manager);
        bookType = (EditText) findViewById(R.id.txtType);
        author = (EditText) findViewById(R.id.txtAuthor);
        bookName1 = (EditText) findViewById(R.id.txtbookName);
        submit = (Button)findViewById(R.id.btnSubmit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("RequestBook");
                RequestBook requestBook = new RequestBook();


                requestBook.setAuthor(author.getText().toString());
                requestBook.setBookType(bookType.getText().toString());
                //  requestBook.setBookName(bookName1.getText().toString());

                Toast.makeText(request_books_manager.this ,bookName1.getText().toString(), Toast.LENGTH_SHORT).show();
                firebaseDatabase.push().setValue(requestBook).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(request_books_manager.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                        clearFields();
                    }
                });
            }
        });


    }

    protected void clearFields(){
        bookType.setText(" ");
        bookName.setText(" ");
        author.setText(" ");
    }
}
