package com.example.euapp16.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Language_activity extends AppCompatActivity {

    Button ButtonQ1, ButtonQ2, ButtonQ3, back;
    Intent intentMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_activity);

        /** Finds all required objects in the current View **/
        back = (Button) findViewById(R.id.button_Back);
        ButtonQ1 = (Button) findViewById(R.id.button_Q1);
        ButtonQ2 = (Button) findViewById(R.id.button_Q2);
        ButtonQ3 = (Button) findViewById(R.id.button_Q3);

        /** Return to previous activity **/
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        /* TEMPORARY - replace with database topic readout */
        ButtonQ1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intentMain = new Intent (getApplicationContext(), QnA_activity.class);
                intentMain.putExtra("question", "How is the passive form for the past simple?");
                intentMain.putExtra("answer", "Was/were + past Particle.");
                startActivity(intentMain);
            }
        });

        ButtonQ2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intentMain = new Intent (getApplicationContext(), QnA_activity.class);
                intentMain.putExtra("question", "How would you say\"good night\" in Italian?");
                intentMain.putExtra("answer", "Buonanotte.");
                startActivity(intentMain);
            }
        });

        ButtonQ3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intentMain = new Intent (getApplicationContext(), QnA_activity.class);
                intentMain.putExtra("question", "How would you say \"i love you\" in German?");
                intentMain.putExtra("answer", "Ich liebe dich.");
                startActivity(intentMain);
            }
        });
    }
}
