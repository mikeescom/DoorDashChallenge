package com.mikeescom.doordashchallenge.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mikeescom.doordashchallenge.R;
import com.mikeescom.doordashchallenge.utils.SharedPref;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPref.init(this);
    }
}