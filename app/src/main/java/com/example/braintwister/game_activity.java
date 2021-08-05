package com.example.braintwister;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class game_activity extends AppCompatActivity {
    private static final String TAG = "game_activity";
    CountDownTimer timer;
    int crtAnsLocation;
    TextView questextview, remainingquescount;
    int a, b, questioncount, questionlimit = 10, wrongcount = 0, correctcount = 0;
    Button button0, button1, button2, button3;
    String resultcorrect="",resultwrong ="";
    ArrayList<Integer> answer = new ArrayList<>();
    float textviewsize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_activity);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        remainingquescount = findViewById(R.id.remaininglife_textView);
        remainingquescount.setText(questioncount + " / " + questionlimit);
        questioncount = 0;
        numbergenerator();



        final TextView remainingtime = findViewById(R.id.Remainingtime_textView);
        textviewsize = 30;
        Log.d(TAG, "textview size is " + textviewsize);
        timer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int m = (int) millisUntilFinished / 1000;
                if (m <= 20) {
                    remainingtime.setTextColor(getResources().getColor(R.color.danger_red));
                    if (m % 2 != 0) {
                        remainingtime.setTextSize(TypedValue.COMPLEX_UNIT_SP, textviewsize - 1);
                        remainingtime.setText(m + "S");
                        Log.d(TAG, "onTick: decrease size = " + textviewsize);
                    } else {
                        remainingtime.setTextSize(TypedValue.COMPLEX_UNIT_SP, textviewsize + 1);
                        Log.d(TAG, "onTick: increase size = " + textviewsize);
                        remainingtime.setText(m + "S");
                    }

                } else {
                    remainingtime.setText(m + "S");
                }
            }

            @Override
            public void onFinish() {

               showdialog();

            }
        }.start();

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(game_activity.this);
        alertDialog.setTitle("Quit Match");
        alertDialog.setMessage("Do you want to cancel the match");

        alertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                timer.cancel();
           //     overridePendingTransition(R.anim.swipe_right, R.anim.swipe_left);
            }
        });

        alertDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();


    }

    public void showdialog() {
        resultcorrect =String.valueOf(correctcount);
        resultwrong =String.valueOf(wrongcount);
        finish();
        Intent intent=new Intent(getApplicationContext(),activity_result.class);
        intent.putExtra("correct",resultcorrect);
        intent.putExtra("wrong",resultwrong);
        startActivity(intent);


    }


    public void answertap(View view) {
        if (Integer.toString(crtAnsLocation).equals(view.getTag().toString())) {

            switch (crtAnsLocation) {
                case 0:
                    button0.setSelected(true);
                    break;
                case 1:
                    button1.setSelected(true);
                    break;
                case 2:
                    button2.setSelected(true);
                    break;
                case 3:
                    button3.setSelected(true);
                    break;
            }

            numbergenerator();
            correctcount++;
            remainingquescount.setText(correctcount + " / " + questionlimit);

        } else {
            int wronganslocation = Integer.parseInt(view.getTag().toString());
            vibrate();
            Log.d(TAG, "answertap: " + wronganslocation);
            switch (crtAnsLocation) {
                case 0:
                    button0.setSelected(true);
                    break;
                case 1:
                    button1.setSelected(true);
                    break;
                case 2:
                    button2.setSelected(true);
                    break;
                case 3:
                    button3.setSelected(true);
                    break;
            }
            switch (wronganslocation) {
                case 0:
                    button0.setSelected(false);

                    break;
                case 1:
                    button1.setSelected(false);
                    break;
                case 2:
                    button2.setSelected(false);
                    break;
                case 3:
                    button3.setSelected(false);
                    break;
            }


            numbergenerator();
            wrongcount++;

        }
    }





    public void backgroudset() {
        button0.setBackgroundResource(R.drawable.gamebutton_selector);
        button1.setBackgroundResource(R.drawable.gamebutton_selector);
        button2.setBackgroundResource(R.drawable.gamebutton_selector);
        button3.setBackgroundResource(R.drawable.gamebutton_selector);
    }

    public void numbergenerator() {
        backgroudset();
        if (questioncount < questionlimit) {
            Random rand = new Random();
            a = rand.nextInt(21);
            b = rand.nextInt(21);

            questextview = findViewById(R.id.question_textView);
            questextview.setText(a + " + " + b);
            crtAnsLocation = rand.nextInt(4);
            answer.clear();
            for (int i = 0; i < 4; i++) {
                Log.e(TAG, "numbergenerator: " + i);
                if (i == crtAnsLocation) {
                    answer.add(a + b);
                } else {
                    int wrongans = rand.nextInt(41);
                    Log.e(TAG, "wrong numbergenerator: " + wrongans);
                    while (wrongans == (a + b)) {
                        wrongans = rand.nextInt(41);
                    }
                    answer.add(wrongans);
                }
            }
            button0.setText(Integer.toString(answer.get(0)));
            button1.setText(Integer.toString(answer.get(1)));
            button2.setText(Integer.toString(answer.get(2)));
            button3.setText(Integer.toString(answer.get(3)));
            questioncount++;
        } else {
            questionlimit += 10;
        }
    }
    public void vibrate(){
        Vibrator v=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            v.vibrate(VibrationEffect.createOneShot(200,VibrationEffect.DEFAULT_AMPLITUDE));
        }else {
            v.vibrate(200);
        }
    }

}
