package com.example.ffbfv14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Choice extends AppCompatActivity implements View.OnClickListener{
    ImageButton rest, foods, prof;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        rest = findViewById(R.id.imBrest);
        rest.setOnClickListener(this);
        foods = findViewById(R.id.imBfood);
        foods.setOnClickListener(this);
        prof = findViewById(R.id.imBprofile);
        prof.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.imBrest:
                Intent i = new Intent(this,Eaterylist.class);
                // add
                i.putExtra("type", "Restaurant");
                startActivity(i);
                break;
            case R.id.imBfood:
                Intent j = new Intent(this,Eaterylist.class);
                j.putExtra("type", "Foodstall");
                                startActivity(j);
                break;
            case R.id.imBprofile:
                startActivity(new Intent(this, ProfileActivity.class));
        }

    }
}