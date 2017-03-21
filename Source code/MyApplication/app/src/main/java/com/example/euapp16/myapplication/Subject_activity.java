package com.example.euapp16.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Subject_activity extends AppCompatActivity {

    Intent intentMain;
    Button buttonBack, buttonIT, buttonMath, buttonLanguages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject_activity);

        /** Finds all required objects in the current View **/
        buttonBack = (Button) findViewById(R.id.button_Back);
        buttonIT = (Button) findViewById(R.id.button_IT);
        buttonMath = (Button) findViewById(R.id.button_Q1);
        buttonLanguages = (Button) findViewById(R.id.button_Language);

        /** Return to previous activity **/
        buttonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        /* TEMPORARY - replace with database category readout */
        buttonIT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intentMain = new Intent (getApplicationContext(), IT_activity.class);
                startActivity(intentMain);
            }
        });

        buttonMath.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intentMain = new Intent (getApplicationContext(), Math_Activity.class);
                startActivity(intentMain);
            }
        });

        buttonLanguages.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intentMain = new Intent (getApplicationContext(), Language_activity.class);
                startActivity(intentMain);
            }
        });
    }
}
