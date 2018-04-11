package com.example.euapp16.myapplication;
/*{}*/
public class PostItem {
    private String mText1;
    private String mText2;
    private int mID;
    private int mAuthor;

    public PostItem(String textBig, String textSmall, int id, int authorid){
        mText1=textBig;
        mText2=textSmall;
        mID=id;
        mAuthor=authorid;
    }

    public String getBigText(){
        return mText1;
    }

    public String getTextSmall(){
        return mText2;
    }

    public int getAuthor(){
        return mAuthor;
    }

    public int getID(){return mID;}
}
