package com.example.finalassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class BookDetailsActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tvName, tvbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        imageView = findViewById(R.id.imageViewBookD);
        tvName = findViewById(R.id.tvNameD);
        tvbook = findViewById(R.id.tvBookDetail);

        Intent intent = getIntent();

        if(intent.getExtras()!=null){
            String selectedName = intent.getStringExtra("name");
            String selectedDetail = intent.getStringExtra("detail");
            int selectedImage = intent.getIntExtra("image",0);

            tvName.setText(selectedName);
            tvbook.setText(selectedDetail);
            imageView.setImageResource(selectedImage);
        }
        //set Value
    }
}