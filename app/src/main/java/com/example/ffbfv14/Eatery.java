package com.example.ffbfv14;

import android.os.Parcel;
import android.os.Parcelable;

public class Eatery implements Parcelable
{
    String name, desc, url;

    public Eatery(String name, String desc, String url) {
        this.name = name;
        this.desc = desc;
        this.url = url;
    }

    public Eatery() {}

    protected Eatery(Parcel in) {
        name = in.readString();
        desc = in.readString();
        url = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(desc);
        dest.writeString(url);
    }
}
