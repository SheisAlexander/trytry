package com.example.english;

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

public class Login extends AppCompatActivity {

    EditText loginuser, loginpassword;
    Button loginbutton,registerbutton;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginuser = findViewById(R.id.LoginPersonName);
        loginpassword = findViewById(R.id.LoginPassword);
        loginbutton = findViewById(R.id.Loginbutton);
        registerbutton = findViewById(R.id.registerbutton);

        auth = FirebaseAuth.getInstance();
        Registerbutton.setOnClickListener(new View.OnClickListener(){
            Intent i = new Intent(packageContext:Login.this, Signup.class)
            startActivity(i);
        })

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_text = loginuser.getText().toString();
                String pass_text = loginpassword.getText().toString();

                if(TextUtils.isEmpty(user_text) || TextUtils.isEmpty(pass_text)){
                    Toast.makeText(context:Login_Actitvity.this, text:"Please fill the Fields",Toast.LENGTH_SHORT).show();
                }else{
                    auth.signInWithEmailAndPassword(user_text, pass_text)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                                public void onComplete(@NonNull Task<AuthResult> task){
                                    if(task.isSuccessful()){
                                        Intent i = new Intent(packageContext: Login_Activity.this, MainActivity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(context:)
                                    }
                                }
                            }
                })
            }
        });

    }
}