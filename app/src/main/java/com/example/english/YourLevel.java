package com.example.english;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class YourLevel extends AppCompatActivity {

    private TextView sorttextview,level;
    private SeekBar seekbar;
    private Button sortbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_your_level);



        seekbar = findViewById(R.id.SeekBar);
        sorttextview = findViewById(R.id.SortTextView);
        sortbutton =findViewById(R.id.Sortbutton);
        level =findViewById(R.id.level);

        seekbar.setMax(8);
        seekbar.setProgress(0);
        sorttextview.setText("The boy hears a sound. He looks up. He sees an airplane. The airplane is in the sky. It is a silver airplane. It has two wings. It has a tail. It has two jet engines. There is a pilot on the airplane. He flies the airplane. He lands the airplane.\n");
        level.setText("Level 1");
        sortbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] field = new String[2];
                        field[0] = "username";
                        field[1] = "currentlevel";


                        String[] data = new String[2];
                        data[0] = "A";
                        data[1] = "1";


                        PutData putData = new PutData("http://163.13.201.116:8080/english/currentlevel.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                //progressBar.setVisibility(View.GONE);
                                String result = putData.getResult();
                                if (result.equals("儲存成功")) {
                                    Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {

                                }
                            }
                        }
                        //End Write and Read data with URL
                    }
                });
                Intent intent = new Intent();
                intent.setClass(YourLevel.this,MainActivity.class);
                startActivity(intent);


            }
        });



        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                if(progress == 0){
                    sorttextview.setText("The boy hears a sound. He looks up. He sees an airplane. The airplane is in the sky. It is a silver airplane. It has two wings. It has a tail. It has two jet engines. There is a pilot on the airplane. He flies the airplane. He lands the airplane.\n");
                    level.setText("Level 1");
                    sortbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Handler handler = new Handler();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    String[] field = new String[2];
                                    field[0] = "username";
                                    field[1] = "currentlevel";


                                    String[] data = new String[2];
                                    data[0] = "A";
                                    data[1] = "1";


                                    PutData putData = new PutData("http://163.13.201.116:8080/english/currentlevel.php", "POST", field, data);
                                    if (putData.startPut()) {
                                        if (putData.onComplete()) {
                                            //progressBar.setVisibility(View.GONE);
                                            String result = putData.getResult();
                                            if (result.equals("儲存成功")) {
                                                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                                finish();
                                            } else {

                                            }
                                        }
                                    }
                                    //End Write and Read data with URL
                                }
                            });
                            Intent intent = new Intent();
                            intent.setClass(YourLevel.this,MainActivity.class);
                            startActivity(intent);

                            
                        }
                    });
                }
                else if(progress == 1){
                    sorttextview.setText("Once, there was a Thief. He did not feel sorry for his bad deeds. He also believed that he was very smart. Often, he thought to himself, ‘I am the smartest of all. No one can trick me!’One day, the Thief was walking down the countryside, he saw a Boy. The Boy was sitting near a well. The Thief saw that the Boy was crying. \n");
                    level.setText("Level 2");
                    sortbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Handler handler = new Handler();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    String[] field = new String[2];
                                    field[0] = "username";
                                    field[1] = "currentlevel";


                                    String[] data = new String[2];
                                    data[0] = "A";
                                    data[1] = "2";


                                    PutData putData = new PutData("http://163.13.201.116:8080/english/currentlevel.php", "POST", field, data);
                                    if (putData.startPut()) {
                                        if (putData.onComplete()) {
                                            //progressBar.setVisibility(View.GONE);
                                            String result = putData.getResult();
                                        }
                                    }
                                    //End Write and Read data with URL
                                }
                            });

                            Intent intent = new Intent();
                            intent.setClass(YourLevel.this,MainActivity.class);
                            startActivity(intent);

                        }
                    });
                }
                else if(progress == 2){
                    sorttextview.setText("Subway is the world’s most successful sandwich restaurant franchise. It was started in 1965 by Fred DeLuca who wanted to make extra cash selling sandwiches to finance his dream of becoming a doctor. They started franchising the Subway name and the company achieved quite remarkable growth. Today, it has almost 35,000 restaurants in 92 countries. It is the world’s second-largest restaurant chain.\n");
                    level.setText("Level 3");
                    sortbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Handler handler = new Handler();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    String[] field = new String[2];
                                    field[0] = "username";
                                    field[1] = "currentlevel";


                                    String[] data = new String[2];
                                    data[0] = "A";
                                    data[1] = "3";


                                    PutData putData = new PutData("http://163.13.201.116:8080/english/currentlevel.php", "POST", field, data);
                                    if (putData.startPut()) {
                                        if (putData.onComplete()) {
                                            //progressBar.setVisibility(View.GONE);
                                            String result = putData.getResult();
                                        }
                                    }
                                    //End Write and Read data with URL
                                }
                            });

                            Intent intent = new Intent();
                            intent.setClass(YourLevel.this,MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }
                else if(progress == 3){
                    sorttextview.setText("Lincoln vowed to end slavery during his campaign for president, while never speaking about how the country would keep its relationship with the southern states when slavery was ended. This angered many southerners who promised to rebel if Lincoln won. The war lasted four years, and about 620,000 soldiers died. In what is considered the turning point of the war, Lincoln issued his famous Emancipation Proclamation in January 1863. What it did was gain international favor from the world because of its ethical qualities. \n");
                    level.setText("Level 4");
                    sortbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Handler handler = new Handler();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    String[] field = new String[2];
                                    field[0] = "username";
                                    field[1] = "currentlevel";


                                    String[] data = new String[2];
                                    data[0] = "A";
                                    data[1] = "4";


                                    PutData putData = new PutData("http://163.13.201.116:8080/english/currentlevel.php", "POST", field, data);
                                    if (putData.startPut()) {
                                        if (putData.onComplete()) {
                                            //progressBar.setVisibility(View.GONE);
                                            String result = putData.getResult();
                                        }
                                    }
                                    //End Write and Read data with URL
                                }
                            });

                            Intent intent = new Intent();
                            intent.setClass(YourLevel.this,MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }
                else if(progress == 4){
                    sorttextview.setText("It was Einstein who first conceived of a nuclear fission weapon that he felt Germany was on the verge of discovery. He urged President Franklin D. Roosevelt to begin development of a similar weapon, but also felt it was best not to use it. His warning about the Nazis served the president well, and the Manhattan Project was born. This project was designed to develop a nuclear weapon in the United States in case Hitler unleashed his own weapon. Einstein was a life-long pacifist. He spoke of the purity of science, and how it should be used for the betterment of Humankind, and not for creating weapons of mass destruction. \n");
                    level.setText("Level 5");
                    sortbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Handler handler = new Handler();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    String[] field = new String[2];
                                    field[0] = "username";
                                    field[1] = "currentlevel";


                                    String[] data = new String[2];
                                    data[0] = "A";
                                    data[1] = "5";


                                    PutData putData = new PutData("http://163.13.201.116:8080/english/currentlevel.php", "POST", field, data);
                                    if (putData.startPut()) {
                                        if (putData.onComplete()) {
                                            //progressBar.setVisibility(View.GONE);
                                            String result = putData.getResult();
                                        }
                                    }
                                    //End Write and Read data with URL
                                }
                            });

                            Intent intent = new Intent();
                            intent.setClass(YourLevel.this,MainActivity.class);
                            startActivity(intent);
                        }
                    });

                }
                else if(progress == 5){
                   sorttextview.setText("Manchester United Football Club is the biggest brand in world football and the world’s richest club. The English Premier League club was founded as Newton Heath in 1878 and changed its name to Manchester United in 1902. It is one of the most successful clubs in English football and perhaps the most widely-supported in the world, with as many as 350 million fans. In 2010, Forbes magazine ranked Manchester United second to the New York Yankees in its list of the world’s most valuable sports team brands. The club has always managed to secure lucrative sponsorship deals and regularly receives the highest share of TV rights. United manage its worldwide exposure well through its own TV channel, MUTV. It also has a financial services arm and a foundation to help poorer children succeed.");
                    level.setText("Level 6");
                    sortbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Handler handler = new Handler();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    String[] field = new String[2];
                                    field[0] = "username";
                                    field[1] = "currentlevel";


                                    String[] data = new String[2];
                                    data[0] = "A";
                                    data[1] = "6";


                                    PutData putData = new PutData("http://163.13.201.116:8080/english/currentlevel.php", "POST", field, data);
                                    if (putData.startPut()) {
                                        if (putData.onComplete()) {
                                            //progressBar.setVisibility(View.GONE);
                                            String result = putData.getResult();
                                        }
                                    }
                                    //End Write and Read data with URL
                                }
                            });

                            Intent intent = new Intent();
                            intent.setClass(YourLevel.this,MainActivity.class);
                            startActivity(intent);
                        }
                    });

                }
                else if(progress == 6){
                    sorttextview.setText("A lush green forest in the middle of a rocky wasteland. No, this paradise is not an illusion. Abdul Kareem has created it with his own hands.Kareem’s 30-acre forest is in Kasargode district, Kerala. It is home to 1,500 medicinal plants, 2,000 varieties of trees, rare birds, animals and insects. Agricultural scientist, MS Swaminathan, has called the forest a “wonderful example of the power harmony with nature.”So, how did Kareem manage to convert a wasteland into a forest?Let us go back 24 years, to 1977, when Kareem purchased a five-acre rocky wasteland. Kareem was an airlines ticketing agent with a craze for the woods. Though he never went to college, he could talk about the properties of plants and trees like an expert botanist, reportsThe Hindustan Times.Kareem dug a huge well and began to toil in the rocky, arid terrain. In the beginning, people thought he was crazy to waste his time and money on wasteland. But, Kareem has ‘green fingers’ (a term used for people who love nature). Soon, he began investing more and more of his savings to add land and amenities.Today, the ‘wasteland’ is the haven of nature-lovers – from students wanting to explore the woods, to agricultural scientists. Kareem has been honoured by several organisations, including the United Nations, for his work.He just let his forest grow naturally, without insecticides or fertilisers. He believed in the ability of nature to replenish itself without the interference of humans. That’s why he does not allow fallen leaves or twigs from the forest to be removed.Recently, Kareem even refused an offer by a well-known resort to launch an Ayurveda (ancient Hindu practice of holistic medicine) centre in the forest.“I wanted to spread the message that if trees, animals and birds survive, only then human beings have a future,” Kareem said in an interview.Shouldn’t we be listening?");
                    level.setText("Level 7");
                    sortbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Handler handler = new Handler();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    String[] field = new String[2];
                                    field[0] = "username";
                                    field[1] = "currentlevel";


                                    String[] data = new String[2];
                                    data[0] = "A";
                                    data[1] = "7";


                                    PutData putData = new PutData("http://163.13.201.116:8080/english/currentlevel.php", "POST", field, data);
                                    if (putData.startPut()) {
                                        if (putData.onComplete()) {
                                            //progressBar.setVisibility(View.GONE);
                                            String result = putData.getResult();
                                        }
                                    }
                                    //End Write and Read data with URL
                                }
                            });

                            Intent intent = new Intent();
                            intent.setClass(YourLevel.this,MainActivity.class);
                            startActivity(intent);
                        }
                    });

                }
                else if(progress == 7){
                    sorttextview.setText("It is not a new way of spreading happiness. The method has been practised for a while and has also been captured on celluloid by Hollywood in the film ‘Patch Adams’. In the film (based on a true story) Patch Adams is the name of a studentof medicine, who decides to use humour to help patients. The role was played by Robin Williams, Hollywood’s leading comedian.This technique has been adopted in Brazil, to great success. Members of the ‘Group of Frolic’ (Turma da Pholia) regularly visit hospitals to cheer patients in Rio de Janeiro. Their clowning around helps in the treatment, say doctors. While their methods might be somewhat unusual, a sound principle governs them: a good state of mind can increase the immunity of a patient and speed up recovery.The Cure is LaughterBut how do they bring about a good state of mind? By bringing theatre, music and poetry to people in hospitals, orphanages and asylums. “The reception we have been getting is wonderful. The reward for bringing a smile to the face of a child is well worth the effort that we put in,” says Micheli Bento Cortermani, a 16-year-old member of the group.The ‘Group of Frolic’ was founded in São Paulo two years ago. The group consists of 25 actors, clowns and musicians, between 16 and 30 years of age.“I have not done any scientific research on the subject but aftermembers of the group have visited the hospital, the patients acceptthe treatment more easily,” says a doctor.Some research on the subject has been done, though. Doctor SebastiãoCarlos Silva Jr, is a neurosurgeon (brain surgeon) at the Clinic of Happiness of theSchool of Medicine of São José do Rio Preto. He has writtenan entire article on various ways to handle pain.Members of bodies like the Clinic of Happiness, through their visits, try and forge close links with patients suffering from chronic pain. They do so by showing the patients how to breathe properly, and encouraging them to visualize and relax through the performances.These activities act on the emotions and imagination of those who are suffering. The connection thus formed between the body and the mind helps in reducing pain, says Carlos. For it’s known that the feeling that one is in pain is morewhen the person is scared, or sad, or lonely, or is suffering from a lack of sleep.The patients look forward to regular visits by members of the group(they visit at the same time every day) help in breaking monotony.“It is nice to have the same group of people coming regularlybecause patients, especially children, look forward to thesevisits,” says he.Members of the Clinic of Happiness go to five hospitals on a regular basis and collect money for work through the sale of postcards and T-shirts on street corners. They are thoroughly trained for the job, even not to be depressed while dealing with seriously-ill patients.In the city of São Paulo, another group called Doctor of Happiness (Doutores daAlegria) is also following the same technique to make patients happy. It does not work with volunteers though. Members of this group too visit hospitals at the same time on specific days of the week. They create a strong bond of friendship and trust with the patients.These groups are yet another testimony to the time-tested saying, that laughter can be the best medicine.");
                    level.setText("Level 8");
                    sortbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Handler handler = new Handler();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    String[] field = new String[2];
                                    field[0] = "username";
                                    field[1] = "currentlevel";


                                    String[] data = new String[2];
                                    data[0] = "A";
                                    data[1] = "8";


                                    PutData putData = new PutData("http://163.13.201.116:8080/english/currentlevel.php", "POST", field, data);
                                    if (putData.startPut()) {
                                        if (putData.onComplete()) {
                                            //progressBar.setVisibility(View.GONE);
                                            String result = putData.getResult();
                                        }
                                    }
                                    //End Write and Read data with URL
                                }
                            });

                            Intent intent = new Intent();
                            intent.setClass(YourLevel.this,MainActivity.class);
                            startActivity(intent);
                        }
                    });

                }
                else if(progress == 8){
                    sorttextview.setText("Long before roads needed traffic lights, railways were using a system of signals to control train traffic. In the early railways, a single track was used for both up-going and down-going trains, and safety depended on spacing the arrival and departure of trains according to time intervals.These signals consisted of a ball and something that looked like a kite. When the kite was raised on top it indicated danger while if the ball was raised, it indicated the all clear.In 1841, the first semaphore (visual form of signalling) railway signal was installed at London station. This consisted of a signal arm in a horizontal position to express ‘stop’. If lowered to 45 degrees it urged the engine driver to proceed with caution and if pointed vertically skyward it indicated the track was clear. The signals were painted red as it was easy to identify and attract the driver’s attention.At night, oil lamps were added on top of these poles and a red light indicated ‘stop’ while a white light indicated that the driver could proceed.However, the white coloured light proved disastrous as it stood for both ‘the all clear’ and ‘warning indications’ and so resulted in many accidents.In January 1876, the engine driver of the Flying Scotsman, an express train, rammed into a freight train running ahead on the same track because of the confusion in the signals. The derailment blocked the track in the opposite direction. This caused a major accident as the Manchester express train, which was running on this track, crashed into the already derailed bogies, killing 13 people and seriously injuring 24 others.Following this disastrous accident, the signal system was modified. First, the normally displayed indication for ‘stop’ became red while an additional yellow light was added to indicate to the driver to proceed with caution. In 1893, green lights replaced the white light to avoid confusion as street lighting and house lighting hampered the driver from reading the signal rightly.Since railway signalling was introduced, civic officials decided to try it out in the congested streets of London. Thus, even before the advent of motorcars, traffic lights were introduced in 1868 in London to control the flow of horse buggies, wagons and pedestrians.This signal was a revolving lantern with red and green signals. Red meant ‘stop’ and green meant ‘caution’. The lantern was lit by gas and turned up by means of a lever at its base so that the appropriate light faced the traffic.But in an unfortunate incident this traffic light exploded, on January 2, 1869, injuring the policeman who was operating it.With automobiles being introduced in America and Britain the traffic situation was getting problematic. A police officer called William Potts of Detroit, Michigan, decided to do something about the problem and he decided to adapt railroad signals for street use.His real problem was that rail traffic ran along one way while traffic flow at intersections ran at right angles and were four-way.Nevertheless, he used two-coloured lights, red and green, and connected the signals by electricity. The first traffic light was thus installed in 1920 on the corner of Woodward and Michigan Avenues in Detroit.But these lights were manually operated and were still two-way lights. At about the same time, Garrett Augustus Morgan Sr., an African-American, from Cleveland, Ohio, realized the need for traffic lights after seeing a collision between an automobile and a horse-drawn carriage.Distressed by the accident he decided to do something about it and on November 20, 1923 patented the four-way traffic light signal.Who Invented Traffic Lights?Today, traffic lights have an internal timer that is programmed to stay on for a specific amount of time. Policemen also control the lights on certain roads depending on the amount of traffic.Morgan’s invention has indeed saved many thousands of lives and created vehicular order among chaos. So next time you wait at a red light, Relax, and thank Garrett Augustus Morgan for his invention.");
                    level.setText("Level 9");
                    sortbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Handler handler = new Handler();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    String[] field = new String[2];
                                    field[0] = "username";
                                    field[1] = "currentlevel";


                                    String[] data = new String[2];
                                    data[0] = "A";
                                    data[1] = "9";


                                    PutData putData = new PutData("http://163.13.201.116:8080/english/currentlevel.php", "POST", field, data);
                                    if (putData.startPut()) {
                                        if (putData.onComplete()) {
                                            //progressBar.setVisibility(View.GONE);
                                            String result = putData.getResult();
                                        }
                                    }
                                    //End Write and Read data with URL
                                }
                            });

                            Intent intent = new Intent();
                            intent.setClass(YourLevel.this,MainActivity.class);
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
    /*public static void  Yourcurrentlevel (String level) throws JSONException {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[2];
                field[0] = "username";
                field[1] = "currentlevel";


                String[] data = new String[2];
                data[0] = "A";
                data[1] = level;


                PutData putData = new PutData("http://163.13.201.116:8080/english/currentlevel.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        //progressBar.setVisibility(View.GONE);
                        String result = putData.getResult();
                    }
                }
                //End Write and Read data with URL
            }
        });


    }

     */



}
