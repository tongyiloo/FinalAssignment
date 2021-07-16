package com.example.finalassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class LibRecordActivity extends AppCompatActivity {

    private ImageView imgView;
    private TextView title, description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lib_record);

        Intent intent = getIntent();
        String lid = intent.getStringExtra("LID");
        String description = intent.getStringExtra("DESCRIPTION");
        String title = intent.getStringExtra("TITLE");


    }
}