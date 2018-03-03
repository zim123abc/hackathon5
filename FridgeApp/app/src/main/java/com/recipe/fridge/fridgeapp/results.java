package com.recipe.fridge.fridgeapp;

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
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);



        Bundle extras = getIntent().getExtras();
        //Iterate through intent, pass strings into an arrayList and make a comma seperated string list
        if (extras != null)
        {
            for (String key : extras.keySet())
            {
                Object tmp = extras.get(key);
                String val = tmp.toString();
                arrayList.add(val);
                searchTag.append(val + ",");
            }
            searchTags = searchTag.toString();
        }
        requestQueue = Volley.newRequestQueue(this);
        String uri = Uri.parse("https://community-food2fork.p.mashape.com/search") .buildUpon() .build().toString();

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //TODO: Implement stuff here
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO: also do stuff here
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<>();
                params.put("X-Mashape-Key", "<API_KEY>");
                params.put("Accept", "text/plain");
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
