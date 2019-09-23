package com.application.ultimatee_bookreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class books_manager_bookmark_listview extends AppCompatActivity {

    FirebaseListAdapter adapter;
    ListView lv;
    Button btnViewBmark1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_manager_bookmark_listview);
        try {
//
            lv=findViewById(R.id.listView1);
            Query query=FirebaseDatabase.getInstance().getReference().child("Bookmarks");

            FirebaseListOptions<Bookmarks> options=new FirebaseListOptions.Builder<Bookmarks>()
                    .setLayout(R.layout.books_manager_bookmark_view) // books_manager_view contains one book details
                    .setQuery(query, Bookmarks.class)
                    .build();
            adapter=new FirebaseListAdapter(options) {
                @Override
                protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                    TextView bookname=v.findViewById(R.id.txtViewBName);
                    btnViewBmark1 = v.findViewById(R.id.btnViewBmark);

//
//

                    Bookmarks bookmarks=(Bookmarks) model;
                    final String bName=bookmarks.getBookName();
                    final String note=bookmarks.getNote();
                    final String dwnUrl=bookmarks.getDownUrl();
                    bookname.setText(bName);
//
                    btnViewBmark1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent intent=new Intent(books_manager_bookmark_listview.this, books_manager_bookmarkAdd.class);
                            intent.putExtra("status", "view");
                            intent.putExtra("url", dwnUrl);
                            intent.putExtra("bookName", bName);
                            intent.putExtra("note", note);
                            startActivity(intent);
                        }
                    });
//
//
                }
            };
//
//
            lv.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
//
    }
//
//
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