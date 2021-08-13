package com.example.english;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

public class article extends AppCompatActivity {

    TextView title,article,keyword1,keyword2,keyword3,keyword4,keyword5,keyword6;
    TextToSpeech textToSpeech;
    ImageButton b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        //set back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        title = findViewById(R.id.title02);
        article = findViewById(R.id.article01);
        keyword1 = findViewById(R.id.keyword01);
        keyword2 = findViewById(R.id.keyword02);
        keyword3 = findViewById(R.id.keyword03);
        keyword4 = findViewById(R.id.keyword04);
        keyword5 = findViewById(R.id.keyword05);
        keyword6 = findViewById(R.id.keyword06);

        Intent i = getIntent();
        String title0 = i.getStringExtra("title");
        title.setText(title0);

        String article0 =i.getStringExtra("article") ;
        article.setText(article0);

        String keyword01 = i.getStringExtra("keyword1");
        keyword1.setText(keyword01);
        String keyword02 = i.getStringExtra("keyword2");
        keyword2.setText(keyword02);
        String keyword03 = i.getStringExtra("keyword3");
        keyword3.setText(keyword03);
        String keyword04 = i.getStringExtra("keyword4");
        keyword4.setText(keyword04);
        String keyword05 = i.getStringExtra("keyword5");
        keyword5.setText(keyword05);
        String keyword06 = i.getStringExtra("keyword6");
        keyword6.setText(keyword06);

        //text to speech

        b1 = findViewById(R.id.soundbutton);
        // create an object textToSpeech and adding features into it

        // Adding OnClickListener
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {

                        // if No error is found then only it will run
                        if(i!=TextToSpeech.ERROR){
                            // To Choose language of speech
                            textToSpeech.setLanguage(Locale.US);
                            textToSpeech.speak("article", TextToSpeech.QUEUE_FLUSH, null);


                        }


                    }
                });


            }
        });





    }
}