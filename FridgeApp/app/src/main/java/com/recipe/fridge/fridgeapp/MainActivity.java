package com.recipe.fridge.fridgeapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText searchBox;
    private Button btn;
    private Button btn1;
    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    private String searchTags;
    private StringBuilder searchTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBox = (EditText) findViewById(R.id.searchBox);
        btn = (Button) findViewById(R.id.searchButton);
        btn1 = (Button) findViewById(R.id.searchTag);
        list = (ListView) findViewById(R.id.searchTags);
        arrayList = new ArrayList<String>();
        searchTag = new StringBuilder();
        searchTags = new String();
        searchTag.append("recipes ");

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
        Context context = getApplicationContext();

        final Toast nullTagToast = Toast.makeText(context, "Please enter an item",Toast.LENGTH_SHORT);
        final Toast nullSearchToast = Toast.makeText(context, "No Items entered",Toast.LENGTH_SHORT);


        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.remove(adapter.getItem(position));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isEmpty(searchBox)) {
                    arrayList.add(searchBox.getText().toString());
                    adapter.notifyDataSetChanged();
                    searchBox.setText("");
                }
                else {
                    nullTagToast.show();
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(MainActivity.this,results.class);
                if (!adapter.isEmpty()) {
                    for (String str : arrayList) {
                        //intent.putExtra(str, str);
                        searchTag.append(str + " ");
                    }
                    searchTags = searchTag.toString();
                    Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                    intent.putExtra(SearchManager.QUERY, searchTags); // query contains search string
                    startActivity(intent);
                    searchTag.setLength(8);
                    //startActivity(intent);
                }
                else {
                    nullSearchToast.show();
                }
            }
        });

    }

    private boolean isEmpty(EditText eText) {
        return eText.getText().toString().trim().length() == 0;

    }
}
