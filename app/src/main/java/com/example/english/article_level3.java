package com.example.english;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class article_level3 extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> keywords1 = new ArrayList<>();
    ArrayList<String> keywords2 = new ArrayList<>();
    ArrayList<String> keywords3 = new ArrayList<>();
    ArrayList<String> keywords4 = new ArrayList<>();
    ArrayList<String> keywords5 = new ArrayList<>();
    ArrayList<String> keywords6 = new ArrayList<>();
    ArrayList<String> articles = new ArrayList<>();



    String result;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar

        setContentView(R.layout.activity_main);

        //Initialize and Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set home selected
        bottomNavigationView.setSelectedItemId(R.id.home);
        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        return true;
                    case R.id.analysis:
                        startActivity(new Intent(getApplicationContext(), Analysis.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.test:
                        startActivity(new Intent(getApplicationContext(), Test.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.dictionary:
                        startActivity(new Intent(getApplicationContext(), Dictionary.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }

        });


        //Imagebutton -setting button
        ImageButton mainButton = (ImageButton) findViewById(R.id.settingbutton);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(article_level3.this, Settings.class);
                startActivity(intent);
            }
        });


        //Recyclerview
        recyclerView = findViewById(R.id.recyclerView);
        //Recyclerview configuration
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        // 宣告按鈕的監聽器監聽按鈕是否被按下
        // 跟上次在 View 設定的方式並不一樣
        // 我只是覺得好像應該也教一下這種寫法
        Thread thread = new Thread(mutiThread);
        thread.start(); // 開始執行
    }
    /* ======================================== */

    // 建立一個執行緒執行的事件取得網路資料
    // Android 有規定，連線網際網路的動作都不能再主線程做執行
    // 畢竟如果使用者連上網路結果等太久整個系統流程就卡死了
    private Runnable mutiThread = new Runnable(){
        public void run()
        {
            try {
                URL url = new URL("http://163.13.201.88/english/level3.php");
                // 開始宣告 HTTP 連線需要的物件，這邊通常都是一綑的
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                // 建立 Google 比較挺的 HttpURLConnection 物件
                connection.setRequestMethod("POST");
                // 設定連線方式為 POST
                connection.setDoOutput(true); // 允許輸出
                connection.setDoInput(true); // 允許讀入
                connection.setUseCaches(false); // 不使用快取
                connection.connect(); // 開始連線

                int responseCode =
                        connection.getResponseCode();
                // 建立取得回應的物件
                if(responseCode ==
                        HttpURLConnection.HTTP_OK){
                    // 如果 HTTP 回傳狀態是 OK ，而不是 Error
                    InputStream inputStream =
                            connection.getInputStream();
                    // 取得輸入串流
                    BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
                    // 讀取輸入串流的資料
                    String box = ""; // 宣告存放用字串
                    String line = null; // 宣告讀取用的字串
                    while((line = bufReader.readLine()) != null) {
                        box += line + "\n";
                        // 每當讀取出一列，就加到存放字串後面
                    }
                    inputStream.close(); // 關閉輸入串流
                    result = box; // 把存放用字串放到全域變數
                }
                // 讀取輸入串流並存到字串的部分
                // 取得資料後想用不同的格式
                // 例如 Json 等等，都是在這一段做處理
                JSONArray userArray  = new JSONArray(result);
                for(int i=0;i<userArray.length();i++){

                    //creating a json object for fetching single data
                    JSONObject userDetail = userArray.getJSONObject(i);
                    //Fetching title & tag and storing them in arraylist
                    titles.add(userDetail.getString("title"));
                    articles.add(userDetail.getString("article"));

                    JSONArray keywordArray  = new JSONArray(userDetail.getString("keyword"));
                    keywords1.add(keywordArray.getString(0));
                    keywords2.add(keywordArray.getString(1));
                    keywords3.add(keywordArray.getString(2));
                    keywords4.add(keywordArray.getString(3));
                    keywords5.add(keywordArray.getString(4));
                    keywords6.add(keywordArray.getString(5));

                }


            } catch(Exception e) {
                result = e.toString(); // 如果出事，回傳錯誤訊息
            }

            // 當這個執行緒完全跑完後執行
            runOnUiThread(new Runnable() {
                public void run() {
                    CustomAdapter customAdapter = new CustomAdapter(titles,keywords1,keywords2,keywords3,keywords4,keywords5,keywords6,articles);
                    recyclerView.setAdapter(customAdapter); // 更改顯示文字
                }
            });
        }
    };




}