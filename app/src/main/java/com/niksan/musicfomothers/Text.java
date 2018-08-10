package com.niksan.musicfomothers;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Text extends AppCompatActivity {
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        txt = findViewById(R.id.textView);
        Typeface font = Typeface.createFromAsset(this.getAssets(), "B Roya.ttf");
        txt.setTypeface(font);


    }




/*
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Home.circleMenu.openMenu();
    }*/
}
