package com.example.euapp16.myapplication;
/*{}*/
public class CommentItem {
    private String mText1;
    private String mText2;

    public CommentItem(String text, String author){
        mText1=text;
        mText2=author;
    }

    public String getText(){
        return mText1;
    }

    public String getAuthor(){
        return mText2;
    }
}
