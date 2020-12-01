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
                startActivity(new Intent(this,Restaurants.class));
                break;
            case R.id.imBfood:
                //startActivity(new Intent(this,RegistrationAndLogin.class));
                break;
            case R.id.imBprofile:
                startActivity(new Intent(this, ProfileActivity.class));
        }

    }
}