package com.application.ultimatee_bookreader;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class books_manager_listView extends AppCompatActivity {


    public String url1;
    ListView lv;
    EditText searchField;
    FirebaseListAdapter adapter;
    Button btnDownload,btnBookmark;
    ImageButton btnSearch;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_manager_listview);

        try {

            btnSearch = findViewById(R.id.serach_btn);




            lv =  findViewById(R.id.listView);
            Query query=FirebaseDatabase.getInstance().getReference().child("Books");


            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    searchField = findViewById(R.id.search_field_txt);
                    String srch = searchField.getText().toString().trim();
                    if(srch.isEmpty()){
//                        query=FirebaseDatabase.getInstance().getReference().child("Books");
                        Toast.makeText(getApplicationContext(), "Empty", Toast.LENGTH_SHORT).show();
                    }
                    else{
                       // query= FirebaseDatabase.getInstance().getReference().child("Books").orderByChild("bookname").equalTo("1111");

                        Toast.makeText(getApplicationContext(), " Not Empty", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            FirebaseListOptions<Books> options = new FirebaseListOptions.Builder<Books>()
                    .setLayout(R.layout.books_manager_view) // books_manager_view contains one book details
                    .setQuery(query, Books.class)
                    .build();

            adapter = new FirebaseListAdapter(options) {
                @Override
                protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                    TextView bookname = v.findViewById(R.id.bookName);
                    TextView bookType = v.findViewById(R.id.bookType);
                    TextView details = v.findViewById(R.id.details);
                    btnDownload = v.findViewById((R.id.btnDownload));
                    btnBookmark = v.findViewById(R.id.btnAddBookmark);

                    Books books = (Books) model;
                    bookname.setText(books.getBookname());
                    bookType.setText(books.getBookType());
                    details.setText(books.getDetails());
                    final String bookName;

                    bookName = books.getBookname();
                    btnBookmark.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(books_manager_listView.this,books_manager_bookmarkAdd.class);
                            intent.putExtra("url",url1 );
                            intent.putExtra("bookName",bookName);
                            intent.putExtra("status", "AddLayout");
                            intent.putExtra("note"," " );
                            startActivity(intent);
                        }
                    });


                    StorageReference storageRef = FirebaseStorage.getInstance().getReference("Books/" + books.getBookname());
                    storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                    {
                        @Override
                        public void onSuccess(final Uri downloadUrl)
                        {
                            url1 = downloadUrl.toString();
                            btnDownload.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse( downloadUrl.toString()));
                                    startActivity(browserIntent);
                                }
                            });


                        }
                    });



                }
            };


            lv.setAdapter(adapter);

        }
        catch (Exception e){
            e.printStackTrace();
        }

}




    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
