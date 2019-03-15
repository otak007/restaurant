package com.example.week6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MenuItemAdapter extends ArrayAdapter<MenuItem> {
    private ArrayList<MenuItem> menuItems;

    // Constructor
    public MenuItemAdapter(Context context, int resource, ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        menuItems = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent, false);
        }

        // Get all views
        ImageView imageView = convertView.findViewById(R.id.image);
        TextView titleView = convertView.findViewById(R.id.title);
        TextView priceView = convertView.findViewById(R.id.price);

        // Get the menu information
        String imageUrl = menuItems.get(position).getImageUrl();
        String title = menuItems.get(position).getName();
        String price = "\u20ac" + menuItems.get(position).getPrice() + "0";

        // Set the menu information in the right views
        titleView.setText(title);
        priceView.setText(price);
        Picasso.with(getContext()).load(imageUrl).into(imageView);

        return convertView;
    }
}