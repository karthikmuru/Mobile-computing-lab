package karthik.ex1a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    TextView reg,dob;
    EditText name,dept,year;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        submit = (Button)findViewById(R.id.Next);
        submit.setOnClickListener(this);
        Intent intent = getIntent();

        String RegNo = intent.getStringExtra("reg");
        String Dob = intent.getStringExtra("dob");

        reg = (TextView) findViewById(R.id.tvReg);
        dob = (TextView) findViewById(R.id.tvDob);


        reg.setText(RegNo);
        dob.setText(Dob);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.Next :

                name = (EditText)findViewById(R.id.name);
                dept = (EditText)findViewById(R.id.dept);
                year = (EditText)findViewById(R.id.year);


                Intent disp = new Intent(v.getContext(), ThirdActivity.class);
                disp.putExtra("reg",reg.getText().toString());
                disp.putExtra("dob" , dob.getText().toString());
                disp.putExtra("name" , name.getText().toString());
                disp.putExtra("dept",dept.getText().toString());
                disp.putExtra("year",year.getText().toString());

                startActivity(disp);
                break;

        }
    }
}
