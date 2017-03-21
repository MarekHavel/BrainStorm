package com.example.euapp16.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register_Activity extends AppCompatActivity {

    Button buttonBack,buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        /** Finds all required objects in the current View **/
        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        /** Return to previous activity **/
        buttonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        /* Temporary - replace with database registration code */
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Feature TBD. ETA: November 2k17", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
