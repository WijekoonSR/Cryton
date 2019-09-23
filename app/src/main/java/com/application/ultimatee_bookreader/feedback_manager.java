package com.application.ultimatee_bookreader;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import android.os.Bundle;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class feedback_manager extends AppCompatActivity {
    private RatingBar ratingBar;
    private Button btnSubmit;
    EditText nameField,emailField,feedbackField;
    DatabaseReference dbRef;
    Spinner feedbackSpinner;

    Feedback feedback;

    public void clearControls(){
        nameField.setText("");
        emailField.setText("");
        feedbackField.setText("");
        feedbackSpinner.setSelection(0);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_feedback_manager);
        addListenerOnRatingBar();
        addListenerOnButton();

        feedback = new Feedback();

        nameField = findViewById(R.id.Name);
        String name = nameField.getText().toString();

        emailField =  findViewById(R.id.Email);
        String email = emailField.getText().toString();

        feedbackField =  findViewById(R.id.Feedback);
        String feedback = feedbackField.getText().toString();

        feedbackSpinner =  findViewById(R.id.spinner);
        String feedbackType = feedbackSpinner.getSelectedItem().toString();



    }

    public void addListenerOnRatingBar() {

        ratingBar =  findViewById(R.id.ratingBar);


        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {



            }
        });
    }

    public void addListenerOnButton() {
        dbRef =FirebaseDatabase.getInstance().getReference().child("Feedback");

        ratingBar =  findViewById(R.id.ratingBar);
        btnSubmit =  findViewById(R.id.Submit);
        feedbackSpinner = findViewById(R.id.spinner);

        //if click on me, then display the current rating value.
        btnSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if(nameField.getText().toString().isEmpty() && emailField.getText().toString().isEmpty() && feedbackField.getText().toString().isEmpty()){
                    Toast.makeText(getBaseContext(),"Fields are Empty",Toast.LENGTH_SHORT).show();

                }else if(nameField.getText().toString().isEmpty()){
                    Toast.makeText(getBaseContext(),"Name Field is empty",Toast.LENGTH_SHORT).show();

                }else if(emailField.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), "Email Field is empty", Toast.LENGTH_SHORT).show();
                }else if(feedbackField.getText().toString().isEmpty()){
                    Toast.makeText(getBaseContext(), "Feedback Field is empty", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(feedbackSpinner.getSelectedItem().toString())){
                    Toast.makeText(getApplicationContext(),"Select Feedback Type",Toast.LENGTH_SHORT).show();
                }
                else {

                    feedback.setName(nameField.getText().toString().trim());
                    feedback.setEmail(emailField.getText().toString().trim());
                    feedback.setText(feedbackField.getText().toString().trim());
                    feedback.setType(feedbackSpinner.getSelectedItem().toString().trim());


                    dbRef.push().setValue(feedback);


                    Toast.makeText(feedback_manager.this, String.valueOf(ratingBar.getRating()), Toast.LENGTH_SHORT).show();
                    Toast.makeText(feedback_manager.this, "Thanks! Your Feedback is submitted.", Toast.LENGTH_LONG).show();
                    clearControls();

                }


            }

        });

    }
}
