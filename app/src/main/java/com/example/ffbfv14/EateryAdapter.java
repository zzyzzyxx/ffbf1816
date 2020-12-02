package com.example.ffbfv14;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EateryAdapter extends RecyclerView.Adapter<EateryAdapter.EateryHolder> {

    ArrayList<Eatery> list;
    EateryHolder.EateryInterface listener;
// make the constructor for the list
    public EateryAdapter(ArrayList<Eatery> list, EateryHolder.EateryInterface _listener) {
        this.list = list;
        listener = _listener;
    }

    @NonNull
    @Override
    public EateryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.eaterycard, parent,false);
        EateryHolder holder = new EateryHolder(v, listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EateryHolder holder, int position) {
            holder.tv.setText(list.get(position).getName());
        Picasso.get().load(list.get(position).getUrl()).fit().into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    // for each item in Recycle View the holder object will hold one card
    public static class EateryHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView tv;
        ImageView iv;
        EateryInterface listener;
        public EateryHolder(@NonNull View itemView, EateryInterface _listener) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_card_name);
            iv = itemView.findViewById(R.id.iv_card_image);
            listener = _listener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            listener.onEateryClick(getAdapterPosition());
        }

        public interface EateryInterface{

            public void onEateryClick(int i);
        }
    }
}
