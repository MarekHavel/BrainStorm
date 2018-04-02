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


public class subjectAdapter extends RecyclerView.Adapter<subjectAdapter.subjectViewHolder> {

    private ArrayList<SubjectItem> mSubjectList;

    public static class subjectViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgView;
        public TextView txtView1;
        public TextView txtView2;
        public subjectViewHolder(View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.img);
            txtView1 = itemView.findViewById(R.id.cardPrimaryText);
            txtView2 = itemView.findViewById(R.id.cardSecondaryText);
        }
    }

    public subjectAdapter(ArrayList<SubjectItem> subjectList){
        mSubjectList=subjectList;
    }

    @NonNull
    @Override
    public subjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_subject,parent, false);
        subjectViewHolder subjectVH = new subjectViewHolder(v);
        return subjectVH;
    }

    @Override
    public void onBindViewHolder(@NonNull subjectViewHolder holder, int position) {
        SubjectItem currentItem = mSubjectList.get(position);

        holder.imgView.setImageResource(currentItem.getImageResource());
        holder.txtView1.setText(currentItem.getBigText());
        holder.txtView2.setText(currentItem.getTextSmall());
    }

    @Override
    public int getItemCount() {
        return mSubjectList.size();
    }


}
