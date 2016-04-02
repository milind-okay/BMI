package com.wordpress.milindkrohit.bmi;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * Created by milind on 27/3/16.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class BMIInput extends DialogFragment {
    DialogComm dialogComm;
    Dialog dialog;
    String title, displayMessageS;
    int m;
    String tip_weight, tip_str, underweight, normal, overweight, obese, moobese;
    TextView mtip_weight, mtip_str, munderweight, mnormal, moverweight, mobese, mmoobese;
    View view;
    boolean loss_gain;
    EditText mAge, mHeight_ft, mWeight, mHeight_in;
    private Button button;
    private ImageButton img_buttonM, img_buttonF;
    public boolean ismale = true;
    public String age_input = "", weight_input = "", height_ft = "", height_in = "";
    public String gender;

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        dialogComm = (DialogComm) getActivity();
    }

    public void setDialogType(int a) {
        m = a;
    }

    public void setTip(String weight, String months, boolean loss_gain) {
        tip_weight = weight;
        tip_str = months;
        this.loss_gain = loss_gain;
    }

    public void setBmiTable(String s1, String s2, String s3, String s4, String s5) {
        underweight = s1;
        normal = s2;
        overweight = s3;
        obese = s4;
        moobese = s5;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        if (m == 1) {
            view = inflater.inflate(R.layout.bim_inputs, null);
            // set prompts.xml to alertdialog builder
            button = (Button) view.findViewById(R.id.bmiSave);
            img_buttonM = (ImageButton) view.findViewById(R.id.maleButton);
            img_buttonF = (ImageButton) view.findViewById(R.id.femaleButton);

            img_buttonM.setOnClickListener(imgButtonHandlerM);
            img_buttonF.setOnClickListener(imgButtonHandlerF);

            final EditText mAge = (EditText) view
                    .findViewById(R.id.bimAgeInput);
            final EditText mHeight_ft = (EditText) view.findViewById(R.id.bmiHeightInput_ft);
            final EditText mHeight_in = (EditText) view.findViewById(R.id.bmiHeightInput_in);
            final EditText mWeight = (EditText) view.findViewById(R.id.bmiWeightInput);


            builder.setView(view);
            dialog = builder.create();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {


                    age_input = mAge.getText().toString();
                    weight_input = mWeight.getText().toString();
                    height_ft = mHeight_ft.getText().toString();
                    height_in = mHeight_in.getText().toString();
                    save(view);
                    dialog.dismiss();

                }
            });

        } else if (m == 2) {
            view = inflater.inflate(R.layout.bmi_table, null);
            builder.setView(view);
            dialog = builder.create();
        } else if (m == 3) {
            view = inflater.inflate(R.layout.bmi_message, null);
            builder.setView(view);
            dialog = builder.create();
        } else {
            view = inflater.inflate(R.layout.bmi_tip, null);
            TextView weight = (TextView) view.findViewById(R.id.bmi_tip_weight);
            weight.setText(tip_weight + " Kgs");

            builder.setView(view);
            dialog = builder.create();
        }


        return dialog;
    }

    public void save(View view) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyData", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Age", age_input);
        editor.putString("weight", weight_input);
        editor.putString("height_ft", height_ft);
        editor.putString("height_in", height_in);
        if (ismale) {
            editor.putString("gender", "male");
            gender = "male";
        } else {
            editor.putString("gender", "female");
            gender = "female";
        }


        editor.commit();
        //Toast.makeText(getActivity(), "Data Saved Succesfully ", Toast.LENGTH_LONG).show();
    }

    View.OnClickListener imgButtonHandlerM = new View.OnClickListener() {

        public void onClick(View v) {
            if (!ismale) {
                img_buttonM.setBackgroundResource(R.drawable.male_blue);
                ismale = true;
                img_buttonF.setBackgroundResource(R.drawable.female_light);
            }


        }
    };
    View.OnClickListener imgButtonHandlerF = new View.OnClickListener() {

        public void onClick(View v) {
            if (ismale) {
                img_buttonF.setBackgroundResource(R.drawable.female_blue);
                ismale = false;
                img_buttonM.setBackgroundResource(R.drawable.male_light);
            }


        }
    };


    @Override
    public void onDetach() {
        super.onDetach();
        if (m == 1) {
            dialogComm.showinput(age_input.toString(), weight_input.toString(), height_ft.toString(), height_in.toString(), gender.toString());
        }
    }
}


interface DialogComm {
    void DialogButtonAction(int a);

    void showinput(String age, String weight, String ft, String in, String sex);
}
