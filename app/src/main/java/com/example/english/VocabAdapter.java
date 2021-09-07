package com.example.english;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class VocabAdapter extends RecyclerView.Adapter<VocabAdapter.MyViewHolder> {
    // creating  arraylists
    ArrayList<String> vocabulary;



    //constructor


    public VocabAdapter(ArrayList<String> vocabulary) {
        this.vocabulary = vocabulary;
    }

    // ViewHolders
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        //Inflate the item layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vocablayout,parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        //set the data in items
        holder.vocab.setText(vocabulary.get(position));





    }

    @Override
    public int getItemCount(){
        return vocabulary.size();
    }
    //myholder class
    public class MyViewHolder extends RecyclerView.ViewHolder{
        //widgets
        TextView vocab;

        //Constructor
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            //set onclick listener here

            vocab = itemView.findViewById(R.id.title01);





        }
    }

}
