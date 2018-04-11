package com.example.euapp16.myapplication;
/*{}*/
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.postViewHolder> {

    private ArrayList<PostItem> mPostItems;

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class postViewHolder extends RecyclerView.ViewHolder{
        TextView txtView1;
        TextView txtView2;
        postViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            txtView1 = itemView.findViewById(R.id.cardPrimaryText);
            txtView2 = itemView.findViewById(R.id.cardSecondaryText);
            itemView.setOnClickListener(view -> {
                if(listener!=null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }

    public PostAdapter(ArrayList<PostItem> subjectList){
        mPostItems =subjectList;
    }

    @NonNull
    @Override
    public postViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_postlist,parent, false);
        return new postViewHolder(v,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull postViewHolder holder, int position) {
        PostItem currentItem = mPostItems.get(position);
        holder.txtView1.setText(currentItem.getBigText());
        holder.txtView2.setText(currentItem.getTextSmall());
    }

    @Override
    public int getItemCount() {
        return mPostItems.size();
    }


}
