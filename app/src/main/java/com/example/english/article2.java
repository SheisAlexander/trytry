package com.example.english;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class article2 extends AppCompatActivity {

    TextView title, article, keyword1, keyword2, keyword3, keyword4, keyword5, keyword6, english_id;
    String result;
    String titles,articles,english_ids,keywords1s,keywords2s,keywords3s,keywords4s,keywords5s,keywords6s;

    String Word  ;
    ImageButton article_heart;
    Button next;
    RadioButton interesting,easy,boring,difficult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_article);
        Handler handler1 = new Handler(Looper.getMainLooper());
        handler1.post(new Runnable() {
            @Override
            public void run() {

                Bundle bundle = getIntent().getExtras();
                String h = bundle.getString("url");
                FetchData fetchData = new FetchData(h);
                if (fetchData.startFetch()) {
                    if (fetchData.onComplete()) {
                        result = fetchData.getResult();
                        //End ProgressBar (Set visibility to GONE)
                        Log.i("FetchData", result);
                    }
                }
                try {



                    JSONArray userArray = new JSONArray(result);

                    Random r = new Random();
                    int a = r.nextInt(userArray.length())+0;

                    for (int i = a; i < a+1; i++) {

                        //creating a json object for fetching single data
                        JSONObject userDetail = userArray.getJSONObject(i);
                        //Fetching title & tag and storing them in arraylist
                        titles = userDetail.getString("title");
                        articles=userDetail.getString("article");
                        english_ids=userDetail.getString("english_id");

                        JSONArray keywordArray = new JSONArray(userDetail.getString("keyword"));
                        keywords1s =keywordArray.getString(0);
                        keywords2s =keywordArray.getString(1);
                        keywords3s =keywordArray.getString(2);
                        keywords4s =keywordArray.getString(3);
                        keywords5s =keywordArray.getString(4);
                        keywords6s =keywordArray.getString(5);

                        title.setText(titles);
                        article.setText(articles);
                        english_id.setText(english_ids);

                        keyword1.setText(keywords1s+" , "+keywords2s+" , "+keywords3s+" , "+keywords4s+" , "+keywords5s+" , "+keywords6s);

                    }
                }catch (Exception e) {
                    result = e.toString();//如果出事，回傳錯誤訊息

                }
            }
        });

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[2];
                field[0] = "username";
                field[1] = "english_id";


                String[] data = new String[2];
                data[0] = "A";
                data[1] = english_ids;


                PutData putData = new PutData("http://163.13.201.116:8080/english/update2.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();

                    }
                }
                //End Write and Read data with URL
            }
        });


        title = findViewById(R.id.title02);
        article = findViewById(R.id.article01);
        keyword1 = findViewById(R.id.keyword01);
        keyword2 = findViewById(R.id.keyword02);
        keyword3 = findViewById(R.id.keyword03);
        keyword4 = findViewById(R.id.keyword04);
        keyword5 = findViewById(R.id.keyword05);
        keyword6 = findViewById(R.id.keyword06);
        english_id = findViewById(R.id.english_id);










        article_heart = findViewById(R.id.heartbutton);
        article_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] field = new String[2];
                        field[0] = "user_id";
                        field[1] = "english_id";


                        String[] data = new String[2];
                        data[0] = "12";
                        data[1] = english_ids;


                        PutData putData = new PutData("http://163.13.201.116:8080/english/collectarticle.php", "POST", field, data);
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




            }
        });

        //選擇喜好
        interesting=findViewById(R.id.interesting);
        easy = findViewById(R.id.easy);
        difficult = findViewById(R.id.difficult);
        boring = findViewById(R.id.boring);
        next =findViewById(R.id.next);

        //使用者選擇
        RadioGroup radgroup = (RadioGroup) findViewById(R.id.radioGroup);
        int id = radgroup.getCheckedRadioButtonId();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (id) {
                    case R.id.interesting:
                        Intent intent = new Intent();
                        intent.setClass(article2.this, article2.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("url", "http://163.13.201.116:8080/english/select4.php");
                        intent.putExtras(bundle);   // 記得put進去，不然資料不會帶過去哦
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.easy:

                        Intent intent2 = new Intent();
                        intent2.setClass(article2.this, article2.class);
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("url", "http://163.13.201.116:8080/english/select2.php");
                        intent2.putExtras(bundle2);   // 記得put進去，不然資料不會帶過去哦
                        startActivity(intent2);
                        finish();
                        break;
                    case R.id.difficult:

                        Intent intent3 = new Intent();
                        intent3.setClass(article2.this, article2.class);
                        Bundle bundle3 = new Bundle();
                        bundle3.putString("url", "http://163.13.201.116:8080/english/select1.php");
                        intent3.putExtras(bundle3);   // 記得put進去，不然資料不會帶過去哦
                        startActivity(intent3);
                        finish();
                        break;
                    case R.id.boring:

                        Intent intent4 = new Intent();
                        intent4.setClass(article2.this, article2.class);
                        Bundle bundle4 = new Bundle();
                        bundle4.putString("url","http://163.13.201.116:8080/english/select3.php");
                        intent4.putExtras(bundle4);   // 記得put進去，不然資料不會帶過去哦
                        startActivity(intent4);
                        finish();
                        break;
                }
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(article2.this,R.style.TransparentDialog);

                        builder.setCancelable(false);//這邊是設定使用者可否點擊空白處返回
                        View v = getLayoutInflater().inflate(R.layout.set_custom_dialog_layout_with_button,null);
                        builder.setView(v);
                        ImageButton close = v.findViewById(R.id.closebutton);
                        ImageButton heart  = v.findViewById(R.id.heartbutton1);
                        AlertDialog dialog = builder.create();
                        TextView dicText2 = v.findViewById(R.id.textView80);

                        // Instantiate the RequestQueue.
                        RequestQueue queue = Volley.newRequestQueue(article2.this);

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
                                Toast.makeText(article2.this, "一次只能點選一個字", Toast.LENGTH_SHORT).show();
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
                        RequestQueue queue2 = Volley.newRequestQueue(article2.this);

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
                                Toast.makeText(article2.this, "Wrong", Toast.LENGTH_SHORT).show();
                            }
                        });
                        queue2.add(request2);
                        mode.finish();
                        break;
                    case R.id.unhighlight:
                        Toast.makeText(article2.this, "取消螢光筆", Toast.LENGTH_SHORT).show();
                        ssb.setSpan(new BackgroundColorSpan(Color.TRANSPARENT),start,end,1);
                        article.setText(ssb);
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
        Log.d("article2", "onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
        Log.d("article2", "onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();

        // Another activity is taking focus (this activity is about to be "paused").
        Log.d("article2", "onPause");

    }
    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
        Log.d("article2", "onStop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
        Log.d("article2", "onDestroy");
    }



}