package com.example.allproviders;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Provider_Category extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_category);

        textView = findViewById(R.id.category);

        String name = getIntent().getStringExtra("Name");

        textView.setText(name);


    }
}