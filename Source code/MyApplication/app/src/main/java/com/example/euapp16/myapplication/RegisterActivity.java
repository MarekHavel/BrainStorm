package com.example.euapp16.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    RequestQueue registerqueue;
    Button buttonRegister, buttonBack;
    EditText editTextNick, editTextEmail, editTextCountry, editTextPassword, editTextPasswordCheck;
    String passwd, passwdCheck, email, nick, country;
    JSONObject registerRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerqueue = Volley.newRequestQueue(this);

        buttonRegister = findViewById(R.id.buttonRegister);
        buttonBack = findViewById(R.id.register_buttonBack);

        editTextNick = findViewById(R.id.editTextnickname_register);
        editTextEmail = findViewById(R.id.editTextEmail_register);
        editTextPassword = findViewById(R.id.editTextPassword_register);
        editTextPasswordCheck = findViewById(R.id.editTextCheckPassword_register);
        editTextCountry = findViewById(R.id.editTextCountry_register);

        buttonBack.setOnClickListener(view -> finish());

        buttonRegister.setOnClickListener((View view) -> {
            passwd = editTextPassword.getText().toString();
            passwdCheck = editTextPasswordCheck.getText().toString();
            email = editTextEmail.getText().toString();
            nick = editTextNick.getText().toString();
            country = editTextCountry.getText().toString();

            if (!passwd.equals(passwdCheck) || passwd.length()<8)
            {Toast.makeText(RegisterActivity.this, "Passwords not matching or too simple. Try more than 8 characters.", Toast.LENGTH_SHORT).show();}
            else if (email.isEmpty()) {Toast.makeText(RegisterActivity.this, "Email not entered.", Toast.LENGTH_SHORT).show();}
            else if (country.isEmpty()) {Toast.makeText(RegisterActivity.this, "Country not entered", Toast.LENGTH_SHORT).show();}
            else if (nick.isEmpty()) {Toast.makeText(RegisterActivity.this, "Nickname not entered", Toast.LENGTH_SHORT).show();}
            else {
                registerRequest = new JSONObject();
                try {
                    registerRequest.put("username", nick);
                    registerRequest.put("password",passwd);
                    registerRequest.put("country",country);
                    registerRequest.put("email",email);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String url = "http://147.229.242.53/eu/YellowTeam/API/register.php";
                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, registerRequest,
                        response -> {
                            try {
                                if (response.getBoolean("done")){
                                    finish();
                                }
                                else{
                                    Toast.makeText(RegisterActivity.this, "DB error. Try again later.", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        },
                        error -> Log.d("verbose",error.getMessage()));
                registerqueue.add(stringRequest);
            }
        });
    }
}