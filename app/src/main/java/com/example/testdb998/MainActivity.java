package com.example.testdb998;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper db;
    TextInputEditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        db = new DataBaseHelper(this);
    }

    public void btnSignIn(View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        db.fillHobby();

        Integer userId =  db.checkUser(email, password);
        if(userId == -1){
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
            Handler h = new Handler();

            Intent i = new Intent(getApplicationContext(), HobbyActivity.class);
            i.putExtra("userId", userId);
            startActivity(i);

//            h.postDelayed(
//                    new Runnable() {
//                        @Override
//                        public void run() {
//                            Intent i = new Intent(getApplicationContext(), HobbyActivity.class);
//                            i.putExtra("userId", userId);
//                            startActivity(i);
//                        }
//                    }, 2000
//            );
        }
    }

    public void btignnUp(View view) {

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        User user = new User(email, password);
        long id = db.createUser(user);
        Toast.makeText(this, "New user created with ID = " + id, Toast.LENGTH_SHORT).show();
    }
}