package com.example.finalassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BookDetailsActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tvName, tvbook;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        imageView = findViewById(R.id.imageViewBookD);
        tvName = findViewById(R.id.tvNameD);
        tvbook = findViewById(R.id.tvBookDetail);
        fab = findViewById(R.id.btnAddLib);

        Intent intent = getIntent();
        //set Value
        if(intent.getExtras()!=null){
            String selectedName = intent.getStringExtra("name");
            String selectedDetail = intent.getStringExtra("detail");
            int selectedImage = intent.getIntExtra("image",0);

            tvName.setText(selectedName);
            tvbook.setText(selectedDetail);
            imageView.setImageResource(selectedImage);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create a new activity
                startActivity(new Intent(BookDetailsActivity.this, AddLibraryActivity.class));
            }
        });


    }
}