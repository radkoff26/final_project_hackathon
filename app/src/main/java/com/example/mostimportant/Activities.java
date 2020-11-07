package com.example.mostimportant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activities extends AppCompatActivity {

    Button btn_meditation;
    Button btn_sport;
    Button btn_rest;
    Button btn_walking;
    Button btn_eating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        btn_meditation = (Button) findViewById(R.id.button9);
        btn_sport = (Button) findViewById(R.id.button10);
        btn_rest = (Button) findViewById(R.id.button11);
        btn_walking = (Button) findViewById(R.id.button12);
        btn_eating = (Button) findViewById(R.id.button13);

        btn_walking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Walking.class);
                startActivity(intent);
            }
        });
        btn_sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sport.class);
                startActivity(intent);
            }
        });
        btn_rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Rest.class);
                startActivity(intent);
            }
        });
        btn_meditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Meditation.class);
                startActivity(intent);
            }
        });
        btn_eating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Eating.class);
                startActivity(intent);
            }
        });
    }
}