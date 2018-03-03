package com.recipe.fridge.fridgeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView editText;
    private Button btn;
    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (TextView) findViewById(R.id.searchInst);
        btn = (Button) findViewById(R.id.searchButton);
        list = (ListView) findViewById(R.id.searchTags);
        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);

        list.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {

                arrayList.add(editText.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });
    }
    


}
