package com.example.week6;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private String clickedCategory;
    private ArrayList<MenuItem> menuArray = new ArrayList<>();
    private Callback callbackActivity;

    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menu);
        void gotMenuError(String message);
    }

    // Constructor
    public MenuRequest(Context context, String clickedCategory) {
        this.context = context;
        this.clickedCategory = clickedCategory;
    }

    // Get the menus of the internet and return them to the activity
    void getMenu(Callback activity){
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu"
                , null, this, this);
        queue.add(jsonObjectRequest);
        callbackActivity = activity;
    }

    // Creates an error message when it's not possible to get the menu's
    @Override
    public void onErrorResponse(VolleyError error) {
        callbackActivity.gotMenuError(error.getMessage());
    }

    // Save the menu's in an arraylist
    @Override
    public void onResponse(JSONObject response) {

        JSONArray menuJSON;

        try {
            menuJSON = response.getJSONArray("items");
            for (int i = 0; i < menuJSON.length(); i++){
                JSONObject menuItemJson = menuJSON.getJSONObject(i);
                String category = menuItemJson.getString("category");
                if (Objects.equals(category, clickedCategory)) {
                    String name = menuItemJson.getString("name");
                    String description = menuItemJson.getString("description");
                    String imageUrl = menuItemJson.getString("image_url");
                    String price = menuItemJson.getString("price");
                    menuArray.add(new MenuItem(name, description, imageUrl, price, category));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        callbackActivity.gotMenu(menuArray);
    }

}

