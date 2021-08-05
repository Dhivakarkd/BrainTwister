package com.example.braintwister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Settings extends AppCompatActivity  {
    private static int easy;
    private static int medium;
    private static int hard;
    private static int mode = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public static int getEasy() {
        return easy;
    }

    public static int getMedium() {
        return medium;
    }

    public static int getHard() {
        return hard;
    }

    public static int getMode() {
        return mode;
    }

    public void DisplayToast(String message){
        Toast.makeText(getApplicationContext(),"Game has been updated to "+message+ " Difficulty",Toast.LENGTH_SHORT).show();
    }

    public void mediummode(View view) {
        mode =2;
        DisplayToast("Medium");
        finish();
    }

    public void easymode(View view) {
        mode= 1;
        DisplayToast("Easy");
        finish();

    }

    public void hardmode(View view) {
        mode =3;
        DisplayToast("Hard");
        finish();
    }
}
