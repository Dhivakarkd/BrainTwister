package com.example.braintwister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class activity_about extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView icon_link=findViewById(R.id.icon_link);
        if(icon_link != null){
            icon_link.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
}
