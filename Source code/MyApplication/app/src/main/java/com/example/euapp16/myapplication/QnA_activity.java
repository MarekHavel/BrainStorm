package com.example.euapp16.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QnA_activity extends AppCompatActivity {

    Button back, reply;
    TextView tvQuestion, tvAnswer;
    Intent intentMain;
    String quest, ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qna_activity);

        /** Finds all required objects in the current View **/
        intentMain = (Intent) getIntent();
        tvQuestion = (TextView) findViewById(R.id.textView_question);
        tvAnswer = (TextView) findViewById(R.id.textView_answers);
        back = (Button) findViewById(R.id.button_Back);
        reply = (Button) findViewById(R.id.button_Reply);

        /** Return to previous activity **/
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        /* TEMPORARY - replace with database question/answer readout */
        quest = (String) intentMain.getStringExtra("question").toString();
        ans = (String) intentMain.getStringExtra("answer").toString();

        tvQuestion.setText(quest);
        tvAnswer.setText(ans);

        reply.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Feature TBD. ETA: November 2k17", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
