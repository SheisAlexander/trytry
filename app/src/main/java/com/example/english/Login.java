package com.example.english;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText loginusername, loginpassword;
    Button loginbutton,registerbutton;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginusername  = findViewById(R.id.LoginEmail);
        loginpassword  = findViewById(R.id.LoginPassword);
        loginbutton    = findViewById(R.id.Loginbutton);
        registerbutton = findViewById(R.id.Signupbutton);

        //Firebase Auth;
        auth = FirebaseAuth.getInstance();

        //Register Button;
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Signup.class);
                startActivity(i);
            }
        });


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_text = loginusername.getText().toString();
                String pass_text = loginpassword.getText().toString();

                //Checking if it is empty;
                if(TextUtils.isEmpty(user_text) || TextUtils.isEmpty(pass_text)){
                    Toast.makeText(Login.this, "Please fill the Fields", Toast.LENGTH_SHORT).show();
                }else{
                    auth.signInWithEmailAndPassword(user_text, pass_text)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                                public void onComplete(@NonNull Task<AuthResult> task){
                                    if(task.isSuccessful()){
                                        Intent i = new Intent(Login.this, MainActivity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(Login.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    }
            }
        });

    }
}