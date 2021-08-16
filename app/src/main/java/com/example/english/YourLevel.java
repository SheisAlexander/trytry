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

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress == 0){
                    sorttextview.setText("The boy hears a sound. He looks up. He sees an airplane. The airplane is in the sky. It is a silver airplane. It has two wings. It has a tail. It has two jet engines. There is a pilot on the airplane. He flies the airplane. He lands the airplane.\n");
                }
                else if(progress == 1){
                    sorttextview.setText("The cat licks its paws. The cat licks its belly. The dog licks its paws. The dog licks its belly. The cat lies in the grass. The dog lies in the grass. The cat rolls onto its back. The dog rolls onto its back. The cat and dog do things together.\n");
                }
                else if(progress == 2){
                    sorttextview.setText("Subway is the world’s most successful sandwich restaurant franchise. It was started in 1965 by Fred DeLuca who wanted to make extra cash selling sandwiches to finance his dream of becoming a doctor. They started franchising the Subway name and the company achieved quite remarkable growth. Today, it has almost 35,000 restaurants in 92 countries. It is the world’s second-largest restaurant chain. The principles of DeLuca in 1965 are the same today and a reason for its continued success. DeLuca believed in the “importance of serving a well-made, high quality product, providing excellent customer service, keeping operating costs low and finding great locations”. \n");
                }
                else if(progress == 3){
                    sorttextview.setText("Lincoln vowed to end slavery during his campaign for president, while never speaking about how the country would keep its relationship with the southern states when slavery was ended. This angered many southerners who promised to rebel if Lincoln won. The war lasted four years, and about 620,000 soldiers died. In what is considered the turning point of the war, Lincoln issued his famous Emancipation Proclamation in January 1863. What it did was gain international favor from the world because of its ethical qualities. It put pressure on those countries doing business with the south to review their practices. It effectively put an end to the war. Lincoln was assassinated on April 15, 1865 by a southern sympathizer as he sat in a theater with his wife.\n");
                }
                else if(progress == 4){
                    sorttextview.setText("It was Einstein who first conceived of a nuclear fission weapon that he felt Germany was on the verge of discovery. He urged President Franklin D. Roosevelt to begin development of a similar weapon, but also felt it was best not to use it. His warning about the Nazis served the president well, and the Manhattan Project was born. This project was designed to develop a nuclear weapon in the United States in case Hitler unleashed his own weapon. Einstein was a life-long pacifist. He spoke of the purity of science, and how it should be used for the betterment of Humankind, and not for creating weapons of mass destruction. Because of his high IQ, Einstein's name has become synonymous with intelligence in the United States.\n");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sortbutton = (Button) findViewById(R.id.Sortbutton);
        sortbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YourLevel.this, MainActivity.class);
                startActivity(intent);

            }
        });



    }

    }
