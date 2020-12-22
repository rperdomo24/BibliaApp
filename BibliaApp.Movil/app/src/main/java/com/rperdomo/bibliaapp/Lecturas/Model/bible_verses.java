package com.rperdomo.bibliaapp.Lecturas.Model;

public class bible_verses {

    private int idVerse;
    private int idBible;
    private int idBook;
    private int chapter;
    private int verse;
    private String text;

    public int getIdVerse() {
        return idVerse;
    }

    public void setIdVerse(int idVerse) {
        this.idVerse = idVerse;
    }

    public int getIdBible() {
        return idBible;
    }

    public void setIdBible(int idBible) {
        this.idBible = idBible;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public int getVerse() {
        return verse;
    }

    public void setVerse(int verse) {
        this.verse = verse;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
