package com.example.english;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Analysis extends AppCompatActivity {

    CardView vocabheart,level1,level2,level3,level4,level5,level6,articleheart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        Log.d("Analysis", "onCreate");
        setContentView(R.layout.activity_analysis);

        //Initialize and Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set home selected
        bottomNavigationView.setSelectedItemId(R.id.analysis);
        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.analysis:
                        return true;

                    case R.id.dictionary:
                        startActivity(new Intent(getApplicationContext(),Dictionary.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(),Settings.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                }
                return false;
            }
        });

        vocabheart = findViewById(R.id.vocabulary);
        vocabheart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Analysis.this,Vocabulary_heart1.class);
                startActivity(intent);
            }
        });
        level1 = findViewById(R.id.level1);
        level2 = findViewById(R.id.level2);
        level3 = findViewById(R.id.level3);
        level4 = findViewById(R.id.level4);
        level5 = findViewById(R.id.level5);
        level6 = findViewById(R.id.level6);
        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Analysis.this,Vocabulary_level.class);
                Bundle bundle = new Bundle();
                bundle.putString("title","Level1");
                bundle.putString("url","http://163.13.201.116:8080/english/level1word.php");
                intent.putExtras(bundle);   // 記得put進去，不然資料不會帶過去哦
                startActivity(intent);

            }
        });
        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Analysis.this,Vocabulary_level.class);
                Bundle bundle = new Bundle();
                bundle.putString("title","Level2");
                bundle.putString("url","http://163.13.201.116:8080/english/level2word.php");
                intent.putExtras(bundle);   // 記得put進去，不然資料不會帶過去哦
                startActivity(intent);

            }
        });
        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Analysis.this,Vocabulary_level.class);
                Bundle bundle = new Bundle();
                bundle.putString("title","Level3");
                bundle.putString("url","http://163.13.201.116:8080/english/level3word.php");
                intent.putExtras(bundle);   // 記得put進去，不然資料不會帶過去哦
                startActivity(intent);

            }
        });
        level4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Analysis.this,Vocabulary_level.class);
                Bundle bundle = new Bundle();
                bundle.putString("title","Level4");
                bundle.putString("url","http://163.13.201.116:8080/english/level4word.php");
                intent.putExtras(bundle);   // 記得put進去，不然資料不會帶過去哦
                startActivity(intent);

            }
        });
        level5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Analysis.this,Vocabulary_level.class);
                Bundle bundle = new Bundle();
                bundle.putString("title","Level5");
                bundle.putString("url","http://163.13.201.116:8080/english/level5word.php");
                intent.putExtras(bundle);   // 記得put進去，不然資料不會帶過去哦
                startActivity(intent);

            }
        });
        level6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Analysis.this,Vocabulary_level.class);
                Bundle bundle = new Bundle();
                bundle.putString("title","Level");
                bundle.putString("url","http://163.13.201.116:8080/english/leve61word.php");
                intent.putExtras(bundle);   // 記得put進去，不然資料不會帶過去哦
                startActivity(intent);

            }
        });

        articleheart =findViewById(R.id.article_heart);
        articleheart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Analysis.this,Article_heart1.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
        Log.d("Analysis", "onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();

        // The activity has become visible (it is now "resumed").
        Log.d("Analysis", "onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
        Log.d("Analysis", "onPause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
        Log.d("Analysis", "onStop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
        Log.d("Analysis", "onDestroy");
    }
}