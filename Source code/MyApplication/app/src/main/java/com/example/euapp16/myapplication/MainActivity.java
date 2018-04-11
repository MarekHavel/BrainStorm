package com.example.euapp16.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
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
            Intent intentMain = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intentMain);
        });

        buttonLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,PostListActivity.class);
            String pass = editTextPasswd.getText().toString();
            editTextPasswd.setText("");
            loginParse(editTextName.getText().toString(), pass, intent);
        });
    }

    private void loginParse(String uname, String passwd, Intent intent){
        JSONObject loginRequest = new JSONObject();
        try {
            loginRequest.put("username",uname);
            loginRequest.put("password",passwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = "http://app16.sspbrno.cz:80/eu/YellowTeam/API/login.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, loginRequest,
                response -> {
                    try {
                        JSONObject messageFromServer = response.getJSONObject("result");
                        if (messageFromServer.getBoolean("enableLogin")&& uname.equals(messageFromServer.getString("username"))){
                            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.clear();
                            editor.putInt("userID",messageFromServer.getInt("id"));
                            editor.putString("username",uname);
                            editor.apply();
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
