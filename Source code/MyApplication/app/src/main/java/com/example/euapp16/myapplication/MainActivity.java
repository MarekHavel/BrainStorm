package com.example.euapp16.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private RequestQueue loginqueue;
    private EditText editTextName, editTextPasswd;
    private Intent intentMain, intentSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginqueue = Volley.newRequestQueue(this);

        /* Finds all required objects in the current View */
        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonRegister = findViewById(R.id.buttonRegister);
        editTextName = findViewById(R.id.editText);
        editTextPasswd = findViewById(R.id.editText2);

        /* Sends you off to the Registration activity */

        buttonRegister.setOnClickListener(v -> {
            intentMain = new Intent(getApplicationContext(), logon_register.class);
            startActivity(intentMain);
        });
        /* TODO - replace with database login check */
        buttonLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,Subject_activity.class);
            loginParse(editTextName.getText().toString(), editTextPasswd.getText().toString(), intent);
        });
    }

    private void loginParse(String uname, String passwd, Intent intent){
        String url = getString(R.string.server_address) + "/eu/YellowTeam/API/login.php?username=" + uname + "&password="+passwd;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                response -> {
                    try {
                        JSONObject messageFromServer = response.getJSONObject("result");
                        if (messageFromServer.getBoolean("enableLogin")&& uname.equals(messageFromServer.getString("username"))){
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Wrong username or password!",Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);
        loginqueue.add(request);
    }
}
