package com.application.ultimatee_bookreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.application.ultimatee_bookreader.R;

public class user_manager_view_privacy extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
private Spinner spinner;
private Spinner spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager_view_privacy);


//Spinner 1
    Spinner sp = findViewById(R.id.view_acc_privacy_spinner);
    ArrayAdapter<CharSequence> ad = ArrayAdapter.createFromResource(this,R.array.privacy,android.R.layout.simple_spinner_item);
    ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    sp.setAdapter(ad);
    sp.setOnItemSelectedListener(this);


 //spinner 2
        //spinner 2

        Spinner sp2 = findViewById(R.id.view_acc_privacy_spinner2);
        ArrayAdapter<CharSequence> ad2 = ArrayAdapter.createFromResource(this,R.array.privacy,android.R.layout.simple_spinner_item);
        ad2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(ad);
        sp2.setOnItemSelectedListener(this);

  //spinner 3

        Spinner sp3 = findViewById(R.id.view_acc_privacy_spinner3);
        ArrayAdapter<CharSequence> ad3 = ArrayAdapter.createFromResource(this,R.array.privacy_option,android.R.layout.simple_spinner_item);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(ad3);
        sp3.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView , View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



}
