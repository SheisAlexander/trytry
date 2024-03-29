package com.example.english;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Dictionary extends AppCompatActivity {

    TextView dicButton;
    ImageButton soundButton;
    EditText wordText;
    TextView dicText;
    ImageButton d_like;
    int flag =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dictionary);

        //Initialize and Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //Set home selected
        bottomNavigationView.setSelectedItemId(R.id.dictionary);
        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.home:
                        Intent intent = new Intent(Dictionary.this,MainActivity.class);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.analysis:
                        startActivity(new Intent(getApplicationContext(),Analysis.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.dictionary:
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), Settings.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                }
                return false;
            }
        });

        dicButton   = findViewById(R.id.DictionaryButton);
        wordText    = findViewById(R.id.WordText);
        dicText     = findViewById(R.id.DictionaryText);
        soundButton = findViewById(R.id.soundButton);
        d_like = findViewById(R.id.heartButton);
        d_like.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24_dark);
        soundButton .setBackgroundResource(R.drawable.ic_baseline_volume_up_24_dark);
        dicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dicText.setText("");
                final TextView textView = (TextView) findViewById(R.id.text);

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(Dictionary.this);
                String url ="https://api.dictionaryapi.dev/api/v2/entries/en/" + wordText.getText().toString();

                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null,new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String worddef = "";
                        String wordpos = "";
                        try {
                            //第一層
                            JSONObject wordInfo = response.getJSONObject(0);
                            JSONArray word = wordInfo.getJSONArray("meanings");
                            //第二層
                            JSONObject meanings = word.getJSONObject(0);

                            //JSONObject wordmeanp = meanings.getJSONObject("partOfSpeech");
                            wordpos = meanings.getString("partOfSpeech");

                            JSONArray wordmean = meanings.getJSONArray("definitions");

                            //第三層
                            JSONObject worddefs = wordmean.getJSONObject(0);
                            worddef = worddefs.getString("definition");

                            //worddef = wordInfo.getString("meanings");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        dicText.append("詞性: "+"\n"+wordpos+"\n"+"意思: "+"\n"+worddef);

                        //Toast.makeText(Dictionary.this, "Mean: "+worddef, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Dictionary.this, "Wrong", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(request);
            }
        });

        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio();
            }

            private void playAudio() {
                final TextView textView = (TextView) findViewById(R.id.text);

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(Dictionary.this);
                String url ="https://api.dictionaryapi.dev/api/v2/entries/en/" + wordText.getText().toString();
                // initializing media player
                MediaPlayer mediaPlayer = new MediaPlayer();

                // below line is use to set the audio
                // stream type for our media player.
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null,new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String wordsound = "";
                        try {
                            //第一層
                            JSONObject wordInfo = response.getJSONObject(0);
                            JSONArray phonetics = wordInfo.getJSONArray("phonetics");
                            //第二層
                            JSONObject audio = phonetics.getJSONObject(0);
                            wordsound = audio.getString("audio");

                            String url1 = "https:"+wordsound;

                            mediaPlayer.setDataSource(url1);
                            mediaPlayer.prepare();
                            mediaPlayer.start();


                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }

                        //Toast.makeText(Dictionary.this, "Mean: "+worddef, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Dictionary.this, "Wrong", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(request);

            }
        });


        d_like.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // IB_PullDown.setBackgroundResource(R.drawable.pulldown_button_image);
                if (flag == 0) {
                    // TODO Auto-generated method stub
                    d_like.setBackgroundResource(R.drawable.ic_baseline_favorite_24_dark);
                    // ll_AirItem.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[2];
                            field[0] = "user_id";
                            field[1] = "word";
                            String[] data = new String[2];
                            data[0] = "12";
                            data[1] = wordText.getText().toString();
                            PutData putData = new PutData("http://163.13.201.116:8080/english/collectword.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    //progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if (result.equals("儲存成功")) {
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                    flag = 1;
                } else {
                    d_like.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24_dark);
                    ///ll_AirItem.setVisibility(View.GONE);
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[2];
                            field[0] = "user_id";
                            field[1] = "word";
                            String[] data = new String[2];
                            data[0] = "12";
                            data[1] = wordText.getText().toString();;
                            PutData putData = new PutData("http://163.13.201.116:8080/english/delete.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    //progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if (result.equals("刪除成功")) {
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            //End Write and Read data with URL
                        }
                    });
                    flag = 0;
                }
            }

        });

    }

}