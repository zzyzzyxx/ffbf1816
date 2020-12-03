package com.example.ffbfv14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class EateryDetail extends AppCompatActivity {
        TextView tv_det_name, tv_det_desc;
        ImageView iv_detail;
        Button btn_rev,btn_post, btn_book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eatery_detail);
            Intent i = getIntent();
            tv_det_name = findViewById(R.id.tv_det_name);
            tv_det_desc = findViewById(R.id.tv_det_desc);
            iv_detail = findViewById(R.id.iv_detail);
            btn_rev = findViewById(R.id.btn_rev);
            btn_post = findViewById(R.id.btn_post);
            btn_book = findViewById(R.id.btn_book);



            Eatery eats = i.getParcelableExtra("Eatery");
            Picasso.get().load(eats.getUrl()).fit().into(iv_detail);
            tv_det_name.setText(eats.getName());
            tv_det_desc.setText(eats.getDesc());
            String type = eats.getType();

            //if the Eatery type is Foodstall, booking option is invisible

        if(type.compareTo("Foodstall") == 0){
            btn_book.setVisibility(View.INVISIBLE);
        }
        else{
            btn_book.setVisibility(View.VISIBLE);
        }

        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EateryDetail.this, Booking.class));
            }
        });

    }
}