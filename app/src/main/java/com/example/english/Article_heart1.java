package com.example.english;

import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;


public class Article_heart1 extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> keywords1 = new ArrayList<>();
    ArrayList<String> keywords2 = new ArrayList<>();
    ArrayList<String> keywords3 = new ArrayList<>();
    ArrayList<String> keywords4 = new ArrayList<>();
    ArrayList<String> keywords5 = new ArrayList<>();
    ArrayList<String> keywords6 = new ArrayList<>();
    ArrayList<String> articles = new ArrayList<>();
    ArrayList<String> english_ids = new ArrayList<>();


    String result;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        Log.d("Article_heart1", "onCreate");

        setContentView(R.layout.activity_article_heart1);

        //Recyclerview
        recyclerView = findViewById(R.id.recyclerView);
        //Recyclerview configuration

        Handler handler1 = new Handler(Looper.getMainLooper());
        handler1.post(new Runnable() {
            @Override
            public void run() {


                FetchData fetchData = new FetchData("http://163.13.201.116:8080/english/collectarticle1.php");
                if (fetchData.startFetch()) {
                    if (fetchData.onComplete()) {
                        result = fetchData.getResult();
                        //End ProgressBar (Set visibility to GONE)
                        Log.i("FetchData", result);
                    }
                }
                try {

                    JSONArray userArray  = new JSONArray(result);
                    for(int i=0;i<userArray.length();i++){

                        //creating a json object for fetching single data
                        JSONObject userDetail = userArray.getJSONObject(i);
                        //Fetching title & tag and storing them in arraylist
                        titles.add(userDetail.getString("title"));
                        articles.add(userDetail.getString("article"));
                        english_ids.add(userDetail.getString("english_id"));

                        JSONArray keywordArray  = new JSONArray(userDetail.getString("keyword"));
                        keywords1.add(keywordArray.getString(0));
                        keywords2.add(keywordArray.getString(1));
                        keywords3.add(keywordArray.getString(2));
                        keywords4.add(keywordArray.getString(3));
                        keywords5.add(keywordArray.getString(4));
                        keywords6.add(keywordArray.getString(5));





                    }
                }catch (Exception e) {
                    result = e.toString();//如果出事，回傳錯誤訊息

                }
            }
        });

        //Recyclerview

        //Recyclerview configuration


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        ArticleAdapter articleAdapter = new ArticleAdapter(titles,keywords1,keywords2,keywords3,keywords4,keywords5,keywords6,articles,english_ids);
        recyclerView.setAdapter(articleAdapter);


        recyclerViewAction(recyclerView,titles,articleAdapter);



    }
    private void recyclerViewAction(RecyclerView recyclerView, final ArrayList<String> choose, final ArticleAdapter articleAdapter){
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN
                        , ItemTouchHelper.LEFT ); //這裡是告訴RecyclerView你想開啟哪些操作
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int position_dragged = viewHolder.getAdapterPosition();
                int position_target = target.getAdapterPosition();
                Collections.swap(choose, position_dragged, position_target);
                articleAdapter.notifyItemMoved(position_dragged, position_target);
                return false;//管理上下拖曳
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //管理滑動情形
                int position = viewHolder.getAdapterPosition();
                switch (direction) {

                    case ItemTouchHelper.LEFT:


                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                String[] field = new String[2];
                                field[0] = "user_id";
                                field[1] = "word";
                                String[] data = new String[2];
                                data[0] = "12";
                                data[1] = choose.get(position);
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
                                articleAdapter.notifyItemRemoved(position);
                                choose.remove(position);
                                //End Write and Read data with URL
                            }
                        });
                        break;
                }
            }
            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                new RecyclerViewSwipeDecorator.Builder(c,recyclerView,viewHolder,dX,dY,actionState,isCurrentlyActive)
                        .addBackgroundColor(ContextCompat.getColor(Article_heart1.this,android.R.color.holo_red_dark))
                        .addActionIcon(R.drawable.ic_baseline_delete_24)
                        .create()
                        .decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

        });
        helper.attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
        Log.d("MainActivity", "onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
        Log.d("MainActivity", "onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
        Log.d("MainActivity", "onPause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
        Log.d("MainActivity", "onStop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
        Log.d("MainActivity", "onDestroy");
    }






}
