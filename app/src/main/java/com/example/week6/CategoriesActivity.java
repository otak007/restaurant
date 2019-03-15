package com.example.week6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    private ListView categoriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // Show the categories of the menu's
        categoriesList = findViewById(R.id.categories);
        new CategoriesRequest(getApplicationContext()).getCategories(this);

        // Call the CategoryClickListener() when a category is clicked
        categoriesList.setOnItemClickListener(new CategoryClickListener());
    }

    // Get the categories that the CategoriesRequest.java found
    @Override
    public void gotCategories(ArrayList<String> categories) {
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1 , categories);
        categoriesList.setAdapter(categoryAdapter);
    }

    // Show an error message when it wasn't possible to get the categories of the internet
    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }
    // Switch to screen of the menu's of that specific category
    public class CategoryClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String category = (String) parent.getItemAtPosition(position);
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("category", category);
            startActivity(intent);
        }
    }
}
