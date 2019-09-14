package com.application.ultimatee_bookreader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.jar.Attributes;

public class books_manager_add extends AppCompatActivity {

    Button addPdf,upload;
    EditText author,bookName,bookDetails;
    TextView UploadedFname;
    Spinner booktype;
    Uri pdfUri;

    Books book;
    private StorageReference mStorageRef;
    FirebaseStorage storage;
    FirebaseDatabase database;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_manager_add);


        mStorageRef = FirebaseStorage.getInstance().getReference("Books");//****** Created File name in firebase

        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();

        //buttons
        addPdf = (Button) findViewById(R.id.btnAddPdf);
        upload = (Button) findViewById(R.id.btnUploadPDF);
        //inputs
        author = (EditText) findViewById(R.id.txtAuthor);
        bookName = (EditText) findViewById(R.id.txtAuthor);
        booktype = (Spinner) findViewById(R.id.txtBooktype); // spinner
        UploadedFname = (TextView) findViewById(R.id.lblSelectedFile);
        bookDetails = (EditText)findViewById(R.id.txtDetails);
        //prompts

        addPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(books_manager_add.this, Manifest.permission.READ_EXTERNAL_STORAGE )== PackageManager.PERMISSION_GRANTED){
                    selectPDF();

                }
                else
                    ActivityCompat.requestPermissions(books_manager_add.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9 );
            }

        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pdfUri != null){
                    uploadFile(pdfUri);
                }
                else
                    Toast.makeText(books_manager_add.this,"Please Upload Book Pdf File",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadFile(Uri pdfUri) {

        //prompt progress bar
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgress(progressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading File");
        progressDialog.setProgress(0);
        progressDialog.show();

        final String name = bookName.getText().toString()+".pdf";
        final Uri file = pdfUri;
        StorageReference riversRef = mStorageRef.child(name);

        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        book = new Books("","","","","");
                        book.setBookname(bookName.getText().toString());
                        book.setAuthor(author.getText().toString());
                        book.setDetails(bookDetails.getText().toString());
                        book.setBookType(booktype.getSelectedItem().toString());
                        // Get a URL to the uploaded content
                        String downloadUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                        book.setDownloadURL(downloadUrl);
                        //store the url in real time databse
                        DatabaseReference reference = database.getReference();// return the path to reference
                        reference.child("Books/"+ bookName.getText().toString() + System.currentTimeMillis()).setValue(book).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    progressDialog.dismiss();
                                    Toast.makeText(books_manager_add.this, "Book succesully Added", Toast.LENGTH_SHORT).show();
                                    clearFields();
                                } else{
                                    progressDialog.dismiss();
                                    Toast.makeText(books_manager_add.this, "Not Uploaded Succeszfully", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(books_manager_add.this, "Try Again", Toast.LENGTH_LONG).show();
                            }
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        Toast.makeText(books_manager_add.this,"Not Uploaded  ",Toast.LENGTH_SHORT).show();                      // ...
                    }
                });

    }//end  riversRef.putFile(file) method


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
            selectPDF();
        }
        else
            Toast.makeText(books_manager_add.this,"Please Provide Permission",Toast.LENGTH_SHORT).show();
    }

    private void selectPDF() {
        //select file using file manager
        //using intents
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,86);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //check  whether user has selected file or not
        if(requestCode == 86 && resultCode == RESULT_OK && data != null){
            pdfUri = data.getData();//return uri for selected
            UploadedFname.setText("File is selected : " + data.getData().getLastPathSegment());
            UploadedFname.setTextColor(getResources().getColor(R.color.Orange));

        }
        else{
            Toast.makeText(books_manager_add.this,"Please Select a file",Toast.LENGTH_SHORT).show();
        }
    }

    public void clearFields(){
        author.getText().clear();
        bookName.getText().clear();
        UploadedFname.setText(getResources().getText(R.string.fileSelected));

    }
}
