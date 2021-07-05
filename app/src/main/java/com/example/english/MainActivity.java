package com.example.english;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    String test = "測試";
    int x = 1224;


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

        ImageButton mainButton = (ImageButton)findViewById(R.id.settingbutton);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
            }});

    }
}