package com.example.rahulpandey.databindingexample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import com.example.rahulpandey.databindingexample.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.view.setHasFixedSize(true);
        mainBinding.view.setLayoutManager(new GridLayoutManager(this, 2));
        mainBinding.setAdapter(new MyAdapter(getApplicationContext()));


    }


}
