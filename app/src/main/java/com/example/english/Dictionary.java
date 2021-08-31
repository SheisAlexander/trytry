package com.example.english;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Locale;

public class Dictionary extends AppCompatActivity {

    Button dicButton;
    ImageButton soundButton;
    EditText wordText;
    TextView dicText;

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
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
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
                        return true;
                }
                return false;
            }
        });

        dicButton   = findViewById(R.id.DictionaryButton);
        wordText    = findViewById(R.id.WordText);
        dicText     = findViewById(R.id.DictionaryText);
        soundButton = findViewById(R.id.soundButton);

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
                            //wordpos = wordmeanp.getString("partOfSpeech");

                            JSONArray wordmean = meanings.getJSONArray("definitions");

                            //第三層
                            JSONObject worddefs = wordmean.getJSONObject(0);
                            worddef = worddefs.getString("definition");

                            //worddef = wordInfo.getString("meanings");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        dicText.append("詞性: "+"\n"+wordpos+"意思: "+"\n"+worddef);

                        //Toast.makeText(Dictionary.this, "Mean: "+worddef, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Dictionary.this, "Wrong", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(request);

                // Request a string response from the provided URL.
//                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                Toast.makeText(Dictionary.this, response, Toast.LENGTH_SHORT).show();
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(Dictionary.this, "Error!", Toast.LENGTH_SHORT).show();
//                    }
//                });

                // Add the request to the RequestQueue.


                //Toast.makeText(Dictionary.this, ":)", Toast.LENGTH_SHORT).show();
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
    }
}