package com.example.english;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vishnusivadas.advanced_httpurlconnection.FetchData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Vocabulary_level extends AppCompatActivity {


    RecyclerView recyclerView;
    String result;
    ArrayList<String> vocabulary = new ArrayList<>();
    VocabAdapter vocabAdapter;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_vocabulary_heart1);

        title = findViewById(R.id.title);


        Handler handler1 = new Handler(Looper.getMainLooper());
        handler1.post(new Runnable() {
            @Override
            public void run() {
                Bundle bundle = getIntent().getExtras();
                String h = bundle.getString("url" );
                String t =bundle.getString("title" );
                title.setText(t);


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

                    for (int i = 0; i < userArray.length(); i++) {

                        //creating a json object for fetching single data
                        JSONObject userDetail = userArray.getJSONObject(i);
                        //Fetching title & tag and storing them in arraylist
                        vocabulary.add(userDetail.getString("word"));





                    }
                }catch (Exception e) {
                    result = e.toString();//如果出事，回傳錯誤訊息

                }
            }
        });

        //Recyclerview
        recyclerView = findViewById(R.id.recyclerView);
        //Recyclerview configuration


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        vocabAdapter = new VocabAdapter(vocabulary);
        recyclerView.setAdapter(vocabAdapter);



    }
}