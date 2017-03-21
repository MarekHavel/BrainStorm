package com.example.euapp16.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonLogin, buttonRegister;
    EditText editTextName, editTextPasswd;
    public Intent intentMain, intentSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Finds all required objects in the current View **/
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextName = (EditText) findViewById(R.id.editText);
        editTextPasswd = (EditText) findViewById(R.id.editText2);

        /** Sends you off to the Registration activity **/
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intentMain = new Intent (getApplicationContext(), Register_Activity.class);
                startActivity(intentMain);
            }
        });

        /* TEMPORARY - replace with database login check */
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {String n = editTextName.getText().toString();
                String ph = editTextPasswd.getText().toString();
                String x = "root";
                if (n.equals(x) && ph.equals(x)) {
                    intentSecond = new Intent (getApplicationContext(), Subject_activity.class);
                    startActivity(intentSecond);
                }
                else {
                    Toast.makeText(MainActivity.this, "E-mail or password wrong.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
