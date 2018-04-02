package com.example.euapp16.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QnA_activity extends AppCompatActivity {

    private Button back, reply;
    private TextView tvQuestion, tvAnswer;
    private Intent intentMain;
    private String quest, ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qna_activity);

        /* Finds all required objects in the current View */
        intentMain = getIntent();
        tvQuestion = findViewById(R.id.textView_question);
        tvAnswer = findViewById(R.id.textView_answers);
        back = findViewById(R.id.button_Back);
        reply = findViewById(R.id.button_Reply);

        /* Return to previous activity */
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        /* TEMPORARY - replace with database question/answer readout */
        quest = intentMain.getStringExtra("question");
        ans = intentMain.getStringExtra("answer");

        tvQuestion.setText(quest);
        tvAnswer.setText(ans);

        reply.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Feature TBD. ETA: November 2k17", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
