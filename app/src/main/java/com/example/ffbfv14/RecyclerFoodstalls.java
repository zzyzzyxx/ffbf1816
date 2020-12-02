package com.example.ffbfv14;
// KLASA NIEUZYWANA, DO USUNIECIA
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class RecyclerFoodstalls extends AppCompatActivity {
    // for RecycleView to work 3 parts are needed:
    //this variable contains recycleview created in layout
    private RecyclerView mRecyclerView;
    // the bridge between the data in ArrayList and RecycleView, that provides as much data at once as we need
    private RecyclerView.Adapter mAdapter;
        // manager responsible for aligning single items in ArrayList
    private RecyclerView.LayoutManager mLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_foodstalls);

        ArrayList<Item> itemsList = new ArrayList<>();
        itemsList.add(new Item(R.drawable.ic_baseline, "Line 1", "Line 2"));
        itemsList.add(new Item(R.drawable.ic_business, "Line 3", "Line 4"));
        itemsList.add(new Item(R.drawable.ic_brightness, "Line 5", "Line 6"));
        itemsList.add(new Item(R.drawable.ic_baseline, "Line 7", "Line 8"));
        itemsList.add(new Item(R.drawable.ic_business, "Line 9", "Line 10"));
        itemsList.add(new Item(R.drawable.ic_brightness, "Line 11", "Line 12"));
        itemsList.add(new Item(R.drawable.ic_baseline, "Line 13", "Line 14"));
        itemsList.add(new Item(R.drawable.ic_business, "Line 15", "Line 16"));
        itemsList.add(new Item(R.drawable.ic_brightness, "Line 17", "Line 18"));
        itemsList.add(new Item(R.drawable.ic_baseline, "Line 19", "Line 20"));
        itemsList.add(new Item(R.drawable.ic_business, "Line 21", "Line 22"));
        itemsList.add(new Item(R.drawable.ic_brightness, "Line 23", "Line 24"));
        itemsList.add(new Item(R.drawable.ic_baseline, "Line 25", "Line 26"));
        itemsList.add(new Item(R.drawable.ic_business, "Line 27", "Line 28"));
        itemsList.add(new Item(R.drawable.ic_brightness, "Line 29", "Line 30"));
        itemsList.add(new Item(R.drawable.ic_baseline, "Line 31", "Line 32"));
        itemsList.add(new Item(R.drawable.ic_business, "Line 33", "Line 34"));
        itemsList.add(new Item(R.drawable.ic_brightness, "Line 35", "Line 36"));



        mRecyclerView = findViewById(R.id.recyclerFoods);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ItemAdapter(itemsList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
}