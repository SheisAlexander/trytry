package com.example.english;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class YourLevel extends AppCompatActivity {

    private Button levelbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_level);

        SeekBar seekBar;
        TextView textView;
        int seekValue;

        seekBar = (SeekBar)findViewById(R.id.SeekBar);
        textView = (TextView)findViewById(R.id.SortTextView);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        levelbutton = (Button) findViewById(R.id.Sortbutton);
        levelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YourLevel.this, MainActivity.class);
                startActivity(intent);

            }
        });



    }

    }
