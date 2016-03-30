package com.wordpress.milindkrohit.bmi;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Build;
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
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainBMI extends AppCompatActivity implements DialogComm{
    ImageView mneedle;
    Button mbuttonok;
    double mAge,mWeight,mHeight;
    float mNeedleAngle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bmi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.needle);
        // Getting width & height of the given image.
        int w = bmp.getWidth();
        int h = bmp.getHeight();

        mneedle.setImageBitmap(bmp);
        RelativeLayout l1 = (RelativeLayout)findViewById(R.id.bmiL1);
        Arc pcc = new Arc (this);
        pcc.setValue(3.5,6.5,5,10,15);
        Bitmap result = Bitmap.createBitmap(125, 125, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        pcc.draw(canvas);
      // animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
            //    R.anim.rotate);
        l1.addView(pcc);
        // start the animation
       // needle.startAnimation(animRotate);



        mbuttonok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mNeedleAngle = (float) (mWeight / (mHeight * mHeight)) - 15;
                turn(mNeedleAngle * 6);
                //showMyDialog(4);
            }
        });
        showMyDialog(1);


    }
    public void init(){

        mbuttonok = (Button) findViewById(R.id.buttonok);
        mneedle = (ImageView) findViewById(R.id.needle );
        mWeight = 60;
        mHeight = 1.54;
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
    public void turn(float degrees)
    {


        RotateAnimation anim = new RotateAnimation(0, degrees,Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(300);
        anim.setFillEnabled(true);
        anim.setFillAfter(true);
       mneedle.startAnimation(anim);

    }

    @Override
    public void DialogButtonAction(int a) {

    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void showMyDialog( int dNum){
        BMIInput dialogAlert= new BMIInput();
       dialogAlert.setDialogType(dNum);
        if(dNum == 2){
            dialogAlert.setBmiTable("<8","2-3","2-3","2-3","2-3");
        }else if(dNum == 4){
            dialogAlert.setTip("5.3","2-3");
        }
        dialogAlert.show(getFragmentManager(), "gameAlert");
    }
}
