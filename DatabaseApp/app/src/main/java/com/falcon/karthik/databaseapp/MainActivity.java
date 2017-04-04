package com.falcon.karthik.databaseapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    Button add,delete,view,viewAll,modify;
    EditText name,roll,dept;
    SQLiteDatabase db;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student (roll VARCHAR(20),name VARCHAR(20),dept VARCHAR(20));");
        //db.execSQL("DELETE FROM student;");

        name = (EditText)findViewById(R.id.name);
        roll = (EditText)findViewById(R.id.roll);
        dept = (EditText)findViewById(R.id.dept);

        add = (Button)findViewById(R.id.add);
        delete = (Button)findViewById(R.id.delete);
        view = (Button)findViewById(R.id.view);
        viewAll = (Button)findViewById(R.id.viewAll);
        modify = (Button)findViewById(R.id.modify);

        add.setOnClickListener(this);
        delete.setOnClickListener(this);
        view.setOnClickListener(this);
        viewAll.setOnClickListener(this);
        modify.setOnClickListener(this);

        toast = new Toast(this);

    }

    @Override
    public void onClick(View v) {

        if(v == add){

            String Name,Dept,Roll;

            Roll = roll.getText().toString().trim();
            Name = name.getText().toString().trim();
            Dept = dept.getText().toString().trim();

            if(Roll.length() ==0 || Name.length() == 0 || Dept.length() == 0)
                toast.makeText(getApplicationContext(),"Enter all fields!",Toast.LENGTH_SHORT).show();
            else{
                db.execSQL("INSERT INTO student VALUES ('" + Roll + "','" + Name + "','" + Dept +"');");
                toast.makeText(getApplicationContext(),"Added!",Toast.LENGTH_SHORT).show();
            }

        }

        if(v == view){

            String Roll;
            Roll = roll.getText().toString().trim();

            Cursor c = db.rawQuery("SELECT * FROM student  WHERE roll='" + Roll +"';",null);

            if(c.moveToFirst()){

                name.setText(c.getString(1));
                dept.setText(c.getString(2));

            }
            else
                toast.makeText(getApplicationContext(),"Record not found!",Toast.LENGTH_SHORT).show();

        }

        if(v == viewAll){

            StringBuffer buffer = new StringBuffer();

            Cursor c = db.rawQuery("SELECT * FROM student;",null);

            if(c.getCount() == 0)
                toast.makeText(getApplicationContext(),"No Records in the databse!",Toast.LENGTH_SHORT);
            else{

                while(c.moveToNext()){

                    buffer.append("Roll : " + c.getString(0) + "\n");
                    buffer.append("Name : " + c.getString(1) + "\n");
                    buffer.append("Department : " + c.getString(2) + "\n\n");

                }

                showMessage("Student Details",buffer.toString());

            }
        }

        if(v == delete){

            String Roll;

            Roll = roll.getText().toString().trim();

            if(Roll.length() == 0)
                toast.makeText(getApplicationContext(),"Enter Roll. No.!",Toast.LENGTH_SHORT).show();
            else{

                db.execSQL("DELETE FROM student WHERE roll='" + Roll + "'");
                toast.makeText(getApplicationContext(),"Deleted!",Toast.LENGTH_SHORT).show();

            }
        }

        if(v == modify){

            String Name,Dept,Roll;
            Roll = roll.getText().toString().trim();
            Name = name.getText().toString().trim();
            Dept = dept.getText().toString().trim();

            Cursor c = db.rawQuery("SELECT * FROM student  WHERE roll='" + Roll +"';",null);

            if(c.moveToFirst()){

                db.execSQL("UPDATE student SET name='"+ Name +"',dept='"+ Dept + "' WHERE roll='"+ Roll+"';");
                toast.makeText(getApplicationContext(),"Updated!",Toast.LENGTH_SHORT).show();

            }
            else
                toast.makeText(getApplicationContext(),"Record not found!",Toast.LENGTH_SHORT).show();

        }

    }

    public void showMessage(String title,String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
}
