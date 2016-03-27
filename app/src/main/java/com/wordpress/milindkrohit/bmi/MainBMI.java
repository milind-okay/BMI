package com.wordpress.milindkrohit.bmi;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainBMI extends AppCompatActivity {
    Animation animRotate;
    ImageButton needle;
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bmi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        LinearLayout l = (LinearLayout)findViewById(R.id.bmiL);
        LinearLayout l1 = (LinearLayout)findViewById(R.id.bmiL1);
        Arc pcc = new Arc (this);
        Bitmap result = Bitmap.createBitmap(125, 125, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        pcc.draw(canvas);
       // animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
        //        R.anim.rotate);
        l1.addView(pcc);
        // start the animation
       // needle.startAnimation(animRotate);


    }
    public void init(){
       // v.findViewById(R.id.vMain);
       // needle = (ImageButton)findViewById(R.id.needle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_bmi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
