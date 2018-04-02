package com.example.euapp16.myapplication;
/*{}*/
public class SubjectItem {
    private int mImageResource;
    private String mText1;
    private String mText2;

    public SubjectItem(int imgRes,String textBig, String textSmall){
        mImageResource=imgRes;
        mText1=textBig;
        mText2=textSmall;
    }

    public int getImageResource(){
        return mImageResource;
    }

    public String getBigText(){
        return mText1;
    }

    public String getTextSmall(){
        return mText2;
    }
}
