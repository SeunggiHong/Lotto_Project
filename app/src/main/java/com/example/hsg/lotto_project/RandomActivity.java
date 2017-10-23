package com.example.hsg.lotto_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RandomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("번호 랜덤생성");
        setContentView(R.layout.activity_random_num);
    }
}
