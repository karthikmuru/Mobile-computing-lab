package karthik.ex1a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    TextView reg,dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();

        String RegNo = intent.getStringExtra("reg");
        String Dob = intent.getStringExtra("dob");
        Toast.makeText(getApplicationContext(),RegNo,Toast.LENGTH_SHORT).show();
    }
}
