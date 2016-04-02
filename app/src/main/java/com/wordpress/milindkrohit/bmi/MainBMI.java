package com.wordpress.milindkrohit.bmi;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainBMI extends AppCompatActivity implements DialogComm {
    Animation animRotate;
    ImageView mneedle;
    Button mbuttonok;
    double  mWeight, mHeight, age, heightFt, heightIn;
    float mNeedleAngle;
    private String age_input = "", height_input_ft = "", height_input_in = "", weight_input = "";
    private final String Default = "N/A";
    TextView ageTextView, weightTextView, heightTextViewIn, heightTextViewFt,text_2_part;
    ImageButton risk_button, target_button, info_button,edit_button;
    ImageView imageView1,imageView2,gauge_marks;
    String weight,height_in,height_ft,gender,mAge;
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bmi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.needle);
        RelativeLayout l1 = (RelativeLayout)findViewById(R.id.bmiL1);
        ImageView ivBack = new ImageView(this);
        ivBack.setImageResource(R.drawable.gauge_mark);

        l1.addView(ivBack);
        Arc pcc = new Arc (this);
        pcc.setValue(3.5, 6.5, 5, 10, 15);
        Bitmap result = Bitmap.createBitmap(125, 125, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(result);
        pcc.draw(canvas);

        l1.addView(pcc);



        mneedle.setImageBitmap(bmp);

        // checking for the sharedPreferences...

        SharedPreferences sharedPreferences = this.getSharedPreferences("MyData", Context.MODE_PRIVATE);
        mAge = sharedPreferences.getString("Age", Default);
        weight = sharedPreferences.getString("weight", Default);
        height_ft = sharedPreferences.getString("height_ft", Default);
        height_in = sharedPreferences.getString("height_in", Default);
        gender = sharedPreferences.getString("gender", Default);

        if (!(mAge.equals(Default) || weight.equals(Default) || height_in.equals(Default) || height_ft.equals(Default) || gender.equals(Default))) {

            // convert string to doubles.....
            // check for the inch measurement....
            try {
                // Make use of autoboxing.  It's also easier to read.
                age = Double.parseDouble(mAge);
                mWeight = Double.parseDouble(weight);
                heightFt = Double.parseDouble(height_ft);
                heightIn = Double.parseDouble(height_in);
                mHeight = 0.3048 * (heightFt + heightIn);
            } catch (NumberFormatException e) {
                // p did not contain a valid double
            }
            display();
            mNeedleAngle = (float) (mWeight / (mHeight * mHeight));
            turn(mNeedleAngle * 6);
            //showMyDialog(4);
            // display the calcuated value ideal weight...........


        } else {

            showMyDialog(1);
            sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
            mAge = sharedPreferences.getString("Age", Default);
            weight = sharedPreferences.getString("weight", Default);
            height_ft = sharedPreferences.getString("height_ft", Default);
            height_in = sharedPreferences.getString("height_in", Default);
            gender = sharedPreferences.getString("gender", Default);
            try {
                // Make use of autoboxing.  It's also easier to read.
                age = Double.parseDouble(mAge);
                mWeight = Double.parseDouble(weight);
                heightFt = Double.parseDouble(height_ft);
                heightIn = Double.parseDouble(height_in);
                mHeight = 0.3048 * (heightFt + heightIn);

            }catch (NumberFormatException e) {
                // p did not contain a valid double
            }
            display();
        }
        if(gender=="male")
        {
            imageView2.setBackgroundResource(R.drawable.female_light);
            imageView1.setBackgroundResource(R.drawable.male_blue);
        }
        else if(gender=="female")
        {
            imageView2.setBackgroundResource(R.drawable.female_blue);
            imageView1.setBackgroundResource(R.drawable.male_light);
        }

        edit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showMyDialog(1);

            }
        });

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

    public void init() {

        //mbuttonok = (Button) findViewById(R.id.buttonok);
        mneedle = (ImageView) findViewById(R.id.needle);
        risk_button = (ImageButton) findViewById(R.id.risk_button);
        info_button = (ImageButton) findViewById(R.id.info_button);
        target_button = (ImageButton) findViewById(R.id.target_button);
        edit_button=(ImageButton)findViewById(R.id.editImage);
        imageView1=(ImageView)findViewById(R.id.imageView1);
        imageView2=(ImageView)findViewById(R.id.imageView2);
        //gauge_marks = (ImageView)findViewById(R.id.gauge_marks);
        ageTextView = (TextView) findViewById(R.id.age_display);
        heightTextViewFt = (TextView) findViewById(R.id.height_ft_display);
        heightTextViewIn = (TextView) findViewById(R.id.height_in_display);
        weightTextView = (TextView) findViewById(R.id.weight_display);
        text_2_part=(TextView)findViewById(R.id.text_2_part);
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

    public void turn(float degrees) {
        RotateAnimation anim = new RotateAnimation(0, degrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(800);
        anim.setFillEnabled(true);
        anim.setFillAfter(true);
        mneedle.startAnimation(anim);
    }

    @Override
    public void DialogButtonAction(int a) {

    }

    @Override
    public void showinput(String age, String input_weight, String ft, String in, String sex) {
        mAge = age;
        weight=input_weight;
        height_in=in;
        height_ft=ft;
        gender=sex;
        if(gender=="male")
        {
            imageView2.setBackgroundResource(R.drawable.female_light);
            imageView1.setBackgroundResource(R.drawable.male_blue);
        }
        else if(gender=="female")
        {
            imageView2.setBackgroundResource(R.drawable.female_blue);
            imageView1.setBackgroundResource(R.drawable.male_light);
        }


        try {

            mWeight = Double.parseDouble(weight);
            heightFt = Double.parseDouble(height_ft);
            heightIn = Double.parseDouble(height_in);
            mHeight = 0.3048 * (heightFt + heightIn);

        }catch (NumberFormatException e) {
            // p did not contain a valid double
        }
        display();
        mNeedleAngle = (float) (mWeight / (mHeight * mHeight));
        turn(mNeedleAngle * 6);
    }
    public void display() {
        ageTextView.setText(mAge);
        weightTextView.setText(weight);
        heightTextViewIn.setText(height_in);
        heightTextViewFt.setText(height_ft);
        String ideal_weight=String.format("%.2f",20*mHeight*mHeight);   // showing the ideal weight.........
        text_2_part.setText(ideal_weight);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void showMyDialog(int dNum) {
        BMIInput dialogAlert = new BMIInput();
        dialogAlert.setDialogType(dNum);
        if (dNum == 2) {
            dialogAlert.setBmiTable("<8", "18.5-25", "25-30", "30-40", ">40");
        } else if (dNum == 4) {
            double weight_change =(20*mHeight*mHeight) - mWeight;
            String weight=String.format("%.2f",weight_change);
            dialogAlert.setTip(weight, "2-3",true);
        }
        dialogAlert.show(getFragmentManager(), "gameAlert");
    }
}
