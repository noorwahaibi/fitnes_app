package com.example.fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class homeActivity extends AppCompatActivity {

    TextView titlepage, subtitlepage, intropage;

    View divpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        titlepage = (TextView) findViewById(R.id.titlepage);
        subtitlepage =(TextView) findViewById(R.id.subtilepage);
        intropage = (TextView) findViewById(R.id.intropage);

        divpage = (View) findViewById(R.id.divpage);
    }
}