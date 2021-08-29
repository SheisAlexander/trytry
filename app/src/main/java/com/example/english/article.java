package com.example.english;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.M)
public class article extends AppCompatActivity {

    TextView title, article, keyword1, keyword2, keyword3, keyword4, keyword5, keyword6, english_id;
    TextToSpeech textToSpeech;
    String english_ids;


    ImageButton b1;
    Button btn_easy, btn_other, btn_hard;
  //  ActionMode.Callback2 textSelectionActionModeCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[2];
                field[0] = "username";
                field[1] = "english_id";


                String[] data = new String[2];
                data[0] = "A";
                data[1] = english_ids;


                PutData putData = new PutData("http://163.13.201.116:8080/english/update2.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Log.i("TAG1", result);

                    }
                }
                //End Write and Read data with URL
            }
        });

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
        english_id = findViewById(R.id.english_id);

        Intent i = getIntent();
        String title0 = i.getStringExtra("title");
        title.setText(title0);

        String article0 = i.getStringExtra("article");
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

        english_ids = i.getStringExtra("english_id");
        english_id.setText(english_ids);

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

                        if (textToSpeech.isSpeaking()) {
                            textToSpeech.stop();
                        } else {

                            // if No error is found then only it will run
                            if (i != TextToSpeech.ERROR) {
                                // To Choose language of speech
                                textToSpeech.setLanguage(Locale.US);
                                textToSpeech.speak(article0, TextToSpeech.QUEUE_FLUSH, null);
                            }
                        }


                    }
                });


            }
        });


        btn_easy = findViewById(R.id.button_easy);
        btn_other = findViewById(R.id.button_other);
        btn_hard = findViewById(R.id.button_hard);

        btn_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(article.this,article2.class);
                Bundle bundle = new Bundle();
                bundle.putString("url","http://163.13.201.116:8080/english/select1.php");
                intent.putExtras(bundle);   // 記得put進去，不然資料不會帶過去哦
                startActivity(intent);
                finish();


            }
        });

        btn_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(article.this,article2.class);
                Bundle bundle = new Bundle();
                bundle.putString("url","http://163.13.201.116:8080/english/select2.php");
                intent.putExtras(bundle);   // 記得put進去，不然資料不會帶過去哦
                startActivity(intent);
                finish();

            }
        });
        btn_hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(article.this,article2.class);
                Bundle bundle = new Bundle();
                bundle.putString("url","http://163.13.201.116:8080/english/select3.php");
                intent.putExtras(bundle);   // 記得put進去，不然資料不會帶過去哦
                startActivity(intent);
                finish();

            }
        });


    }






}

