package com.recipe.fridge.fridgeapp;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class results extends AppCompatActivity {

    private ArrayList<String> arrayList;
    private String searchTags;
    private StringBuilder searchTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        arrayList = new ArrayList<String>();
        searchTag = new StringBuilder();

        Bundle extras = getIntent().getExtras();
        //Iterate through intent, pass strings into an arrayList and make a comma seperated string list
        if (extras != null)
        {
            for (String key : extras.keySet())
            {
                Object tmp = extras.get(key);
                String val = tmp.toString();
                if (val != null)
                {
                    arrayList.add(val);
                    searchTag.append(val + " ");
                }
            }
            searchTags = searchTag.toString();

            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, searchTags);
            startActivity(intent);
        }

    }
}
