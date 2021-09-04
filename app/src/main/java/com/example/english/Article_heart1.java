package com.example.english;

import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Article_heart1 extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_article_heart1);
    }

}