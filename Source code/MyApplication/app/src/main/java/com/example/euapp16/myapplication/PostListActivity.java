package com.example.euapp16.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PostListActivity extends AppCompatActivity {
    RequestQueue subjectqueue;
    Button logoffBtn;
    Button addPostBtn;
    ArrayList<PostItem> postItems;
    PostAdapter PostAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postlist);

        subjectqueue = Volley.newRequestQueue(this);

        logoffBtn=findViewById(R.id.button_logout);
        logoffBtn.setOnClickListener(v -> {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.clear();
            editor.apply();
            finish();
        });

        postItems = new ArrayList<>();


        RecyclerView subjectRecycler = findViewById(R.id.subjectRecyclerView);
        subjectRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager subjectLayoutManager = new LinearLayoutManager(this);
        PostAdapter = new PostAdapter(postItems);
        subjectRecycler.setLayoutManager(subjectLayoutManager);
        subjectRecycler.setAdapter(PostAdapter);

        populateCards(PostAdapter);

        PostAdapter.setOnItemClickListener(position -> {
            int postID = postItems.get(position).getID();
            String postName = postItems.get(position).getBigText();
            String postText = postItems.get(position).getTextSmall();
            int postAuthor = postItems.get(position).getAuthor();
            Intent intent = new Intent(getApplicationContext(),SinglePostActivity.class);
            intent.putExtra("id",postID);
            intent.putExtra("name",postName);
            intent.putExtra("text",postText);
            intent.putExtra("userID",postAuthor);
            startActivity(intent);
        });

        addPostBtn = findViewById(R.id.addPostBtn);
        addPostBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddPostActivity.class);
            startActivity(intent);
        });
    }

    private void populateCards(PostAdapter adapter) {
        String url = "http://app16.sspbrno.cz:80/eu/YellowTeam/API/posts.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                response -> {
                    try {
                        JSONArray messageFromServer = response.getJSONArray("posts");
                        for(int pos = 0; messageFromServer.length()>pos; pos++){
                            JSONObject show = messageFromServer.getJSONObject(pos);
                            postItems.add(new PostItem(show.getString("name"),show.getString("text"),show.getInt("id"),show.getInt("user_id")));
                            adapter.notifyItemInserted(pos);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);
        subjectqueue.add(request);
    }
}
