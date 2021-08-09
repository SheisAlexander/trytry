package com.example.english;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class read_viewJSON extends AppCompatActivity {



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_read_view_json);
        }
    }


//    @Override

//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
 //       try {
            // get JSONObject from JSON file
//            JSONObject obj = new JSONObject(loadJSONFromAsset());
//            // fetch JSONArray named users
//            JSONArray userArray = obj.getJSONArray("settings");
            // implement for loop for getting users list data
//            for (int i = 0; i < userArray.length(); i++) {
                // create a JSONObject for fetching single user data
//                JSONObject userDetail = userArray.getJSONObject(i);
                // fetch email and name and store it in arraylist
//                level.add(userDetail.getString("level"));
 //           }
//        } catch(JSONException e){
//            e.printStackTrace();

//        }
//        TextView textView = (TextView) findViewById(R.id.textView);

//        if (level.get(0) == "upper") {
//            textView.setText(level.get(0));
//        } else {

//        }