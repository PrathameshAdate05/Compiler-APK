package com.prathamesh.compiler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Splash extends AppCompatActivity {

    ImageView splashBack;
    ImageView splashTitle1;
    Animation fromLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        splashBack = findViewById(R.id.IVSplashBack);
        splashTitle1 = findViewById(R.id.IVSplashTitle1);

        Glide.with(Splash.this).load(R.drawable.splashgif).into(splashBack);
        fromLeft = AnimationUtils.loadAnimation(this,R.anim.splashtitileanim);
        splashTitle1.setAnimation(fromLeft);


    }
}