package com.example.english;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class articleh extends AppCompatActivity {

    TextView title, article, keyword1;
    TextToSpeech textToSpeech;
    String Word ;
    //ProgressBar progressBar;

    ImageButton sound,stop,article_heart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_articleh);

        //set back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        title = findViewById(R.id.title02);
        article = findViewById(R.id.article01);
        keyword1 = findViewById(R.id.keyword01);


        Intent i = getIntent();
        String title0 = i.getStringExtra("title");
        title.setText(title0);

        String article0 = i.getStringExtra("article");
        article.setText(article0);
        String keyword01 = i.getStringExtra("keyword1");
        String keyword02 = i.getStringExtra("keyword2");
        String keyword03 = i.getStringExtra("keyword3");
        String keyword04 = i.getStringExtra("keyword4");
        String keyword05 = i.getStringExtra("keyword5");
        String keyword06 = i.getStringExtra("keyword6");

        keyword1.setText(keyword01+"  "+keyword02+"  "+keyword03+"  "+keyword04+"  "+keyword05+"  "+keyword06);


        //text to speech

        sound = findViewById(R.id.soundbutton);
        stop = findViewById(R.id.stopbutton);

        // create an object textToSpeech and adding features into it
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int i) {

                // if No error is found then only it will run
                if (i != TextToSpeech.ERROR) {
                    // To Choose language of speech
                    textToSpeech.setLanguage(Locale.US);

                }
            }
        });

        // Adding OnClickListener
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.speak(article0, TextToSpeech.QUEUE_FLUSH, null);

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textToSpeech.stop();


            }
        });



        /* ======================================== */
        //螢光筆、儲存
        // TextView article = findViewById(R.id.article01);
        article.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater menuInflater = mode.getMenuInflater();
                menuInflater.inflate(R.menu.selection_action_menu,menu);
                return true;//return false則不會顯示menu
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                MenuInflater menuInflater = mode.getMenuInflater();
                menu.clear();
                menuInflater.inflate(R.menu.selection_action_menu,menu);
                return true;//隱藏所有非本app的item
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                //根據item的ID處理點擊事件
                int start = article.getSelectionStart();
                int end = article.getSelectionEnd();
                SpannableStringBuilder ssb = new SpannableStringBuilder(article.getText());
                String selectedText = article.getText().toString().substring(start,end);

                switch (item.getItemId()){
                    case R.id.lookup:
                        AlertDialog.Builder builder = new AlertDialog.Builder(articleh.this,R.style.TransparentDialog);

                        builder.setCancelable(false);//這邊是設定使用者可否點擊空白處返回
                        View v = getLayoutInflater().inflate(R.layout.set_custom_dialog_layout_with_button,null);
                        builder.setView(v);
                        ImageButton close = v.findViewById(R.id.closebutton);
                        ImageButton heart  = v.findViewById(R.id.heartbutton1);
                        AlertDialog dialog = builder.create();
                        TextView dicText2 = v.findViewById(R.id.textView80);

                        // Instantiate the RequestQueue.
                        RequestQueue queue = Volley.newRequestQueue(articleh.this);

                        String url ="https://api.dictionaryapi.dev/api/v2/entries/en/" + selectedText;

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

                                    Word = wordInfo.getString("word");

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                dicText2.append("單字： "+Word+"\n"+"詞性: "+wordpos+"\n"+"意思: "+"\n"+worddef);

                                //Toast.makeText(Dictionary.this, "Mean: "+worddef, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(articleh.this, "一次只能點選一個字", Toast.LENGTH_SHORT).show();
                            }
                        });
                        queue.add(request);
                        dialog.show();
                        close.setOnClickListener((v1 -> {
                            dialog.dismiss();
                        }));
                        heart.setOnClickListener((v1 -> {
                            ssb.setSpan(new BackgroundColorSpan(Color.YELLOW),start,end,1);
                            article.setText(ssb);
                            //儲存至資料庫
                            Handler handler = new Handler();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    String[] field = new String[2];
                                    field[0] = "user_id";
                                    field[1] = "word";
                                    String[] data = new String[2];
                                    data[0] = "12";
                                    data[1] = Word;
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
                            dialog.dismiss();

                        }));
                        mode.finish();//收起menu
                        break;
                    case R.id.highlight:
                        //Toast.makeText(article.this, "螢光筆", Toast.LENGTH_SHORT).show();
                        ssb.setSpan(new BackgroundColorSpan(Color.YELLOW),start,end,1);
                        article.setText(ssb);
                        RequestQueue queue2 = Volley.newRequestQueue(articleh.this);

                        String url2 ="https://api.dictionaryapi.dev/api/v2/entries/en/" + selectedText;

                        JsonArrayRequest request2 = new JsonArrayRequest(Request.Method.GET,url2,null,new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                String worddef = "";
                                String wordpos = "";

                                try {
                                    //第一層
                                    JSONObject wordInfo = response.getJSONObject(0);

                                    Word = wordInfo.getString("word");
                                    Handler handler = new Handler();
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            String[] field = new String[2];
                                            field[0] = "user_id";
                                            field[1] = "word";
                                            String[] data = new String[2];
                                            data[0] = "12";
                                            data[1] = Word;
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
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                //Toast.makeText(Dictionary.this, "Mean: "+worddef, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(articleh.this, "Wrong", Toast.LENGTH_SHORT).show();
                            }
                        });
                        queue2.add(request2);
                        mode.finish();
                        break;
                    case R.id.unhighlight:
                        Toast.makeText(articleh.this, "取消螢光筆", Toast.LENGTH_SHORT).show();
                        ssb.setSpan(new BackgroundColorSpan(Color.TRANSPARENT),start,end,1);
                        article.setText(ssb);
                        RequestQueue queue3 = Volley.newRequestQueue(articleh.this);

                        String url3 ="https://api.dictionaryapi.dev/api/v2/entries/en/" + selectedText;

                        JsonArrayRequest request3 = new JsonArrayRequest(Request.Method.GET,url3,null,new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {

                                try {
                                    //第一層
                                    JSONObject wordInfo = response.getJSONObject(0);

                                    Word = wordInfo.getString("word");
                                    Handler handler = new Handler();
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            String[] field = new String[2];
                                            field[0] = "user_id";
                                            field[1] = "word";
                                            String[] data = new String[2];
                                            data[0] = "12";
                                            data[1] = Word;
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
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                //Toast.makeText(Dictionary.this, "Mean: "+worddef, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(articleh.this, "Wrong", Toast.LENGTH_SHORT).show();
                            }
                        });
                        queue3.add(request3);
                        mode.finish();
                        break;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
        //if( textToSpeech != null ) textToSpeech.shutdown();
        Log.d("article", "onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
        Log.d("article", "onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
        Log.d("article", "onPause");
    }
    @Override
    protected void onStop() {
        if( textToSpeech!= null ) textToSpeech.shutdown();
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
        Log.d("article", "onStop");
    }
    @Override
    protected void onDestroy() {
        if( textToSpeech!= null ) textToSpeech.shutdown();
        super.onDestroy();
        // The activity is about to be destroyed.
        Log.d("article", "onDestroy");
    }





}
