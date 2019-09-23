package com.application.ultimatee_bookreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.core.Context;

public class books_manager_search extends AppCompatActivity {

    private RecyclerView mPeopleRV;
    private FirebaseRecyclerAdapter<Books,BookViewHolder> mPeopleRVAdapter;

    private EditText mSearchField;
    private ImageButton mSearchbutton;
    private RecyclerView mResultList;
    private DatabaseReference mBookDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_manager_search);

        mBookDatabase = FirebaseDatabase.getInstance().getReference().child("Books");

        mSearchField = (EditText)findViewById(R.id.search_field_txt);
        mSearchbutton = (ImageButton) findViewById(R.id.serach_btn);

        mResultList = (RecyclerView)findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

        mSearchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseUserSearch();
            }
        });

    }

    private void firebaseUserSearch() {
        Query query = mBookDatabase.orderByKey();

        FirebaseRecyclerOptions bookOptions = new FirebaseRecyclerOptions.Builder<Books>()
                .setQuery(query, Books.class)
                .build();
        mPeopleRVAdapter = new FirebaseRecyclerAdapter<Books, BookViewHolder>(bookOptions) {
            @Override
            protected void onBindViewHolder(@NonNull BookViewHolder bookViewHolder, int i, @NonNull Books books) {
                bookViewHolder.setdetails(books.getBookname(),books.getAuthor(),books.getBookType(),books.getDetails(),books.getDownloadURL());
            }

            @NonNull
            @Override
            public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view =  LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.books_manager_view, parent, false);
                return new BookViewHolder(view);
            }
        };
        mResultList.setAdapter(mPeopleRVAdapter);


    }
        @Override
        public void onStart() {
            super.onStart();
            mPeopleRVAdapter.startListening();
        }

        @Override
        public void onStop() {
            super.onStop();
            mPeopleRVAdapter.stopListening();
        }
        public class BookViewHolder extends RecyclerView.ViewHolder{

            View v;
            public BookViewHolder(View itemView){
                super(itemView);
                v = itemView;
            }

        public void setdetails(String bName,String author, String bType, String bDetails,String downUrl){
            TextView bookname = v.findViewById(R.id.bookName);
            TextView bookAuthor = v.findViewById(R.id.Author);
            TextView bookType = v.findViewById(R.id.bookType);
            TextView details = v.findViewById(R.id.details);
            Button btnDownload = findViewById(R.id.btnDownload);
            Button btnBookmark = findViewById(R.id.btnAddBookmark);

            bookname.setText(bName);
            bookAuthor.setText(author);
            bookType.setText(bType);
            details.setText(bDetails);
            String url = downUrl;
        }



    }
}
