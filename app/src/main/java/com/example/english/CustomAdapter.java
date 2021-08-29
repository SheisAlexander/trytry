package com.example.english;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    // creating  arraylists
    ArrayList<String> titles;
    ArrayList<String> keywords1;
    ArrayList<String> keywords2;
    ArrayList<String> keywords3;
    ArrayList<String> keywords4;
    ArrayList<String> keywords5;
    ArrayList<String> keywords6;
    ArrayList<String> articles;


    //constructor


    public CustomAdapter(ArrayList<String> titles, ArrayList<String> keywords1, ArrayList<String> keywords2, ArrayList<String> keywords3, ArrayList<String> keywords4, ArrayList<String> keywords5, ArrayList<String> keywords6, ArrayList<String> articles) {
        this.titles = titles;
        this.keywords1 = keywords1;
        this.keywords2 = keywords2;
        this.keywords3 = keywords3;
        this.keywords4 = keywords4;
        this.keywords5 = keywords5;
        this.keywords6 = keywords6;
        this.articles = articles;
    }

    // ViewHolders
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder( @NotNull ViewGroup parent, int viewType) {
        //Inflate the item layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout,parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {
        //set the data in items
        holder.title.setText(titles.get(position));





    }

    @Override
    public int getItemCount(){
        return titles.size();
    }
    //myholder class
    public class MyViewHolder extends RecyclerView.ViewHolder{
        //widgets
        TextView title;

        //Constructor
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            //set onclick listener here
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //start new intent
                    Intent i = new Intent(v.getContext(),article.class);
                    //sending title of the cardview
                    i.putExtra("title",titles.get(getAdapterPosition()));
                    i.putExtra("article",articles.get(getAdapterPosition()));
                    i.putExtra("keyword1",keywords1.get(getAdapterPosition()));
                    i.putExtra("keyword2",keywords2.get(getAdapterPosition()));
                    i.putExtra("keyword3",keywords3.get(getAdapterPosition()));
                    i.putExtra("keyword4",keywords4.get(getAdapterPosition()));
                    i.putExtra("keyword5",keywords5.get(getAdapterPosition()));
                    i.putExtra("keyword6",keywords6.get(getAdapterPosition()));

                    //starting next activity from view "v"
                    v.getContext().startActivity(i);



                }
            });



            title = itemView.findViewById(R.id.title01);





        }
    }

}