package com.falcon.karthik.sdcard;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button write,read;
    EditText name,content;

    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        write = (Button)findViewById(R.id.write);
        read = (Button)findViewById(R.id.read);
        name = (EditText) findViewById(R.id.fileName);
        content = (EditText)findViewById(R.id.contents);

        write.setOnClickListener(this);
        read.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v == write){

            String fileName = name.getText().toString();
            String fileContents = content.getText().toString();

            try{

                File file = new File("sdcard/" + fileName);
                file.createNewFile();

                FileOutputStream fout = new FileOutputStream(file);
                OutputStreamWriter writer = new OutputStreamWriter(fout);

                writer.append(fileContents);
                toast.makeText(getApplicationContext(),"File Written!",Toast.LENGTH_SHORT).show();

                fout.close();


            }
            catch(IOException e) {
                e.printStackTrace();
            }

        }
        if(v == read){

            toast.makeText(getApplicationContext(),"hello!",Toast.LENGTH_SHORT).show();
            String fileName = name.getText().toString();
            String content = "";
            String buffer = "";

            try{
                File file = new File("/sdcard/ " + fileName);
                FileInputStream fin = new FileInputStream(file);
                BufferedReader reader= new BufferedReader(new InputStreamReader(fin));

                while((content = reader.readLine()) != null){
                    buffer += content + "\n";
                }
                reader.close();

                toast.makeText(getApplicationContext(),"buffer",Toast.LENGTH_LONG);

                AlertDialog.Builder box = new AlertDialog.Builder(this);
                box.setCancelable(true);
                box.setMessage(buffer);
                box.show();


            }
            catch(FileNotFoundException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            toast.makeText(getApplicationContext(),"buffer",Toast.LENGTH_LONG).show();




        }
    }
}
