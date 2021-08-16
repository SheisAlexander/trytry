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
import android.widget.ImageButton;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    //1- Widgets
    RecyclerView recyclerView;
    //Arraylist for title and tag
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> keywords1 = new ArrayList<>();
    ArrayList<String> keywords2 = new ArrayList<>();
    ArrayList<String> keywords3 = new ArrayList<>();
    ArrayList<String> keywords4 = new ArrayList<>();
    ArrayList<String> keywords5 = new ArrayList<>();
    ArrayList<String> keywords6 = new ArrayList<>();
    ArrayList<String> articles = new ArrayList<>();









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
                switch(menuItem.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.analysis:
                        startActivity(new Intent(getApplicationContext(),Analysis.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.test:
                        startActivity(new Intent(getApplicationContext(),Test.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.dictionary:
                        startActivity(new Intent(getApplicationContext(),Dictionary.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }

        });
        //Imagebutton -setting button
        ImageButton mainButton = (ImageButton)findViewById(R.id.settingbutton);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
            }});



        //Recyclerview
        recyclerView = findViewById(R.id.recyclerView);
        //Recyclerview configuration
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        //getting json
        try{
            //getting json object from json file
            //JSONObject obj = new JSONObject(loadJSONfromAssets());
            //Fetch JsonArray name
            JSONArray userArray  = new JSONArray(loadJSONfromAssets());
            //Implementation of loop for getting users list data
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Calling the CustomAdapter to send the reference and data to adapter
        CustomAdapter customAdapter = new CustomAdapter(titles,keywords1,keywords2,keywords3,keywords4,keywords5,keywords6,articles);
        recyclerView.setAdapter(customAdapter);

    }
    // method for loading Json from assets
    private String loadJSONfromAssets() {
        String json = null;
        try{
            InputStream is = getAssets().open("all_article.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return json;

    }
}