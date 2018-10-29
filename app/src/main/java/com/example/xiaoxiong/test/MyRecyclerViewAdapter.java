package com.example.xiaoxiong.test;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter {

    private int[] myData;

    static class ViewHolder extends RecyclerView.ViewHolder{
        int i;
        public ViewHolder(View view){
            super(view);
        }
    }

    public MyRecyclerViewAdapter(int[] data){
        myData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
