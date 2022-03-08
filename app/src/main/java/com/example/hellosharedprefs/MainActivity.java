package com.example.hellosharedprefs;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btRes, btRed, btBlack, btBlue, btGreen, btCount, btReset;
    private int currentCount=0, currentColor;
    private final String COUNT_KEY = "count";
    private final String COLOR_KEY = "color";
    private SharedPreferences mPreferences;

    private final View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId()==R.id.btRed){
                int color = Color.parseColor("#F44336");
                btRes.setBackgroundColor(color);
                currentColor=color;
            } else if (view.getId()==R.id.btBlack){
                int color = Color.parseColor("#121111");
                btRes.setBackgroundColor(color);
                currentColor=color;
            } else if (view.getId()==R.id.btBlue){
                int color = Color.parseColor("#2196F3");
                btRes.setBackgroundColor(color);
                currentColor=color;
            } else if (view.getId()==R.id.btGreen){
                int color = Color.parseColor("#4CAF50");
                btRes.setBackgroundColor(color);
                currentColor=color;
            } else if (view.getId()==R.id.btCount){
                currentCount++;
                btRes.setText(String.valueOf(currentCount));
            } else if (view.getId()==R.id.btReset){
                currentCount = 0;
                btRes.setText(String.valueOf(currentCount));

                currentColor = Color.parseColor("#B5AFAF");
                btRes.setBackgroundColor(currentColor);

                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                preferencesEditor.clear();
                preferencesEditor.apply();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btRes = findViewById(R.id.btRes);
        btRed = findViewById(R.id.btRed);
        btBlack = findViewById(R.id.btBlack);
        btBlue = findViewById(R.id.btBlue);
        btGreen = findViewById(R.id.btGreen);
        btCount = findViewById(R.id.btCount);
        btReset = findViewById(R.id.btReset);

        btRed.setOnClickListener(click);
        btBlack.setOnClickListener(click);
        btBlue.setOnClickListener(click);
        btGreen.setOnClickListener(click);
        btCount.setOnClickListener(click);
        btReset.setOnClickListener(click);


        String sharedPrefFile = "com.example.hellosharedprefs";
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        currentCount = mPreferences.getInt(COUNT_KEY, 0);
        btRes.setText(String.valueOf(currentCount));
        currentColor = mPreferences.getInt(COLOR_KEY, currentColor);
        btRes.setBackgroundColor(currentColor);
    }


    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(COUNT_KEY, currentCount);
        preferencesEditor.putInt(COLOR_KEY, currentColor);
        preferencesEditor.apply();
    }
}