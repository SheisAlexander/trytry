package com.example.english;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Signup extends AppCompatActivity {

    EditText username, password,email;
    Button SignupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        email = findViewById(R.id.editTextUserName);
        SignupBtn = findViewById(R.id.Signupbutton);
    }
}