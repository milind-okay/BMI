package com.wordpress.milindkrohit.bmi;

/**
 * Created by milind on 12/2/16.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class Arc extends View {

    Paint paint;
    Path path;
    float m1, m2, m3, m4, m5, m;

    public Arc(Context context) {
        super(context);
        init();
        setMinimumHeight(100);
        setMinimumWidth(100);
    }

    public Arc(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        setMinimumHeight(100);
        setMinimumWidth(100);
    }

    public Arc(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        setMinimumHeight(100);
        setMinimumWidth(100);
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);

    }

    public void setValue(double x, double y, double z, double p, double q) {
        m1 = (float) (x * 6);
        m2 = (float) (y * 6);
        m3 = (float) (z * 6);
        m4 = (float) (p * 6);
        m5 = 30;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        float rad, x, y;
        x = (float) getWidth() / 2;
        y = (float) getHeight() - ((float) getHeight() / 6);
        if(y <x){
           rad = y - ((float) getHeight() / 4);
        }else{
            rad = x - (x/4);
        }

        final RectF oval = new RectF();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(80.0f);

        float startAngle = 180;
        oval.set(x - rad, y - rad, x + rad, y + rad);
        paint.setColor(Color.BLUE);
        canvas.drawArc(oval, startAngle, m1, false, paint);

        paint.setColor(Color.GREEN);
        startAngle += m1;
        canvas.drawArc(oval, startAngle, m2, false, paint);
        paint.setColor(Color.rgb(144, 102, 3));
        startAngle += m2;
        canvas.drawArc(oval, startAngle, m3, false, paint);
        paint.setColor(Color.rgb(204, 51, 10));
        startAngle += m3;
        canvas.drawArc(oval, startAngle, m4, false, paint);
        paint.setColor(Color.RED);
        startAngle += m4;
        canvas.drawArc(oval, startAngle, 180 - (startAngle - 180), false, paint);

        paint.setColor(Color.WHITE);
        startAngle = 180;
        for (int i = 0; i < 5; i++) {
            startAngle += m5;
            canvas.drawArc(oval, startAngle, 1, false, paint);
        }

    }

}