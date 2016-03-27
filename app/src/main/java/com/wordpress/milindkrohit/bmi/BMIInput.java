package com.wordpress.milindkrohit.bmi;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by milind on 27/3/16.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class BMIInput extends DialogFragment {
    DialogComm dialogComm;
    String title,displayMessageS;
    int m;
    String tip_weight,tip_str,underweight,normal,overweight,obese,moobese;
    TextView mtip_weight,mtip_str,munderweight,mnormal,moverweight,mobese,mmoobese;
    View view;
    boolean iconType,buttonType;
    EditText mAge,mHeight_ft,mWeight,mHeight_in;

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        dialogComm = (DialogComm) getActivity();
    }
    public void setDialogType(int a){
        m  = a;
    }
    public void setTip(String weight,String months){
        tip_weight = weight;
        tip_str = months;
    }
    public void setBmiTable(String s1,String s2,String s3,String s4,String s5){
        underweight = s1;
        normal = s2;
        overweight = s3;
        obese = s4;
        moobese = s5;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new  AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        if(m==1) {
            view = inflater.inflate(R.layout.bim_inputs, null);

        }else if(m==2){
            view = inflater.inflate(R.layout.bmi_table, null);
        }else if(m==3){
            view = inflater.inflate(R.layout.bmi_message, null);
        }else{
            view = inflater.inflate(R.layout.bmi_tip, null);
        }
      /*   if(buttonType)
            builder.setMessage(displayMessageS);
        else
            builder.setMessage(displayMessage);
        //if(iconType)
        // builder.setIcon(R.drawable.ic_happy);
        // else
        // builder.setIcon(R.drawable.ic_sad);
       if(buttonType){

            builder.setNegativeButton(R.string.replay, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialogComm.DialogButtonAction(1);
                }
            });
            builder.setNeutralButton(R.string.need_more_time, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialogComm.DialogButtonAction(2);
                }
            });
        }else{
            builder.setPositiveButton(R.string.dismiss, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        }
        builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogComm.DialogButtonAction(0);
            }
        });*/
        builder.setView(view);
        Dialog dialog = builder.create();
        return dialog;
    }
/*
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bim_inputs,null);

    }*/
}

interface DialogComm{
    void DialogButtonAction(int a);
}
