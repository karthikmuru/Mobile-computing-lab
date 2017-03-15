package karthik.ex1a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    TextView REG,DOB,NAME,DEPT,YEAR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Intent intent = getIntent();

        String reg = intent.getStringExtra("reg");
        String dob = intent.getStringExtra("dob");
        String name = intent.getStringExtra("name");
        String dept = intent.getStringExtra("dept");
        String year = intent.getStringExtra("year");

        Toast.makeText(getApplicationContext(),reg,Toast.LENGTH_SHORT).show();

        REG = (TextView)findViewById(R.id.tvReg);
        DOB = (TextView)findViewById(R.id.tvDob);
        NAME = (TextView)findViewById(R.id.tvName);
        DEPT = (TextView)findViewById(R.id.tvDept);
        YEAR = (TextView)findViewById(R.id.tvYear);

        REG.setText(reg);
        DOB.setText(dob);
        NAME.setText(name);
        DEPT.setText(dept);
        YEAR.setText(year);


    }
}
