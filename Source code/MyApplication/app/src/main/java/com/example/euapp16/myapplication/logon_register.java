package com.example.euapp16.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class logon_register extends AppCompatActivity {

    Button buttonRegister, buttonBack;
    EditText editTextNick, editTextEmail, editTextCountry, editTextPassword, editTextPasswordCheck;
    String passwd, passwdCheck, email, nick, country;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon_register);

        buttonRegister = findViewById(R.id.buttonRegister);
        buttonBack = findViewById(R.id.register_buttonBack);

        editTextNick = findViewById(R.id.editTextnickname_register);
        editTextEmail = findViewById(R.id.editTextEmail_register);
        editTextPassword = findViewById(R.id.editTextPassword_register);
        editTextPasswordCheck = findViewById(R.id.editTextCheckPassword_register);
        editTextCountry = findViewById(R.id.editTextCountry_register);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwd = editTextPassword.getText().toString();
                passwdCheck = editTextPasswordCheck.getText().toString();
                email = editTextEmail.getText().toString();
                nick = editTextNick.getText().toString();
                country = editTextCountry.getText().toString();

                if (!passwd.equals(passwdCheck) || passwd.length()<8)
                {Toast.makeText(logon_register.this, "Passwords not matching or too simple. Try more than 8 characters.", Toast.LENGTH_SHORT).show();}
                else if (!email.isEmpty()) {Toast.makeText(logon_register.this, "Email not entered.", Toast.LENGTH_SHORT).show();}
                else if (!country.isEmpty()) {Toast.makeText(logon_register.this, "Country not entered", Toast.LENGTH_SHORT).show();}
                else if (!nick.isEmpty()) {Toast.makeText(logon_register.this, "Nickname not entered", Toast.LENGTH_SHORT).show();}
                else {Toast.makeText(logon_register.this, "not implemented yet", Toast.LENGTH_SHORT).show();} /*TODO*/


            }
        });
    }
    public void pass(){
        /**/
    }
}
