package com.example.furkankykc.sayitahmin;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private int randomValue;
    private TextView info;
    private int live =3;
    private int approchedVal;
    private ConstraintLayout constraint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        constraint = (ConstraintLayout) findViewById(R.id.constraint);
        info = (TextView) findViewById(R.id.textView);
        seekBar.setProgress(6);
        Random rand = new Random();
        randomValue= rand.nextInt(11);
        changeBackground(R.id.constraint, R.drawable.a1);
        update(5);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                update(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        constraint.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                trigger();
                return false;
            }
        });
    }
    private void trigger(){
        if(approchedVal==randomValue) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (!isFinishing()){
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("You Win")
                                .setMessage("BRrrravo")
                                .setCancelable(false)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                     newgame();
                                    }
                                }).show();
                    }
                }
            });
        } else{
            live--;
            if(live==2)
                changeBackground(R.id.constraint, R.drawable.a3);
            else if(live==1)
                changeBackground(R.id.constraint, R.drawable.a5);
            else {
                changeBackground(R.id.constraint, R.drawable.a7);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (!isFinishing()){
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("You Lose")
                                    .setMessage("The number was "+randomValue)
                                    .setCancelable(false)
                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        newgame();
                                        }
                                    }).show();
                        }
                    }
                });
            }

        }
    }
    private void newgame(){
        Random rand = new Random();
        randomValue= rand.nextInt(11);
        live =3;
        changeBackground(R.id.constraint, R.drawable.a1);
    }
    private void update(int seekVal){
            approchedVal = seekVal;
            info.setText("Tahmininiz : "+approchedVal);
    }
    private void changeBackground(int oId,int bgId){
        Resources res = getResources();
        Drawable drawable = res.getDrawable(bgId);
        View layout = (ViewGroup) findViewById(oId);
        //drawable.setAlpha(20);
        layout.setBackgroundDrawable((drawable));
    }
}
