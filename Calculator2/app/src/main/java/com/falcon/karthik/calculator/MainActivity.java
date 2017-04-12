package com.falcon.karthik.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    enum Operation {None, Add, Sub, Mul, Div};

    Operation op = Operation.None;
    String num1 = "", num2 = "";

    TextView display;

    int[] buttonNum = {R.id.buttonZero,R.id.buttonOne,R.id.buttonTwo,R.id.buttonThree,R.id.buttonFour,R.id.buttonFive,R.id.buttonSix,R.id.buttonSeven,R.id.buttonEight,R.id.buttonNine,R.id.buttonDot};
    int[] buttonOps = {R.id.buttonAdd,R.id.buttonSub,R.id.buttonMul,R.id.buttonDiv,R.id.buttonClear,R.id.buttonEqual};

    Button[] num = new Button[11];
    Button[] oper = new Button[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = "";
        num2 = "";

        //NumPad
        for(int i = 0; i < buttonNum.length ; i++){

            num[i] = (Button)findViewById(buttonNum[i]);
            num[i].setOnClickListener(this);
        }

        //Operations
        for(int i = 0; i < buttonOps.length ; i++){

            oper[i] = (Button)findViewById(buttonOps[i]);
            oper[i].setOnClickListener(this);
        }
        display = (TextView)findViewById(R.id.disp);
    }
    //apend number
    void appendDigit(int i){
        if(op == Operation.None) num1 += i;
        else num2 += i;

        refreshDisplay();
    }
    //append decimal point
    void appendDot(){
        if(op == Operation.None) {
            if(!num1.contains(".")) num1 += '.';
        }
        else{
            if(!num2.contains(".")) num2 += '.';
        }

        refreshDisplay();
    }


    //Equal to button
    void Evaluate(){

        double n1,n2,result = 0;
        if(op != Operation.None && !num2.isEmpty()){

            n1 = Double.parseDouble(num1);
            n2 = Double.parseDouble(num2);
            switch (op){

                case Add    : result = n1 + n2; break;
                case Sub    : result = n1 - n2; break;
                case Mul    : result = n1 * n2; break;
                case Div    : result = n1 / n2; break;

            }
            num1 = Double.toString(result);
            num2 = "";

            op = Operation.None;
            refreshDisplay();


        }
    }

    //operations
    void setOperation(int i){

        if(!num1.isEmpty()){
            switch(i){

                case 0 : op = Operation.Add;break;
                case 1 : op = Operation.Sub;break;
                case 2 : op = Operation.Mul;break;
                case 3 : op = Operation.Div;break;
            }

        }

        refreshDisplay();

    }

    void refreshDisplay(){

        String print = num1;
        switch(op){

            case Add: print = num1 + " + " + num2; break;
            case Sub: print = num1 + " - " + num2; break;
            case Mul: print = num1 + " * " + num2; break;
            case Div: print = num1 + " / " + num2; break;

        }

        display.setText(print);
    }

    void clear(){

        num1 = "";
        num2 = "";
        op = Operation.None;
        refreshDisplay();

    }
    @Override
    public void onClick(View v) {

        int view = v.getId();

        for(int i = 0 ;i < 10 ; i++){

            if(view == buttonNum[i]){
                appendDigit(i);
            }

        }

        if(view == buttonNum[10]){
            appendDot();
        }

        for(int i = 0;i<4;i++){

            if(view == buttonOps[i]){
                if(op != Operation.None)
                    Evaluate();
                setOperation(i);
            }
        }

        if(view == R.id.buttonEqual){

            Evaluate();
        }
        if(view == R.id.buttonClear){

            clear();
        }



    }
}
