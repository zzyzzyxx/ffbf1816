    package com.example.ffbfv14;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class Eaterylist extends AppCompatActivity implements EateryAdapter.EateryHolder.EateryInterface {
    RecyclerView rv;
    RecyclerView.LayoutManager manager;
    EateryAdapter adapter;
    //instead of Database reference
    Query dbref;
    ArrayList<Eatery> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eaterylist);
        rv = findViewById(R.id.id_rv);
        Intent i = getIntent();
        String type = i.getStringExtra("type");

        dbref = FirebaseDatabase.getInstance().getReference("_eateries_").orderByChild("type").equalTo(type);
        manager = new LinearLayoutManager(Eaterylist.this);
        //attach the manager to the recycleview
        rv.setLayoutManager(manager);

        dbref.addListenerForSingleValueEvent(listener);

        // floating button visibility for recycleview screens
        FloatingActionButton fab = findViewById(R.id.fab);
        if(type.compareTo("Restaurant") == 0){
            fab.setVisibility(View.INVISIBLE);
        }
        else{
            fab.setVisibility(View.VISIBLE);
        }

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i  = new Intent(Eaterylist.this, UploadImage.class);
                startActivity(i);
               // Here I created Snackbar, but it's no longer needed... R.I.P Snackbar
            }
        });
    }
    //to read from the database
    ValueEventListener listener = new ValueEventListener() {
        @Override
        //all objects from eateries node will be stored in snapshot object
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for(DataSnapshot dss: snapshot.getChildren())
            {
             Eatery eats = dss.getValue(Eatery.class);
             list.add(eats);
            }
            Toast.makeText(Eaterylist.this, String.valueOf(list.size()), Toast.LENGTH_LONG).show();
            //instantiate adapter object
            adapter = new EateryAdapter(list, Eaterylist.this);
            //attach the adapter to the recycleview
            rv.setAdapter(adapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(Eaterylist.this, "Something went wrong, try again.", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onEateryClick(int i) {
        Intent intent = new Intent(Eaterylist.this, EateryDetail.class);
        intent.putExtra("Eatery", list.get(i));
        startActivity(intent);
    }

}