package com.falcon.karthik.multithreading;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView disp;
    Button click;

    final Handler mHandler = new Handler();
    int iteration;

    protected void startThread(){

        final int itemp = iteration;

        Thread thread = new Thread(){

            @Override
            public void run() {

                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disp.append("Task " + itemp + "Completed!\n");
                    }
                });
            }
        };
        thread.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iteration = 0;

        click = (Button)findViewById(R.id.button);
        disp = (TextView)findViewById(R.id.textView);

        click.setOnClickListener(this);
        disp.append("\n");

    }

    @Override
    public void onClick(View v) {

        if(v == click){

            iteration++;
            startThread();
            disp.append("Task " + iteration + "Started!\n");

        }
    }
}
