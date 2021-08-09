package com.example.english;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;

public class Signup extends AppCompatActivity {

    EditText username, password,email;
    Button SignupBtn;

    FirebaseAuth auth;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Initializing Widgets;
        password = findViewById(R.id.SignupPassword);
        email = findViewById(R.id.SignupUserName);
        username = findViewById(R.id.SignupUserName);
        SignupBtn = findViewById(R.id.Signupbutton);

        auth = FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String username_text = username.getText().toString();
                String email_text = email.getText().toString();
                String password_text = password.getText().toSting();
                if(TextUtils.isEmpty(username_text) || TextUtils.isEmpty(email_text) || TextUtils.isEmpty(Password_text)){
                    Toast.makeText(conext: RegisterActivity.this, text:"Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }else{
                    RegisterNow(username_text,email_text,password_text);
                }
            }
        })
    }

    private void RegisterNow(final String username, String email,String password){
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                    public void onComplete(@NonNull Taxk<AuthResult> task){
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userid = firebaseUser.getUid();

                            myRef = FirebaseDatabase.getInstanse()
                                    .getReference(path:"MyUsers")
                                    .child(userid);
                            //HashMaps
                            HashMap<String, String> haspMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("username",username);
                            hashMap.put("imageURL","default");

                            //Opening
                            myRef.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>(){
                                public void onComplete(@NonNUll Task<Void> task){
                                    if(task.isSuccessful()){
                                        Intent i = new Intent(packageContext: RegisterActivity.this, MainActivity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
                                        finish();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(context:RegisterActiviy.this, text:"Invalid Email or Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}