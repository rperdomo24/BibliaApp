package com.rperdomo.bibliaapp.Model;

public class Librositem {

    public Librositem(int imageitem, String name) {
        Imageitem = imageitem;
        this.name = name;
    }

    public int getImageitem() {
        return Imageitem;
    }

    public void setImageitem(int imageitem) {
        Imageitem = imageitem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int Imageitem;
    private String name;
}
