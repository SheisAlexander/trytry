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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Signup extends AppCompatActivity {

    //Widgets;
    EditText email, password,username;
    Button SignupBtn;

    //Firebase Auth
    //auth = FirebaseAuth.getInstance();

    FirebaseAuth auth = FirebaseAuth.getInstance();
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Initializing Widgets;
        email     = findViewById(R.id.SignupEmailAddress);
        password  = findViewById(R.id.SignupPassword);
        username  = findViewById(R.id.SignupUserName);
        SignupBtn = findViewById(R.id.Signupbutton);

        SignupBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String username_text = username.getText().toString();
                String email_text = email.getText().toString();
                String password_text = password.getText().toString();
                if(TextUtils.isEmpty(username_text) || TextUtils.isEmpty(email_text) || TextUtils.isEmpty(password_text)){
                    Toast.makeText(Signup.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }else{
                    RegisterNow(username_text,email_text,password_text);
                }
            }
        })
    }

    private void RegisterNow(final String username, String email,String password){
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                    public void onComplete(@NonNull Task<AuthResult> task){

                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userid = firebaseUser.getUid();

                            Object path;
                            myRef = FirebaseDatabase.getInstance()
                                    .getReference("MyUsers")
                            .child(userid);

                            //HashMaps
                            HashMap<String, String> haspMap = new HashMap<>();
                            haspMap.put("id",userid);
                            haspMap.put("username",username);
                            haspMap.put("imageURL","default");


                            //Opening
                            myRef.setValue(haspMap).addOnCompleteListener(new OnCompleteListener<Void>(){
                                public void onComplete(@NonNull Task<Void> task){

                                    if(task.isSuccessful()){
                                        Intent i = new Intent(Signup.this, MainActivity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
                                        finish();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(Signup.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}