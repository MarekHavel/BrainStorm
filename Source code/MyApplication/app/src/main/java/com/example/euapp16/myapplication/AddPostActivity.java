package com.example.euapp16.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class AddPostActivity extends AppCompatActivity {
    EditText nameEditText;
    EditText textEditText;
    Button submitButton;
    RequestQueue postQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpost);

        postQueue = Volley.newRequestQueue(getApplicationContext());

        nameEditText = findViewById(R.id.newPostName);
        textEditText = findViewById(R.id.newPostText);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(view -> {
            String newPostName = nameEditText.getText().toString();
            String newPostText = textEditText.getText().toString();
            if (newPostName.equals("")){
                Toast.makeText(getApplicationContext(),"No post name given",Toast.LENGTH_LONG).show();
            }
            else if (newPostText.equals("")){
                Toast.makeText(getApplicationContext(),"No text entered.",Toast.LENGTH_LONG).show();
            }
            else {
                submitText(newPostName,newPostText);
            }
        });
    }

    private void submitText(String name, String text) {
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("postname",name);
            jsonRequest.put("posttext",text);
            jsonRequest.put("postUserID", getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE).getInt("userID",0));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = "http://app16.sspbrno.cz:80/eu/YellowTeam/API/addpost.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonRequest,
                response -> {
                    try {
                        Boolean status = response.getBoolean("done");
                        if(status){

                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"DB error!",Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);

        postQueue.add(request);
    }
}
