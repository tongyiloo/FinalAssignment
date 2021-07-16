package com.example.finalassignment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MyLibraryActivity extends AppCompatActivity {

    FloatingActionButton fab;
    ActionBar actionBar;
    RecyclerView mRecyclerView;
    DatabaseHelperLibrary databaseHelperLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_library);

        mRecyclerView = findViewById(R.id.recycleView);
        databaseHelperLibrary = new DatabaseHelperLibrary(this);

        showRecord();

        fab = findViewById(R.id.addFabButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MyLibraryActivity.this, AddLibraryActivity.class));

                Intent intent = new Intent(MyLibraryActivity.this, AddLibraryActivity.class);
                intent.putExtra("editMode", false);
                startActivity(intent);
            }
        });
    }

    private void showRecord() {
        //because last added record show on top
        Adapter adapter = new Adapter(MyLibraryActivity.this, databaseHelperLibrary.getAllData(Constants.C_ADD_TIMESTAMP + " DESC"));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showRecord();
    }
}