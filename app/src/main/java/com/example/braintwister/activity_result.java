package com.example.braintwister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Objects;

public class activity_result extends AppCompatActivity {
    private static final String TAG = "activity_result";

    private static int mode;
    String correctresult,wrongresult;
    RatingBar mRatingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        correctresult= Objects.requireNonNull(getIntent().getExtras()).getString("correct");
        wrongresult=Objects.requireNonNull(getIntent().getExtras()).getString("wrong");
        mRatingBar=findViewById(R.id.ratingBar);
        TextView textView=findViewById(R.id.result_display);
        textView.setText("Total number of Correct Answer "+correctresult);

        int correct=Integer.parseInt(correctresult);
        int wrong =Integer.parseInt(wrongresult);
        mode=Settings.getMode();
        switch (mode){
            case 1:
                if(correct>=20)
                    mRatingBar.setNumStars(5);
                if(correct>=15)
                    mRatingBar.setNumStars(4);
                if(correct>=10)
                    mRatingBar.setNumStars(3);
                if(correct>=7)
                    mRatingBar.setNumStars(2);
                if(correct>=3)
                    mRatingBar.setNumStars(1);
                if(correct>=0)
                    mRatingBar.setNumStars(0);
                break;
            case 2:
                if(correct>=30)
                    mRatingBar.setNumStars(5);
                if(correct>=25)
                    mRatingBar.setNumStars(4);
                if(correct>=20)
                    mRatingBar.setNumStars(3);
                if(correct>=10)
                    mRatingBar.setNumStars(2);
                if(correct>=5)
                    mRatingBar.setNumStars(1);
                if(correct>=0)
                    mRatingBar.setNumStars(0);
                break;
            case 3:
                if(correct>=35)
                    mRatingBar.setNumStars(5);
                if(correct>=30)
                    mRatingBar.setNumStars(4);
                if(correct>=25)
                    mRatingBar.setNumStars(3);
                if(correct>=20)
                    mRatingBar.setNumStars(2);
                if(correct>=5)
                    mRatingBar.setNumStars(1);
                if(correct>=0)
                    mRatingBar.setNumStars(0);
                break;
        }



    }

    public void return_mainMenu(View view) {
        Intent intent =new Intent(getApplicationContext(),MainActivity.class);
        finish();
        startActivity(intent);
    }

    public void playagain(View view) {
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
              //  overridePendingTransition(R.anim.swipe_right,R.anim.swipe_left);
                break;
            case 3:
                intent = new Intent(getApplicationContext(), gamemode_hard.class);
                startActivity(intent);
             //   overridePendingTransition(R.anim.swipe_right,R.anim.swipe_left);
                break;
        }
    }
}
