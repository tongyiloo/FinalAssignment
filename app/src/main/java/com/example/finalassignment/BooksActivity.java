package com.example.finalassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class BooksActivity extends AppCompatActivity {

    GridView gridView;



    String[] names = {"Destiny Arrives", "Captain America", "The Gauntlet", "Loki Where Mischief Lies", "Miles Morales", "Thor: An Origin Story"};
    String[] detail;
    int[] images = {R.drawable.img_avg, R.drawable.img_cap, R.drawable.img_iron, R.drawable.img_loki, R.drawable.img_miles, R.drawable.img_thor};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        detail = getResources().getStringArray(R.array.bookslibrary);

        //detail = new String[]{getString(R.string.string_avg), getString(R.string.test1), getString(R.string.test2), getString(R.string.test3), getString(R.string.test4), getString(R.string.test5),};

        gridView = findViewById(R.id.gridView);

        CustomAdapter customAdapter = new CustomAdapter(names, detail, images, this);

        gridView.setAdapter(customAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = names[position];
                String selectedDetail = detail[position];
                int selectedImage = images[position];

                startActivity(new Intent(BooksActivity.this, BookDetailsActivity.class).putExtra("name", selectedName).putExtra("detail", selectedDetail).putExtra("image", selectedImage));
            }
        });
    }

    public class CustomAdapter extends BaseAdapter{
        private String[] imageNames;
        private String[] bookDetail;
        private int[] imagesPhoto;
        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapter(String[] imageNames, String[] bookDetail, int[] imagesPhoto, Context context) {
            this.imageNames = imageNames;
            this.bookDetail = bookDetail;
            this.imagesPhoto = imagesPhoto;
            this.context = context;
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return imagesPhoto.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = layoutInflater.inflate(R.layout.row_items, parent, false);
            }

            TextView tvName = convertView.findViewById(R.id.tvName);
            ImageView imageView = convertView.findViewById(R.id.imageViewBooks);

            tvName.setText(imageNames[position]);
            imageView.setImageResource(imagesPhoto[position]);

            return convertView;
        }
    }
}