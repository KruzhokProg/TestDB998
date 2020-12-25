package com.example.testdb998;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testdb998.Adapters.SpinnerAdapter;

public class HobbyActivity extends AppCompatActivity {

    Spinner spHobby;
    SpinnerAdapter spinnerAdapter;
    DataBaseHelper db;
    Integer hobbyId, userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobby);

        userId = getIntent().getExtras().getInt("userId");

        db = new DataBaseHelper(this);
        spHobby = findViewById(R.id.spHobby);

        spinnerAdapter = new SpinnerAdapter(db.getHobbies(), this);
        spHobby.setAdapter(spinnerAdapter);

        spHobby.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                hobbyId = db.getHobbyId(position);
                hobbyId = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        spHobby.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

    public void btnAddClick(View view) {
        db.addNewHobby(userId, hobbyId);
        Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
    }
}