package karthik.ex1a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText regNo,dateOfBirth;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login();
    }

    public void login(){

        regNo = (EditText)findViewById(R.id.regNumber);
        dateOfBirth = (EditText)findViewById(R.id.dateOfBirth);
        login = (Button)findViewById(R.id.Login);

        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String reg = regNo.getText().toString();
                String dob = dateOfBirth.getText().toString();
                Intent nextPage = new Intent(v.getContext(),SecondActivity.class);
                nextPage.putExtra("reg",reg);
                nextPage.putExtra("dob",dob);
                startActivity(nextPage);

            }
        });



    }
}
