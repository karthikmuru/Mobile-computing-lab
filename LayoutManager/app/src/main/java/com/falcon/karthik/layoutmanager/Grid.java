package com.falcon.karthik.layoutmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class Grid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        final GridView grid = (GridView)findViewById(R.id.gridView);
        grid.setNumColumns(3);
        grid.setPadding(1,1,1,1);

        String[] planets = new String[] {"Mercury", "Venus" , "Earth" , "Mars" , "Jupiter" , "Saturn" , "Uranus" , "Neptune" , "Pluto" , "Krypton" , "Xandar"};

        ArrayAdapter<String> planetList = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,android.R.id.text1,planets);

        grid.setAdapter(planetList);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(),(String)grid.getItemAtPosition(position),Toast.LENGTH_SHORT).show();

            }
        });
    }
}
