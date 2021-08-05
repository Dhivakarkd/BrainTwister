package com.example.braintwister;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;

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

public class gamemode_medium extends AppCompatActivity {
    private static final String TAG = "gamemode_medium";
    Random rand = new Random();

    CountDownTimer timer;
    int crtAnsLocation;
    TextView questextview, remainingquescount;
    int a, b, questioncount, questionlimit = 40, wrongcount = 0, correctcount = 0, ans;
    Button button0, button1, button2, button3;
    String resultcorrect="",resultwrong ="";
    ArrayList<Integer> answer = new ArrayList<>();
    String symbol = "";
    float textviewsize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamemode_medium);
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
        timer = new CountDownTimer(40000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                int m = (int) millisUntilFinished / 1000;
                if (m <= 20) {
                    remainingtime.setTextColor(getResources().getColor(R.color.danger_red));
                    remainingtime.setText(m + "S");
                    if (m % 2 != 0) {
                        remainingtime.setTextSize(TypedValue.COMPLEX_UNIT_SP, textviewsize - 1);

                    } else {
                        remainingtime.setTextSize(TypedValue.COMPLEX_UNIT_SP, textviewsize + 1);
                    }

                } else {
                    remainingtime.setText(m + "S");
                }
            }


            @Override
            public void onFinish() {
                if (correctcount >= 8) {
                    showdialog(2);
                } else if (correctcount <= 7 && correctcount >= 4) {
                    showdialog(1);
                } else {
                    showdialog(3);
                }
            }
        }.start();

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(gamemode_medium.this);
        alertDialog.setTitle("Quit Match");
        alertDialog.setMessage("Do you want to cancel the match");

        alertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                timer.cancel();
                finish();
            //    overridePendingTransition(R.anim.swipe_right, R.anim.swipe_left);
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

    public void backgroudset() {
        button0.setBackgroundResource(R.drawable.gamebutton_selector);
        button1.setBackgroundResource(R.drawable.gamebutton_selector);
        button2.setBackgroundResource(R.drawable.gamebutton_selector);
        button3.setBackgroundResource(R.drawable.gamebutton_selector);
    }

    public void showdialog(int i) {

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


    public void SymbolPredictor() {

        int symbolpredict = 0;
        symbolpredict = rand.nextInt(4);

        switch (symbolpredict) {
            case 0:
                ans = a + b;
                symbol = " + ";
                break;
            case 1:
                ans = a - b;
                symbol = " - ";
                break;
            case 2:
                ans = a * b;
                symbol = " * ";
                break;
            case 3:
                if ((a != 0) && (b != 0)) {
                    ans = a / b;
                } else {
                    ans = 41;
                }
                symbol = " / ";
                break;

        }

    }

    public void numbergenerator() {
        backgroudset();
        if (questioncount < questionlimit) {

            a = rand.nextInt(61);
            b = rand.nextInt(61);
            SymbolPredictor();
            int min = ((ans - 10) + 1) + 10;
            if (min <= 0) {
                min = 10;
            }
            questextview = findViewById(R.id.question_textView);
            questextview.setText(a + symbol + b);
            crtAnsLocation = rand.nextInt(4);
            answer.clear();
            for (int i = 0; i < 4; i++) {
                Log.e(TAG, "numbergenerator: " + i);
                if (i == crtAnsLocation) {
                    answer.add(ans);
                } else {
                    int wrongans = rand.nextInt(min);
                    Log.e(TAG, "wrong numbergenerator: " + wrongans);
                    while (wrongans == (ans)) {
                        wrongans = rand.nextInt(min);
                    }

                    if (ans < 0) {
                        wrongans = wrongans * (-1);
                        answer.add(wrongans);
                    } else {
                        answer.add(wrongans);
                    }
                }
            }
            button0.setText(Integer.toString(answer.get(0)));
            button1.setText(Integer.toString(answer.get(1)));
            button2.setText(Integer.toString(answer.get(2)));
            button3.setText(Integer.toString(answer.get(3)));
            questioncount++;
        } else {
            showdialog(2);
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
