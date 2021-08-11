package com.example.english;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private TextView registerbutton,forgotPassword;
    private EditText editTextEmail,editTextPassword;
    private Button signIn;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registerbutton = findViewById(R.id.registerbutton);
        registerbutton.setOnClickListener(this);

        signIn = (Button) findViewById(R.id.Loginbutton);
        signIn.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.LoginEmail);
        editTextPassword = (EditText) findViewById(R.id.LoginPassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerbutton:
                startActivity(new Intent(this,Signup.class));
                break;

            case R.id.Loginbutton:
                userLogin();
                break;

            case R.id.forgotPassword:
                startActivity(new Intent(this,ForgotPassword.class));
                break;
        }
    }

    private void userLogin() {
        String email= editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("請輸入信箱!");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("請輸入正確的信箱格式!");
            editTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("請輸入密碼!");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length()<6){
            editTextPassword.setError("密碼至少6位數!");
            editTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.GONE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //跳到測驗那頁(尚未確定)
                    startActivity(new Intent(Login.this,YourLevel.class));

                }else{
                    Toast.makeText(Login.this, "登入失敗!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}