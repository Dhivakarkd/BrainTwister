package com.example.braintwister;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static int mode ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mode = Settings.getMode();
    }

    public void launchgame(View view) {
        mode = Settings.getMode();
        Log.e(TAG, "launchgame: "+ mode);
        Intent intent;
        switch (mode) {

            case 1:
                intent = new Intent(getApplicationContext(), game_activity.class);
                startActivity(intent);
              //  overridePendingTransition(R.anim.swipe_right,R.anim.swipe_left);
                break;
            case 2:
                intent = new Intent(getApplicationContext(), gamemode_medium.class);
                startActivity(intent);
             //   overridePendingTransition(R.anim.swipe_right,R.anim.swipe_left);
                break;
            case 3:
                intent = new Intent(getApplicationContext(), gamemode_hard.class);
                startActivity(intent);
               // overridePendingTransition(R.anim.swipe_right,R.anim.swipe_left);
                break;
        }

    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder backbutton = new AlertDialog.Builder(this);

        backbutton.setTitle("Quit Game");
        backbutton.setMessage("Do you want to quit the game");

        backbutton.setPositiveButton("Quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                MainActivity.super.onBackPressed();
            }
        });
        backbutton.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        backbutton.show();
    }

    public void launchSettings(View view) {
        Intent intent = new Intent(getApplicationContext(), Settings.class);
        startActivity(intent);
        overridePendingTransition(R.anim.swipe_right,R.anim.swipe_left);

    }


    public void Showabout(View view) {
        Intent intent = new Intent(getApplicationContext(), activity_about.class);
        startActivity(intent);
        overridePendingTransition(R.anim.swipe_right,R.anim.swipe_left);
    }
}
