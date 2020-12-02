package com.example.ffbfv14;
// KLASA NIEUZYWANA, DO USUNIECIA
public class Item {
    private int mImageResource;
    private String mText1, mText2;

    public Item() {
    }

    public Item(int imageResource, String text1, String text2) {
        this.mImageResource = imageResource;
        this.mText1 = text1;
        this.mText2 = text2;
    }

    public int getmImageResource() {
        return mImageResource;
    }
    public String getmText1() {
        return mText1;
    }
    public String getmText2() {
        return mText2;
    }
}
