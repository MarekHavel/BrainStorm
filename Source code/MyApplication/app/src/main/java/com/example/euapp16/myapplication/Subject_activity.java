package com.example.euapp16.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Subject_activity extends AppCompatActivity {
    private RecyclerView subjectRecycler;
    private RecyclerView.Adapter subjectAdapter;
    private RecyclerView.LayoutManager subjectLayoutManager;
    RequestQueue subjectqueue;
    Button logoffBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject_activity);

        logoffBtn=findViewById(R.id.button_logout);
        logoffBtn.setOnClickListener(v -> finish());

        ArrayList<SubjectItem> subjectItems = new ArrayList<>();
        subjectItems.add(new SubjectItem(R.drawable.ic_android,"line1","line2"));
        subjectItems.add(new SubjectItem(R.drawable.ic_cast,"line1","line2"));
        subjectItems.add(new SubjectItem(R.drawable.ic_speaker,"line1","line2"));

        subjectRecycler = findViewById(R.id.subjectRecyclerView);
        subjectRecycler.setHasFixedSize(true);
        subjectLayoutManager = new LinearLayoutManager(this);
        subjectAdapter = new subjectAdapter(subjectItems);
        subjectRecycler.setLayoutManager(subjectLayoutManager);
        subjectRecycler.setAdapter(subjectAdapter);

        populateCards();

    }

    private void populateCards() {
        String url = "http://10.0.0.5/eu/YellowTeam/API/subjects.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray messageFromServer = response.getJSONArray("subjects");
                        for(int pos = 0; messageFromServer.length()>pos; pos++){
                            String zobraz = messageFromServer.getJSONObject(pos).toString();
                            Log.d("predmety",zobraz);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);
        subjectqueue.add(request);
    }
}
