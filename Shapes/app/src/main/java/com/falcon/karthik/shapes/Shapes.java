package com.falcon.karthik.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

public class Shapes extends View {

    private Paint paint;
    private RectF mrect;
    public Shapes(Context context) {
        super(context);

        paint = new Paint();
        paint.setColor(Color.RED);

    }

    public void drawTriangle(Canvas canvas,Paint paint,int x, int y, int width){

        int halfWidth = width / 2;
        Path path  = new Path();
        path.moveTo(x , y - halfWidth);

        path.lineTo(x - halfWidth ,y + halfWidth);
        path.lineTo(x + halfWidth , y + halfWidth);
        path.lineTo(x , y - halfWidth);
        path.close();
        canvas.drawPath(path,paint);
    }

    public void drawRhom(Canvas canvas,Paint paint,int x, int y, int wid){

        int half = wid / 2;
        Path p = new Path();
        p.moveTo(x , y - half);
        p.lineTo(x - half,y);
        p.lineTo(x , y + half);
        p.lineTo(x + half,y);
        p.lineTo(x , y - half);
        p.close();
        canvas.drawPath(p,paint);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float left = 100,top = 100;
        float right = left + 100;
        float bottom = top + 100;

        canvas.drawColor(Color.BLUE);
        canvas.drawRect(left,top,bottom,right,paint);

        mrect =  new RectF(350,50,350,400);

        canvas.drawOval(mrect,paint);
        RectF oval = new RectF();
        oval.set(200,150,450,350);
        canvas.drawArc(oval,0,90,true,paint);
        canvas.drawCircle(150,300,50,paint);
        drawTriangle(canvas,paint,150,500,200);
        drawRhom(canvas,paint,150,750,200);

    }
}
