package com.example.english;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Dictionary extends AppCompatActivity {

    Button dicButton;
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

        dicButton = findViewById(R.id.DictionaryButton);
        wordText = findViewById(R.id.WordText);
        dicText = findViewById(R.id.DictionaryText);

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
                        //dicText.setText("Part of Speech: "+wordpos+"\n" +"Mean:"+worddef+"\n");
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
    }
}