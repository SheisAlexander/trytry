package com.example.english;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;

public class article2 extends AppCompatActivity {

    TextView title, article, keyword1, keyword2, keyword3, keyword4, keyword5, keyword6, english_id;
    String result;
    String titles,articles,english_ids,keywords1s,keywords2s,keywords3s,keywords4s,keywords5s,keywords6s;
    ImageButton b1;

    Button btn_easy, btn_other, btn_hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Handler handler1 = new Handler(Looper.getMainLooper());
        handler1.post(new Runnable() {
            @Override
            public void run() {

                Bundle bundle = getIntent().getExtras();
                String h = bundle.getString("url" );
                FetchData fetchData = new FetchData(h);
                if (fetchData.startFetch()) {
                    if (fetchData.onComplete()) {
                        result = fetchData.getResult();
                        //End ProgressBar (Set visibility to GONE)
                        Log.i("FetchData", result);
                    }
                }
                try {


                    JSONArray userArray = new JSONArray(result);
                    Random r = new Random();
                    int a = r.nextInt(userArray.length())+0;

                    for (int i = a; i < a+1; i++) {

                        //creating a json object for fetching single data
                        JSONObject userDetail = userArray.getJSONObject(i);
                        //Fetching title & tag and storing them in arraylist
                        titles = userDetail.getString("title");
                        articles=userDetail.getString("article");
                        english_ids=userDetail.getString("english_id");

                        JSONArray keywordArray = new JSONArray(userDetail.getString("keyword"));
                        keywords1s =keywordArray.getString(0);
                        keywords2s =keywordArray.getString(1);
                        keywords3s =keywordArray.getString(2);
                        keywords4s =keywordArray.getString(3);
                        keywords5s =keywordArray.getString(4);
                        keywords6s =keywordArray.getString(5);

                        title.setText(titles);
                        article.setText(articles);
                        english_id.setText(english_ids);
                        keyword1.setText(keywords1s);
                        keyword2.setText(keywords2s);
                        keyword3.setText(keywords3s);
                        keyword4.setText(keywords4s);
                        keyword5.setText(keywords5s);
                        keyword6.setText(keywords6s);



                    }
                }catch (Exception e) {
                    result = e.toString(); // 如果出事，回傳錯誤訊息
                }
            }
        });

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

                    }
                }
                //End Write and Read data with URL
            }
        });


        title = findViewById(R.id.title02);
        article = findViewById(R.id.article01);
        keyword1 = findViewById(R.id.keyword01);
        keyword2 = findViewById(R.id.keyword02);
        keyword3 = findViewById(R.id.keyword03);
        keyword4 = findViewById(R.id.keyword04);
        keyword5 = findViewById(R.id.keyword05);
        keyword6 = findViewById(R.id.keyword06);
        english_id = findViewById(R.id.english_id);






        btn_easy = findViewById(R.id.button_easy);
        btn_other = findViewById(R.id.button_other);
        btn_hard = findViewById(R.id.button_hard);

        btn_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(article2.this,article2.class);
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
                intent.setClass(article2.this,article2.class);
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
                intent.setClass(article2.this,article2.class);
                Bundle bundle = new Bundle();
                bundle.putString("url","http://163.13.201.116:8080/english/select3.php");
                intent.putExtras(bundle);   // 記得put進去，不然資料不會帶過去哦
                startActivity(intent);
                finish();

            }
        });


    }
}