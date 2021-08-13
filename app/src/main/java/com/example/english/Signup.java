package com.example.english;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.FirebaseDatabase;


public class Signup extends AppCompatActivity implements View.OnClickListener{
    private TextView textViewSignup,registerUser;
    private EditText editTextFullname,editTextEmail,editTextPassword;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        textViewSignup = (TextView) findViewById(R.id.textViewSignup);
        textViewSignup.setOnClickListener(this);

        registerUser = (Button) findViewById(R.id.Signupbutton);
        registerUser.setOnClickListener(this);

        editTextEmail=(EditText) findViewById(R.id.SignupEmailAddress);
        editTextFullname=(EditText) findViewById(R.id.SignupUserName);
        editTextPassword=(EditText) findViewById(R.id.SignupPassword);

        progressBar=(ProgressBar) findViewById(R.id.progressBar);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textViewSignup:
                startActivity(new Intent(this,Login.class));
                break;
            case R.id.Signupbutton:
                registerUser();
                break;
        }

    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String fullName = editTextFullname.getText().toString().trim();

        if(fullName.isEmpty()){
            editTextFullname.setError("請填入名稱!");
            editTextFullname.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextEmail.setError("請輸入信箱!");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("請輸入正確格式的信箱!");
            editTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("請輸入密碼");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            editTextPassword.setError("密碼至少要有6位數!");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(fullName,email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>(){

                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Signup.this,"註冊成功",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }else {
                                        Toast.makeText(Signup.this,"註冊失敗，再試一次",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(Signup.this,"此信箱已註冊過!!",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });
    }
}