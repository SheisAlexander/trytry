package com.example.english;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;



public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> keywords1 = new ArrayList<>();
    ArrayList<String> keywords2 = new ArrayList<>();
    ArrayList<String> keywords3 = new ArrayList<>();
    ArrayList<String> keywords4 = new ArrayList<>();
    ArrayList<String> keywords5 = new ArrayList<>();
    ArrayList<String> keywords6 = new ArrayList<>();
    ArrayList<String> articles = new ArrayList<>();
    ArrayList<String> english_ids = new ArrayList<>();
    ArrayList<String> levels = new ArrayList<>();


    String result;
    String currentlevel ;
    TextView level;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        Log.d("MainActivity", "onCreate");
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
                    case R.id.dictionary:
                        startActivity(new Intent(getApplicationContext(), Dictionary.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), Settings.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }

        });

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                FetchData fetchData = new FetchData("http://163.13.201.116:8080/english/currentlevelview.php");
                if (fetchData.startFetch()) {
                    if (fetchData.onComplete()) {
                        result = fetchData.getResult();
                        //End ProgressBar (Set visibility to GONE)
                        Log.i("FetchData", result);
                    }
                }
                try {

                    JSONArray userArray = new JSONArray(result);

                    for (int i = 0; i < userArray.length(); i++) {

                        //creating a json object for fetching single data
                        JSONObject userDetail = userArray.getJSONObject(i);
                        //Fetching title & tag and storing them in arraylist
                        currentlevel=userDetail.getString("currentlevel");
                        Log.i("Currentlevel",currentlevel);
                        level.setText("Level"+currentlevel);


                    }
                } catch (Exception e) {
                    result = e.toString();//如果出事，回傳錯誤訊息

                }
            }
        });
        level = findViewById(R.id.level);


        //Recyclerview
        recyclerView = findViewById(R.id.recyclerView);
        //Recyclerview configuration


        Handler handler1 = new Handler();
        handler1.post(new Runnable() {
            @Override
            public void run() {


                String h = "http://163.13.201.116:8080/english/level"+currentlevel+".php";
                Log.i("URL",h);
                FetchData fetchData = new FetchData(h);
                if (fetchData.startFetch()) {
                    if (fetchData.onComplete()) {
                        result = fetchData.getResult();
                        //End ProgressBar (Set visibility to GONE)
                        Log.i("FetchData", result);
                    }
                }
                try {


                    JSONArray userArray  = new JSONArray(result);
                    shuffleJsonArray(userArray);


                    for(int i=0;i<userArray.length();i++){


                        //creating a json object for fetching single data
                        JSONObject userDetail = userArray.getJSONObject(i);
                        //Fetching title & tag and storing them in arraylist
                        titles.add(userDetail.getString("title"));
                        articles.add(userDetail.getString("article"));
                        english_ids.add(userDetail.getString("english_id"));
                        levels.add(userDetail.getString("level"));

                        JSONArray keywordArray  = new JSONArray(userDetail.getString("keyword"));
                        keywords1.add(keywordArray.getString(0));
                        keywords2.add(keywordArray.getString(1));
                        keywords3.add(keywordArray.getString(2));
                        keywords4.add(keywordArray.getString(3));
                        keywords5.add(keywordArray.getString(4));
                        keywords6.add(keywordArray.getString(5));

                        CustomAdapter customAdapter = new CustomAdapter(titles,keywords1,keywords2,keywords3,keywords4,keywords5,keywords6,articles,english_ids,levels);
                        recyclerView.setAdapter(customAdapter);



                    }
                }catch (Exception e) {
                    result = e.toString();//如果出事，回傳錯誤訊息

                }
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);









    }

    public static JSONArray shuffleJsonArray (JSONArray array) throws JSONException {
        // Implementing Fisher–Yates shuffle
        Random rnd = new Random();
        for (int i = array.length() - 1; i >= 0; i--)
        {
            int j = rnd.nextInt(i + 1);
            // Simple swap
            Object object = array.get(j);
            array.put(j, array.get(i));
            array.put(i, object);
        }
        return array;
    }
    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
        //if( textToSpeech != null ) textToSpeech.shutdown();

        Log.d("MainActivity", "onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").



        Log.d("MainActivity", "onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();


        // Another activity is taking focus (this activity is about to be "paused").
        Log.d("MainActivity", "onPause");
    }
    @Override
    protected void onStop() {

        super.onStop();


        // The activity is no longer visible (it is now "stopped")
        Log.d("MainActivity", "onStop");
    }
    @Override
    protected void onDestroy() {

        super.onDestroy();
        // The activity is about to be destroyed.
        Log.d("MainActivity", "onDestroy");
    }
    @Override
    public void onRestart()
    {
        super.onRestart();

        finish();
        startActivity(getIntent());
        Log.d("MainActivity", "onRestart");
    }








}
