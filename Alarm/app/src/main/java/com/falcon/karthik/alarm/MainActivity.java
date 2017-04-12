package com.falcon.karthik.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.util.Calendar;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.sql.Time;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TimePicker timePicker ;
    ToggleButton button;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    Toast toast;
    final String sIntent = "com.falcon.karthik.alarm.ALARM_TRIGGERED";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = (TimePicker)findViewById(R.id.timePicker);
        button  = (ToggleButton)findViewById(R.id.toggleButton);

        button.setOnClickListener(this);
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        registerReceiver(alarmTriggered,new IntentFilter(sIntent));

    }

    BroadcastReceiver alarmTriggered = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            toast.makeText(getApplicationContext(),"Wake up! Wake up!", Toast.LENGTH_LONG).show();

            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            if(uri == null){

                uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            }

            Ringtone ringtone = RingtoneManager.getRingtone(context,uri);
            ringtone.play();
            
        }
    };

    @Override
    public void onClick(View v) {

            if(button.isChecked()){

                toast.makeText(getApplicationContext(),"Alarm on",Toast.LENGTH_SHORT).show();
                Calendar calendar= Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,timePicker.getHour());
                calendar.set(Calendar.MINUTE,timePicker.getMinute());
                long time = calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000);

                pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0, new Intent(sIntent),0);

                if(System.currentTimeMillis() > time){

                    time += 1000*60*60*24;
                }

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,time,1000,pendingIntent);

            }
            else{

                toast.makeText(getApplicationContext(),"Alarm Off",Toast.LENGTH_SHORT).show();
                alarmManager.cancel(pendingIntent);
            }

    }
}
