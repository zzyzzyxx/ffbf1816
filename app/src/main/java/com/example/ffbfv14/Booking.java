package com.example.ffbfv14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Booking extends AppCompatActivity {
    WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        wv = findViewById(R.id.id_wv);
        wv.setWebViewClient(new WebViewClient());

        wv.loadUrl("https://www.opentable.com/");
        //fixing javascript on websites
        WebSettings wSettings = wv.getSettings();
        wSettings.setJavaScriptEnabled(true);
    }
// method to fix the navigation via back button on the phone
    @Override
    public void onBackPressed() {
        if(wv.canGoBack()){
            wv.goBack();
        }
        else{super.onBackPressed();}

    }
}