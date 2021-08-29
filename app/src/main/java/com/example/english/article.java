package com.example.english;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.BackgroundColorSpan;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Locale;

public class article extends AppCompatActivity {

    TextView title, article, keyword1, keyword2, keyword3, keyword4, keyword5, keyword6, english_id;
    TextToSpeech textToSpeech;
    String english_ids;


    ImageButton b1;
    Button btn_easy, btn_other, btn_hard;


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



            }
        });

        btn_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        /* ======================================== */
        //螢光筆、儲存
        TextView article = findViewById(R.id.article01) ;
        article.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater menuInflater = mode.getMenuInflater();
                menuInflater.inflate(R.menu.selection_action_menu,menu);
                return true;//返回false则不会显示弹窗
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                MenuInflater menuInflater = mode.getMenuInflater();
                menu.clear();
                menuInflater.inflate(R.menu.selection_action_menu,menu);
                return true;//隱藏所有非本app的item
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                //根据item的ID处理点击事件
                int start = article.getSelectionStart();
                int end = article.getSelectionEnd();
                SpannableStringBuilder ssb = new SpannableStringBuilder(article.getText());
                switch (item.getItemId()){
                    case R.id.lookup:
                        Toast.makeText(article.this, "字典", Toast.LENGTH_SHORT).show();
                        mode.finish();//收起操作菜单
                        break;
                    case R.id.highlight:
                        Toast.makeText(article.this, "螢光筆", Toast.LENGTH_SHORT).show();
                        ssb.setSpan(new BackgroundColorSpan(Color.YELLOW),start,end,1);
                        article.setText(ssb);

                        mode.finish();
                        break;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
            }
        });

    }



}

