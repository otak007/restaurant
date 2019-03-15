package com.example.week6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String name = (String) intent.getSerializableExtra("name");
        String url = (String) intent.getSerializableExtra("url");
        String price = (String) intent.getSerializableExtra("price");
        String description = (String) intent.getSerializableExtra("description");

        TextView titleView = findViewById(R.id.title);
        ImageView imageView = findViewById(R.id.image);
        TextView descriptionView = findViewById(R.id.description);
        TextView priceView = findViewById(R.id.price);

        titleView.setText(name);
        descriptionView.setText(description);
        priceView.setText("€"+price +"0");
        // Show the picture of the menu
        Picasso.with(getApplicationContext()).load(url).into(imageView);
    }
}
