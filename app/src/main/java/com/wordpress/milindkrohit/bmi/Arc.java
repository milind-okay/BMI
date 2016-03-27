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
    float m1,m2,m3,m4;

    public Arc(Context context) {
        super(context);
        init();
    }

    public Arc(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Arc(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        float rad = 275,x,y;
        x = (float)getWidth()/2;
        y = (float)getHeight()/2;
        m1 = 35;
        final RectF oval = new RectF();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(102.0f);
  /*
   * drawArc(RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint)
   *
   * oval - The bounds of oval used to define the shape and size of the arc
   * startAngle - Starting angle (in degrees) where the arc begins
   * sweepAngle - Sweep angle (in degrees) measured clockwise
   * useCenter - If true, include the center of the oval in the arc, and close it if it is being stroked. This will draw a wedge
   * paint - The paint used to draw the arc
   */
        oval.set(x - rad, y - rad, x + rad, y + rad);
        paint.setColor(Color.BLUE);
        canvas.drawArc(oval, 180, 2 * m1, false, paint);
        paint.setColor(Color.BLACK);
       // m1 = 125+ m1;
        canvas.drawArc(oval,(180 + m1)%360, 1, false, paint);
        paint.setColor(Color.GREEN);
       // m2 += m1;
        canvas.drawArc(oval, (180 + 2*m1 )%360, 2*m1, false, paint);
        paint.setColor(Color.RED);
        // m3 += m2;
        canvas.drawArc(oval,(180 + 4*m1)%360,180 - 4*m1, false, paint);
        paint.setColor(Color.BLACK);
        // m1 = 125+ m1;
        canvas.drawArc(oval,(180 + m1)%360, 1, false, paint);
        paint.setColor(Color.BLACK);
        // m1 = 125+ m1;
        canvas.drawArc(oval,(180 + 2*m1)%360, 1, false, paint);
        paint.setColor(Color.BLACK);
        // m1 = 125+ m1;
        canvas.drawArc(oval,(180 + 3*m1)%360, 1, false, paint);
        paint.setColor(Color.BLACK);
        // m1 = 125+ m1;
        canvas.drawArc(oval,(180 + 4*m1)%360, 1, false, paint);



    }

}