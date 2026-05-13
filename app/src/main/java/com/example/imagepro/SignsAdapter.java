package com.example.imagepro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SignsAdapter extends RecyclerView.Adapter<SignsAdapter.ViewHolder > {
    public ArrayList<SignsList> signs;
    ItemClicked activity;
    public SignsAdapter(Context context, ArrayList<SignsList> list){
        signs=list;
        activity = (ItemClicked) context;
    }
    public interface ItemClicked{
        void onItemClicked(int index);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView pimg;
        TextView letter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            letter= itemView.findViewById(R.id.letter);
            pimg=itemView.findViewById(R.id.pimg);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemClicked(signs.indexOf((SignsList) v.getTag()));
                }
            });
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.signimages,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(signs.get(position));
        holder.letter.setText(signs.get(position).getLetter());
        String url = signs.get(position).getLetterTURL();
        System.out.println(url);
        Glide.with(holder.itemView.getContext()).load(url).centerCrop().into(holder.pimg);
    }

    @Override
    public int getItemCount() {
        return signs.size();
    }
}
