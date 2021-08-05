package com.example.braintwister;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;


public class gamemode_hard extends AppCompatActivity {
    private static final String TAG = "gamemode_hard";


    CountDownTimer timer;
    int crtAnsLocation;
    TextView questextview, remainingquescount;
    int a, b, c, d, questioncount, questionlimit = 50, wrongcount = 0, correctcount = 0, ans, symbolgenerate;
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8;
    String resultcorrect="",resultwrong ="";
    ArrayList<Integer> answer = new ArrayList<>();
    Random rand = new Random();
    String symbol1 = "", symbol2 = "", symbol3 = "";
    float textviewsize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamemode_hard);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        remainingquescount = findViewById(R.id.remaininglife_textView);
        remainingquescount.setText(questioncount + " / " + questionlimit);
        questioncount = 0;
        numbergenerator();



        final TextView remainingtime = findViewById(R.id.Remainingtime_textView);
        textviewsize =30;
        timer = new CountDownTimer(120000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int m = (int) millisUntilFinished / 1000;
                if (m >= 60) {
                    int min = m / 60;
                    int sec = m - (60 * min);
                    remainingtime.setText(min + " min " + sec + " S ");

                }
                if(m<=20){

                    remainingtime.setTextColor(getResources().getColor(R.color.danger_red));
                    remainingtime.setText(m + "S");
                    if(m%2 != 0){
                        remainingtime.setTextSize(TypedValue.COMPLEX_UNIT_SP,textviewsize-1);
                    }else{
                        remainingtime.setTextSize(TypedValue.COMPLEX_UNIT_SP,textviewsize+1);
                    }
                }else{
                    remainingtime.setText(m+ " S ");
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

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(gamemode_hard.this);
        alertDialog.setTitle("Quit Match");
        alertDialog.setMessage("Do you want to cancel the match");

        alertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                timer.cancel();
        //        overridePendingTransition(R.anim.swipe_right, R.anim.swipe_left);
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
                case 4:
                    button4.setSelected(true);
                    break;
                case 5:
                    button5.setSelected(true);
                    break;
                case 6:
                    button6.setSelected(true);
                    break;
                case 7:
                    button7.setSelected(true);
                    break;
                case 8:
                    button8.setSelected(true);
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
                case 4:
                    button4.setSelected(true);
                    break;
                case 5:
                    button5.setSelected(true);
                    break;
                case 6:
                    button6.setSelected(true);
                    break;
                case 7:
                    button7.setSelected(true);
                    break;
                case 8:
                    button8.setSelected(true);
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
                case 4:
                    button4.setSelected(false);
                    break;
                case 5:
                    button5.setSelected(false);
                    break;
                case 6:
                    button6.setSelected(false);
                    break;
                case 7:
                    button7.setSelected(false);
                    break;
                case 8:
                    button8.setSelected(false);
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
        button4.setBackgroundResource(R.drawable.gamebutton_selector);
        button5.setBackgroundResource(R.drawable.gamebutton_selector);
        button6.setBackgroundResource(R.drawable.gamebutton_selector);
        button7.setBackgroundResource(R.drawable.gamebutton_selector);
        button8.setBackgroundResource(R.drawable.gamebutton_selector);
    }

    public void numbergenerator() {
        backgroudset();
        if (questioncount < questionlimit) {

            a = rand.nextInt(11);
            b = rand.nextInt(11);
            c = rand.nextInt(11);
            d = rand.nextInt(11);
            symbolgenerate = 0;
            symbol1 = SymbolPredictor(1);
            symbolgenerate = symbolgenerate * 10;
            symbol2 = SymbolPredictor(2);
            if (symbolgenerate % 10 == 4) {
                symbolgenerate = symbolgenerate * 10;
                symbol3 = SymbolPredictor(1);
            } else {
                symbolgenerate = symbolgenerate * 10;
                symbol3 = SymbolPredictor(2);
            }
            QuestionGenerator();
            questextview = findViewById(R.id.question_textView);
            questextview.setText("(" + a + symbol1 + b + ")" + symbol2 + "(" + c + symbol3 + d + ")");
            crtAnsLocation = rand.nextInt(4);
            answer.clear();
            for (int i = 0; i < 9; i++) {
                Log.e(TAG, "numbergenerator: " + i);
                if (i == crtAnsLocation) {
                    answer.add(ans);
                } else {
                    int wrongans = rand.nextInt(41);
                    Log.e(TAG, "wrong numbergenerator: " + wrongans);
                    while (wrongans == (ans)) {
                        wrongans = rand.nextInt(41);
                    }
                    answer.add(wrongans);
                }
            }
            button0.setText(String.valueOf(answer.get(0)));
            button1.setText(String.valueOf(answer.get(1)));
            button2.setText(String.valueOf(answer.get(2)));
            button3.setText(String.valueOf(answer.get(3)));
            button4.setText(String.valueOf(answer.get(4)));
            button5.setText(String.valueOf(answer.get(5)));
            button6.setText(String.valueOf(answer.get(6)));
            button7.setText(String.valueOf(answer.get(7)));
            button8.setText(String.valueOf(answer.get(8)));

            questioncount++;
        } else {
            showdialog(2);
        }
    }

    public String SymbolPredictor(int i) {

        int symbolpredict = rand.nextInt(4);
        String symbol = "";
        if (i == 1) {
            switch (symbolpredict) {
                case 0:

                    symbol = " + ";
                    symbolgenerate = symbolgenerate + 1;
                    break;
                case 1:

                    symbol = " - ";
                    symbolgenerate = symbolgenerate + 2;
                    break;
                case 2:

                    symbol = " * ";
                    symbolgenerate = symbolgenerate + 3;
                    break;
                case 3:

                    symbol = " / ";
                    symbolgenerate = symbolgenerate + 4;
                    break;

            }
        } else {
            symbolpredict = rand.nextInt(3);
            switch (symbolpredict) {
                case 0:

                    symbol = " + ";
                    symbolgenerate = symbolgenerate + 1;
                    break;
                case 1:

                    symbol = " - ";
                    symbolgenerate = symbolgenerate + 2;
                    break;
                case 2:

                    symbol = " * ";
                    symbolgenerate = symbolgenerate + 3;
                    break;


            }
        }
        return symbol;

    }

    public void QuestionGenerator() {

        switch (symbolgenerate) {
            case 112:
                ans = (a + b) + (c - d);
                break;
            case 113:
                ans = (a + b) + (c * d);
                break;
            case 114:
                ans = (a + b) + (c / d);
                break;
            case 121:
                ans = (a + b) - (c + d);
                break;
            case 123:
                ans = (a + b) - (c * d);
                break;
            case 124:
                ans = (a + b) - (c / d);
                break;
            case 131:
                ans = (a + b) * (c + d);
                break;
            case 132:
                ans = (a + b) * (c - d);
                break;
            case 133:
                ans = (a + b) * (c * d);
                break;
            case 134:
                ans = (a + b) * (c / d);
                break;
            case 141:
                ans = (a + b) / (c + d);
                break;
            case 142:
                ans = (a + b) / (c - d);
                break;
            case 143:
                ans = (a + b) / (c * d);
                break;
            case 144:
                ans = (a + b) / (c / d);
                break;
            case 211:
                ans = (a - b) + (c + d);
                break;
            case 212:
                ans = (a - b) + (c - d);
                break;
            case 213:
                ans = (a - b) + (c * d);
                break;
            case 214:
                ans = (a - b) + (c / d);
                break;
            case 221:
                ans = (a - b) - (c + d);
                break;
            case 223:
                ans = (a - b) - (c * d);
                break;
            case 224:
                ans = (a - b) - (c / d);
                break;
            case 231:
                ans = (a - b) * (c + d);
                break;
            case 232:
                ans = (a - b) * (c - d);
                break;
            case 233:
                ans = (a - b) * (c * d);
                break;
            case 234:
                ans = (a - b) * (c / d);
                break;
            case 241:
                ans = (a - b) / (c + d);
                break;
            case 242:
                ans = (a - b) / (c - d);
                break;
            case 243:
                ans = (a - b) / (c * d);
                break;
            case 244:
                ans = (a - b) / (c / d);
                break;
            case 311:
                ans = (a * b) + (c + d);
                break;
            case 312:
                ans = (a * b) + (c - d);
                break;
            case 313:
                ans = (a * b) + (c * d);
                break;
            case 314:
                ans = (a * b) + (c / d);
                break;
            case 321:
                ans = (a * b) - (c + d);
                break;
            case 322:
                ans = (a * b) - (c - d);
                break;
            case 323:
                ans = (a * b) - (c * d);
                break;
            case 324:
                ans = (a * b) - (c / d);
                break;
            case 331:
                ans = (a * b) * (c + d);
                break;
            case 332:
                ans = (a * b) * (c - d);
                break;
            case 334:
                ans = (a * b) * (c / d);
                break;
            case 341:
                ans = (a * b) / (c + d);
                break;
            case 342:
                ans = (a * b) / (c - d);
                break;
            case 343:
                ans = (a * b) / (c * d);
                break;
            case 344:
                ans = (a * b) / (c / d);
                break;

            default:
                ans = (a + b) * (c + d);
                break;


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
