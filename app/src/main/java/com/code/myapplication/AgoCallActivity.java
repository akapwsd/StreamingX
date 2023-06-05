package com.code.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class AgoCallActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ago_call);
        Button acceptBtn = findViewById(R.id.accept_btn);
        Button disagreeBtn = findViewById(R.id.dis_btn);

        acceptBtn.setOnClickListener(view -> {

        });
        disagreeBtn.setOnClickListener(view -> {

        });
    }
}