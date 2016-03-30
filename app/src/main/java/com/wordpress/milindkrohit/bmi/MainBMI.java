package com.wordpress.milindkrohit.bmi;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainBMI extends AppCompatActivity implements DialogComm{
    Animation animRotate;
    ImageView mneedle;
    Button mbuttonok;
    double mAge,mWeight,mHeight, age;
    float mNeedleAngle;
    private String age_input="",height_input_ft="",height_input_in="",weight_input="";
    private final String Default="N/A";

    ImageButton risk_button,target_button,info_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bmi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        LinearLayout l = (LinearLayout)findViewById(R.id.bmiL);
        RelativeLayout l1 = (RelativeLayout)findViewById(R.id.bmiL1);
        Arc pcc = new Arc (this);
        pcc.setValue(12,2,5,10,15);
        Bitmap result = Bitmap.createBitmap(125, 125, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        pcc.draw(canvas);

        // getting id for the buttons...
        risk_button=(ImageButton)findViewById(R.id.risk_button);
        info_button=(ImageButton)findViewById(R.id.info_button);
        target_button=(ImageButton)findViewById(R.id.target_button);

      // animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
            //    R.anim.rotate);
        l1.addView(pcc);
        // start the animation
       // needle.startAnimation(animRotate);

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_1);
        // Getting width & height of the given image.
        int w = bmp.getWidth();
        int h = bmp.getHeight();

        mneedle.setImageBitmap(bmp);

        // checking for the sharedPreferences...

        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String mAge = sharedPreferences.getString("Age", Default);
        String weight = sharedPreferences.getString("weight", Default);
        String height_ft = sharedPreferences.getString("height_ft", Default);
        String height_in = sharedPreferences.getString("height_in", Default);
        String gender =  sharedPreferences.getString("gender", Default);

        if (!(mAge.equals(Default) || weight.equals(Default) || height_in.equals(Default) || height_ft.equals(Default) || gender.equals(Default))) {

             // convert string to doubles.....
             age = Double.parseDouble(mAge);
            mWeight=Double.parseDouble(weight);
            mHeight= Double.parseDouble(height_ft);  // check for the inch measurement....

            mNeedleAngle = (float) (mWeight / (mHeight * mHeight));
            turn(mNeedleAngle * 6);
            //showMyDialog(4);
            // display the calcuated value ideal weight...........

        } else {
            showMyDialog(1);
        }


        info_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showMyDialog(2);
            }
        });
        target_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showMyDialog(4);
            }
        });
        risk_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showMyDialog(3);
            }
        });


    }
    public void init(){

        //mbuttonok = (Button) findViewById(R.id.buttonok);
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
        age_input=dialogAlert.age_input;
        weight_input=dialogAlert.weight_input;
        height_input_ft=dialogAlert.height_ft;
        height_input_in=dialogAlert.height_in;
    }
}
