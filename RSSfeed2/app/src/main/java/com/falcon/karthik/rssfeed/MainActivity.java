package com.falcon.karthik.rssfeed;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ListView rss;
    List headlines, link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rss = (ListView)findViewById(R.id.rssList);
        rss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Uri uri = Uri.parse((String)link.get(position));
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);

            }
        });

        new getRss().execute();

    }

    private class getRss extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {

            headlines = new ArrayList();
            link = new ArrayList();

            try{

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();

                URL url = new URL("http://www.thehindubusinessline.com/?service=rss");

                xpp.setInput(getInputStream(url),"UTF-8");
                boolean inside = false;

                int eventType = xpp.getEventType();

                while(eventType != XmlPullParser.END_DOCUMENT){

                    if(eventType == XmlPullParser.START_TAG){

                        if(xpp.getName().equalsIgnoreCase("item")){
                            inside = true;
                        }
                        else if(xpp.getName().equalsIgnoreCase("title") ){

                            if(inside) headlines.add(xpp.nextText());
                        }
                        else if(xpp.getName().equalsIgnoreCase("link")){
                            if(inside) link.add(xpp.nextText());
                        }

                    }
                    else if(eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")){
                        inside = false;

                    }
                    eventType = xpp.next();
                }
            }
            catch(XmlPullParserException e){
                e.printStackTrace();
            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch(IOException e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,headlines);
            rss.setAdapter(adapter);
        }
    }

    public InputStream getInputStream(URL url){

        try{
            return url.openConnection().getInputStream();
        }
        catch(IOException e){
            return null;
        }

    }
}
