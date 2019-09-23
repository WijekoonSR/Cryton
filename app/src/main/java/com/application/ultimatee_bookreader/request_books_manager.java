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

    Button submit1;
    String bType,bAuthor,bName;
    EditText bookType1, author1,bookName1;
    DatabaseReference firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_books_manager);
        bookType1 = (EditText) findViewById(R.id.txtType1);
        author1 = (EditText) findViewById(R.id.txtAuthor1);
        bookName1 = (EditText) findViewById(R.id.txtBookName1);
        submit1 = (Button)findViewById(R.id.btnSubmit1);


        Toast.makeText(this, " " + bookName1.getText().toString(), Toast.LENGTH_SHORT).show();

        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bookType1.getText().toString().isEmpty() && author1.getText().toString().isEmpty() && bookName1.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), "Fields are Empty!", Toast.LENGTH_SHORT).show();
                } else if (bookType1.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), "Book Type Field is Empty!", Toast.LENGTH_SHORT).show();
                } else if (author1.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), "Author Field is Empty!", Toast.LENGTH_SHORT).show();
                } else if (bookName1.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), "Book Name Field is Empty!", Toast.LENGTH_SHORT).show();

                } else {


                    firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("RequestBook");
                    RequestBook requestBook = new RequestBook();


                    requestBook.setAuthor(author1.getText().toString());
                    requestBook.setBookType(bookType1.getText().toString());
                    requestBook.setBookName(bookName1.getText().toString());

                    Toast.makeText(request_books_manager.this, bookName1.getText().toString(), Toast.LENGTH_SHORT).show();
                    firebaseDatabase.push().setValue(requestBook).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(request_books_manager.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                            clearFields();

                        }

                    });
                }
            }
        });


    }

    protected void clearFields(){
        bookType1.setText(" ");
        bookName1.setText(" ");
        author1.setText(" ");
    }
}
