package com.rperdomo.bibliaapp.Lecturas.Model;

public class List_Verses_items_Card {

    public List_Verses_items_Card(String textVerse, int idVerse) {
        TextVerse = textVerse;
        IdVerse = idVerse;
    }

    public String getTextVerse() {
        return TextVerse;
    }

    public void setTextVerse(String textVerse) {
        TextVerse = textVerse;
    }

    public int getIdVerse() {
        return IdVerse;
    }

    public void setIdVerse(int idVerse) {
        IdVerse = idVerse;
    }

    private String TextVerse;
    private int IdVerse;
}
