package com.example.week6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback{

    ListView menuListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        String clicked_category = (String) intent.getSerializableExtra("category");

        menuListView = findViewById(R.id.menu);

        // Call to MenuRequest class the get the menu items of given class
        new MenuRequest(getApplicationContext(), clicked_category).getMenu(this);
        menuListView.setOnItemClickListener(new ItemClickListener());

    }

    // Get the menu's that the CategoriesRequest.java found
    @Override
    public void gotMenu(ArrayList<MenuItem> menu) {
        MenuItemAdapter adapter = new MenuItemAdapter(this, R.layout.menu_item, menu);
        menuListView.setAdapter(adapter);
    }

    // Show an error message when it wasn't possible to get the menu's of the internet
    @Override
    public void gotMenuError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Switch to screen of with the details of the menu
    public class ItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            MenuItem item = (MenuItem) parent.getItemAtPosition(position);

            String name = item.getName();
            String price = item.getPrice();
            String url = item.getImageUrl();
            String description = item.getDescription();

            Intent intent = new Intent(MenuActivity.this, DetailActivity.class);

            intent.putExtra("name", name);
            intent.putExtra("price", price);
            intent.putExtra("url", url);
            intent.putExtra("description", description);

            startActivity(intent);
        }
    }
}
