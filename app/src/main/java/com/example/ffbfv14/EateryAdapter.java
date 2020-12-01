package com.example.ffbfv14;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EateryAdapter extends RecyclerView.Adapter<EateryAdapter.EateryHolder> {

    ArrayList<Eatery> aList;
// make the constructor for the list
    public EateryAdapter(ArrayList<Eatery> aList) {
        this.aList = aList;
    }

    @NonNull
    @Override
    public EateryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.eaterycard, parent,false);
        EateryHolder holder = new EateryHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EateryHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class EateryHolder extends RecyclerView.ViewHolder{
        TextView tv;
        ImageView iv;
        public EateryHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_list_name);
            iv = itemView.findViewById(R.id.iv_list_image);
        }
    }
}
