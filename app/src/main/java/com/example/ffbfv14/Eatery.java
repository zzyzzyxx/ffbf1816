package com.example.ffbfv14;

import android.os.Parcel;
import android.os.Parcelable;

public class Eatery implements Parcelable
{
    String name, desc, url, type;

    public Eatery(String name, String desc, String url , String type) {
        this.name = name;
        this.desc = desc;
        this.url = url;
        this.type = type;
    }


    public Eatery() {}

    protected Eatery(Parcel in) {
        name = in.readString();
        desc = in.readString();
        url = in.readString();
        type = in.readString();
    }

    public static final Creator<Eatery> CREATOR = new Creator<Eatery>() {
        @Override
        public Eatery createFromParcel(Parcel in) {
            return new Eatery(in);
        }

        @Override
        public Eatery[] newArray(int size) {
            return new Eatery[size];
        }
    };

    public static Creator<Eatery> getCREATOR() {
        return CREATOR;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(desc);
        dest.writeString(url);
        dest.writeString(type);
    }

}
