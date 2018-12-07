package com.jh.myapplication_5;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreenActivitiy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        EasySplashScreen config = new EasySplashScreen(SplashScreenActivitiy.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(4000)
                .withBackgroundColor(Color.parseColor("#1a1b29"))
                .withHeaderText("Header")
                .withFooterText("Footer")
                .withBeforeLogoText("Before logo text")
                .withAfterLogoText("After logo text")
                .withLogo(R.mipmap.ic_launcher_round);

        config.getHeaderTextView().setTextColor(Color.WHITE);
        config.getFooterTextView().setTextColor(Color.WHITE);
        config.getBeforeLogoTextView().setTextColor(Color.WHITE);
        config.getAfterLogoTextView().setTextColor(Color.WHITE);

        View easySplashscreen = config.create();
        setContentView(easySplashscreen);



    }
}
