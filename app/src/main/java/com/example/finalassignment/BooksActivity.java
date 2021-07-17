package com.example.finalassignment;

import androidx.appcompat.app.ActionBar;
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

    private ActionBar actionBar;
    GridView gridView;

    //1 create list of images, names, and detail
    String[] names = {"Destiny Arrives", "Captain America", "The Gauntlet", "Loki Where Mischief Lies", "Miles Morales", "Thor: An Origin Story"};
    String[] detail;
    int[] images = {R.drawable.img_avg, R.drawable.img_cap, R.drawable.img_iron, R.drawable.img_loki, R.drawable.img_miles, R.drawable.img_thor};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        detail = getResources().getStringArray(R.array.bookslibrary);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Books Library");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        gridView = findViewById(R.id.gridView);

        //6 link between the ui component and datasources
        CustomAdapter customAdapter = new CustomAdapter(names, detail, images, this);

        gridView.setAdapter(customAdapter);

        //
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = names[position];
                String selectedDetail = detail[position];
                int selectedImage = images[position];

                //putExtra pass data name, detail, and image
                startActivity(new Intent(BooksActivity.this, BookDetailsActivity.class).putExtra("name", selectedName).putExtra("detail", selectedDetail).putExtra("image", selectedImage));
            }
        });
    }

    //2 create adapter CustomAdapter
    public class CustomAdapter extends BaseAdapter{

        private String[] imageNames;
        private String[] bookDetail;
        private int[] imagesPhoto;
        private Context context;
        private LayoutInflater layoutInflater;

        //3 constructor
        public CustomAdapter(String[] imageNames, String[] bookDetail, int[] imagesPhoto, Context context) {
            this.imageNames = imageNames;
            this.bookDetail = bookDetail;
            this.imagesPhoto = imagesPhoto;
            this.context = context;
            //Use with getSystemService(java.lang.String) to retrieve a LayoutInflater for inflating layout resources in this context.
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        //4 return length of images
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

        //5 return convertView
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = layoutInflater.inflate(R.layout.row_items, parent, false);
            }

            //Link row_items XML file component to java by calling findViewById() method
            TextView tvName = convertView.findViewById(R.id.tvName);
            ImageView imageView = convertView.findViewById(R.id.imageViewBooks);

            // set imageNames and imagePhoto at position
            tvName.setText(imageNames[position]);
            imageView.setImageResource(imagesPhoto[position]);

            return convertView;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        // this function moves the addlibrary activity to previous activity when back button pressed.
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}