package com.example.english;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class YourLevel extends AppCompatActivity {

    private TextView sorttextview;
    private SeekBar seekbar;
    private Button sortbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_level);

        SeekBar seekBar;
        TextView textView;
        int seekValue;

        seekBar = (SeekBar)findViewById(R.id.SeekBar);
        sorttextview = (TextView)findViewById(R.id.SortTextView);
        sortbutton = (Button)findViewById(R.id.Sortbutton);
        sortbutton = (Button) findViewById(R.id.Sortbutton);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress == 0){
                    sorttextview.setText("The boy hears a sound. He looks up. He sees an airplane. The airplane is in the sky. It is a silver airplane. It has two wings. It has a tail. It has two jet engines. There is a pilot on the airplane. He flies the airplane. He lands the airplane.\n");
                    sortbutton.setText("Choose Level1");
                    sortbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(YourLevel.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }
                else if(progress == 1){
                    sorttextview.setText("Once, there was a Thief. He did not feel sorry for his bad deeds. He also believed that he was very smart. Often, he thought to himself, ‘I am the smartest of all. No one can trick me!’\\nOne day, the Thief was walking down the countryside, he saw a Boy. The Boy was sitting near a well. The Thief saw that the Boy was crying. \n");
                    sortbutton.setText("Choose Level2");
                    sortbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(YourLevel.this, article_level2.class);
                            startActivity(intent);
                        }
                    });
                }
                else if(progress == 2){
                    sorttextview.setText("Subway is the world’s most successful sandwich restaurant franchise. It was started in 1965 by Fred DeLuca who wanted to make extra cash selling sandwiches to finance his dream of becoming a doctor. They started franchising the Subway name and the company achieved quite remarkable growth. Today, it has almost 35,000 restaurants in 92 countries. It is the world’s second-largest restaurant chain.\n");
                    sortbutton.setText("Choose Level3");
                    sortbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(YourLevel.this, article_level3.class);
                            startActivity(intent);
                        }
                    });
                }
                else if(progress == 3){
                    sorttextview.setText("Lincoln vowed to end slavery during his campaign for president, while never speaking about how the country would keep its relationship with the southern states when slavery was ended. This angered many southerners who promised to rebel if Lincoln won. The war lasted four years, and about 620,000 soldiers died. In what is considered the turning point of the war, Lincoln issued his famous Emancipation Proclamation in January 1863. What it did was gain international favor from the world because of its ethical qualities. \n");
                    sortbutton.setText("Choose Level4");
                    sortbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(YourLevel.this, article_level4.class);
                            startActivity(intent);
                        }
                    });
                }
                else if(progress == 4){
                    sorttextview.setText("It was Einstein who first conceived of a nuclear fission weapon that he felt Germany was on the verge of discovery. He urged President Franklin D. Roosevelt to begin development of a similar weapon, but also felt it was best not to use it. His warning about the Nazis served the president well, and the Manhattan Project was born. This project was designed to develop a nuclear weapon in the United States in case Hitler unleashed his own weapon. Einstein was a life-long pacifist. He spoke of the purity of science, and how it should be used for the betterment of Humankind, and not for creating weapons of mass destruction. \n");
                    sortbutton.setText("Choose Level5");
                    sortbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(YourLevel.this, article_level5.class);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });





    }

    }
