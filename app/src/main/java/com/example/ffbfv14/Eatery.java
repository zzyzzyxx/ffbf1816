package com.example.ffbfv14;

public class Eatery {
    String name, desc, url;

    public Eatery(String name, String desc, String url) {
        this.name = name;
        this.desc = desc;
        this.url = url;
    }

    public Eatery() {}

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
}
