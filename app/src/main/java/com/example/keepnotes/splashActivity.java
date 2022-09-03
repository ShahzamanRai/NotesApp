package com.example.keepnotes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class splashActivity extends AppCompatActivity {

    TextView tView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tView = findViewById(R.id.textSplash);
        Animation move = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        tView.startAnimation(move);


        new Handler().postDelayed(() -> {
            Intent iNext = new Intent(splashActivity.this, MainActivity.class);
            startActivity(iNext);
            finish();
        }, 5100);

    }
}