package com.falcon.karthik.layoutmanager;

import android.content.Intent;
import android.os.Debug;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Array of strings containing the list of planets
        String[] planets = new String[] {"Mercury", "Venus" , "Earth" , "Mars" , "Jupiter" , "Saturn" , "Uranus" , "Neptune" , "Pluto" , "Krypton" , "Xandar"};

        //ListView
        //Declaring list view
        final ListView list = (ListView)findViewById(R.id.list);

        //ArrayAdapter declaration
        ArrayAdapter<String> planetList = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,android.R.id.text1,planets);
        //Set content in adapter to the list view
        list.setAdapter(planetList);

        //Toast message when clicked
       list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               String click = (String)list.getItemAtPosition(position);
               Toast.makeText(getApplicationContext(),click,Toast.LENGTH_SHORT).show();
           }
       });

        next = (Button)findViewById(R.id.button);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPage = new Intent(MainActivity.this,Grid.class);
                startActivity(nextPage);
            }
        });



    }
}
