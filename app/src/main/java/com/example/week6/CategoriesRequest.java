package com.example.week6;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private ArrayList<String> categoriesArray = new ArrayList<String>();
    private Callback callbackActivity;

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    // Constructor
    public CategoriesRequest(Context context){
        this.context = context;
    }

    void getCategories(Callback activity){
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories"
                , null, this, this);
        queue.add(jsonObjectRequest);
        callbackActivity = activity;
    }

    // Creates an error message when it's not possible to get the categories
    @Override
    public void onErrorResponse(VolleyError error) {
        callbackActivity.gotCategoriesError(error.getMessage());
    }

    // Save the categories in an arraylist
    @Override
    public void onResponse(JSONObject response) {
        JSONArray jsonArray;
        try {
            jsonArray = response.getJSONArray("categories");

            for (int i = 0; i < jsonArray.length(); i++) {
                categoriesArray.add(jsonArray.getString(i));
            }

            callbackActivity.gotCategories(categoriesArray);
        }
        catch(JSONException e){
                e.printStackTrace();
        }
    }
}
