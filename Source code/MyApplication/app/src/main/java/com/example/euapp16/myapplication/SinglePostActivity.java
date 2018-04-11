package com.example.euapp16.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class SinglePostActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    TextView nameTextView;
    TextView textTextView;
    TextView authorTextView;
    ArrayList<CommentItem> commentItems;
    Button addCommentBtn;
    EditText commentEditText;
    int postID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlepost);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        nameTextView = findViewById(R.id.postName);
        textTextView = findViewById(R.id.postText);
        authorTextView = findViewById(R.id.postAuthor);
        addCommentBtn = findViewById(R.id.addCommentButton);
        commentEditText = findViewById(R.id.addCommentEdit);

        Intent intent = getIntent();
        postID = Objects.requireNonNull(intent.getExtras()).getInt("id");
        String postName = Objects.requireNonNull(intent.getExtras()).getString("name");
        String postText = Objects.requireNonNull(intent.getExtras()).getString("text");

        nameTextView.setText(postName);
        textTextView.setText(postText);
        authorTextView.setText("");

        commentItems = new ArrayList<>();

        RecyclerView subjectRecycler = findViewById(R.id.singlePostRecycler);
        subjectRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager subjectLayoutManager = new LinearLayoutManager(this);
        CommentAdapter PostAdapter = new CommentAdapter(commentItems);
        subjectRecycler.setLayoutManager(subjectLayoutManager);
        subjectRecycler.setAdapter(PostAdapter);
        loadPost(postID, PostAdapter);

        addCommentBtn.setOnClickListener(view -> {
            addPost(commentEditText.getText().toString());
            PostAdapter.notifyItemInserted(commentItems.size());
        });
    }

    private void addPost(String text) {JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("user_id", getSharedPreferences("MyPref",MODE_PRIVATE).getInt("userID",0));
            jsonRequest.put("post_id",postID);
            jsonRequest.put("text",text);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = "http://app16.sspbrno.cz:80/eu/YellowTeam/API/addComment.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonRequest,
                response -> {
                    try {
                        Boolean submitted = response.getBoolean("done");
                        if(submitted){
                            commentItems.add(new CommentItem(text, "You!"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);
        requestQueue.add(request);
    }

    private void loadPost(int postID,CommentAdapter adapter) {
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("postID",postID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = "http://app16.sspbrno.cz:80/eu/YellowTeam/API/getPost.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonRequest,
                response -> {
                    try {
                        JSONArray comments = response.getJSONArray("comments");
                        for(int pos = 0; comments.length()>pos; pos++){
                            JSONObject show = comments.getJSONObject(pos);
                            if(postID == show.getInt("post_id")) {
                                commentItems.add(new CommentItem(show.getString("text"), show.getString("username")));
                                adapter.notifyItemInserted(commentItems.size());
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);
        requestQueue.add(request);
    }
}
