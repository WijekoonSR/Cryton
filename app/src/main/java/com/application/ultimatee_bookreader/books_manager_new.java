package com.application.ultimatee_bookreader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
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

public class books_manager_new extends AppCompatActivity {

    Button addPdf,upload;
    EditText txtAuthor,txtbookName;
    TextView txtUploadedFname;
    Spinner booktype;
    Uri pdfUri;

    FirebaseStorage storage;
    FirebaseDatabase database;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_manager_new);

        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();

        //buttons
        addPdf = findViewById(R.id.btnAddPdf);
        upload = findViewById(R.id.btnUploadPDF);
        //inputs
        txtAuthor = findViewById(R.id.txtAuthor);
        txtbookName = findViewById(R.id.txtAuthor);
        booktype = findViewById(R.id.txtBooktype); // spinner
        txtUploadedFname = findViewById(R.id.lblSelectedFile);
        //prompts

        addPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(books_manager_new.this, Manifest.permission.READ_EXTERNAL_STORAGE )== PackageManager.PERMISSION_GRANTED){
                    selectPDF();

                }
                else
                    ActivityCompat.requestPermissions(books_manager_new.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9 );
            }

        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pdfUri != null){
                    uploadFile(pdfUri);
                }
                else
                    Toast.makeText(books_manager_new.this,"Please Upload Book Pdf File",Toast.LENGTH_SHORT).show();
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

        final String filename = txtbookName.getText().toString();
        String author = txtAuthor.getText().toString();
        String bookType = booktype.getSelectedItem().toString();
        StorageReference storageReference = storage.getReference(); // returns root path
        switch (bookType){
            case "biography" :
                storageReference.child("Books/biography").child(filename).putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String url = taskSnapshot.getUploadSessionUri().toString();
                        //store the url in real time databse
                        DatabaseReference reference = database.getReference();// return the path to reference
                        reference.child(filename).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(books_manager_new.this,"Book succesully Added",Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(books_manager_new.this,"Not Uploaded Succeszfully ",Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(books_manager_new.this,"Not Uploaded Succeszfully ",Toast.LENGTH_SHORT).show();

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        // track the  progress
                        int currentprgrs = (int)(100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                        progressDialog.setTitle(currentprgrs);
                    }
                });
                break;
            case "fanatasy" :
                storageReference.child("Books/fanatasy").child(filename).putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String url = taskSnapshot.getUploadSessionUri().toString();
                        //store the url in real time databse
                        DatabaseReference reference = database.getReference();// return the path to reference
                        reference.child(filename).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(books_manager_new.this,"Book succesully Added",Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(books_manager_new.this,"Not Uploaded Succeszfully ",Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(books_manager_new.this,"Not Uploaded Succeszfully ",Toast.LENGTH_SHORT).show();

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        // track the  progress
                        int currentprgrs = (int)(100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                        progressDialog.setTitle(currentprgrs);
                    }
                });

                break;
            case "horror" : storageReference.child("Books/horror").child(filename).putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    String url = taskSnapshot.getUploadSessionUri().toString();
                    //store the url in real time databse
                    DatabaseReference reference = database.getReference();// return the path to reference
                    reference.child(filename).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(books_manager_new.this,"Book succesully Added",Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText(books_manager_new.this,"Not Uploaded Succeszfully ",Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(books_manager_new.this,"Not Uploaded Succeszfully ",Toast.LENGTH_SHORT).show();

                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    // track the  progress
                    int currentprgrs = (int)(100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    progressDialog.setTitle(currentprgrs);
                }
            });
                break;
            case "mystery" :
                storageReference.child("Books/mystery").child(filename).putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String url = taskSnapshot.getUploadSessionUri().toString();
                        //store the url in real time databse
                        DatabaseReference reference = database.getReference();// return the path to reference
                        reference.child(filename).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(books_manager_new.this,"Book succesully Added",Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(books_manager_new.this,"Not Uploaded Succeszfully ",Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(books_manager_new.this,"Not Uploaded Succeszfully ",Toast.LENGTH_SHORT).show();

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        // track the  progress
                        int currentprgrs = (int)(100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                        progressDialog.setTitle(currentprgrs);
                    }
                });

                break;
            case "romance" :
                storageReference.child("Books/romance").child(filename).putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String url = taskSnapshot.getUploadSessionUri().toString();
                        //store the url in real time databse
                        DatabaseReference reference = database.getReference();// return the path to reference
                        reference.child(filename).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(books_manager_new.this,"Book succesully Added",Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(books_manager_new.this,"Not Uploaded Succeszfully ",Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(books_manager_new.this,"Not Uploaded Succeszfully ",Toast.LENGTH_SHORT).show();

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        // track the  progress
                        int currentprgrs = (int)(100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                        progressDialog.setTitle(currentprgrs);
                    }
                });
                break;
            case "science" :
                storageReference.child("Books/science").child(filename).putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String url = taskSnapshot.getUploadSessionUri().toString();
                        //store the url in real time databse
                        DatabaseReference reference = database.getReference();// return the path to reference
                        reference.child(filename).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(books_manager_new.this,"Book succesully Added",Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(books_manager_new.this,"Not Uploaded Succeszfully ",Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(books_manager_new.this,"Not Uploaded Succeszfully ",Toast.LENGTH_SHORT).show();

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        // track the  progress
                        int currentprgrs = (int)(100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                        progressDialog.setTitle(currentprgrs);
                    }
                });
                break;
            case "textile" :
                storageReference.child("Books/textile").child(filename).putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String url = taskSnapshot.getUploadSessionUri().toString();
                        //store the url in real time databse
                        DatabaseReference reference = database.getReference();// return the path to reference
                        reference.child(filename).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(books_manager_new.this,"Book succesully Added",Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(books_manager_new.this,"Not Uploaded Succeszfully ",Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(books_manager_new.this,"Not Uploaded Succeszfully ",Toast.LENGTH_SHORT).show();

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        // track the  progress
                        int currentprgrs = (int)(100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                        progressDialog.setTitle(currentprgrs);
                    }
                });
                break;
            case "western" :
                storageReference.child("Books/western").child(filename).putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String url = taskSnapshot.getUploadSessionUri().toString();
                        //store the url in real time databse
                        DatabaseReference reference = database.getReference();// return the path to reference
                        reference.child(filename).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(books_manager_new.this,"Book succesully Added",Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(books_manager_new.this,"Not Uploaded Succeszfully ",Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(books_manager_new.this,"Not Uploaded Succeszfully ",Toast.LENGTH_SHORT).show();

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        // track the  progress
                        int currentprgrs = (int)(100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                        progressDialog.setTitle(currentprgrs);
                    }
                });
                break;

        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
            selectPDF();
        }
        else
            Toast.makeText(books_manager_new.this,"Please Provide Permission",Toast.LENGTH_SHORT).show();
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
            txtUploadedFname.setText("File is selected : " + data.getData().getLastPathSegment());

        }
        else{
            Toast.makeText(books_manager_new.this,"Please Select a file",Toast.LENGTH_SHORT).show();
        }
    }
}
