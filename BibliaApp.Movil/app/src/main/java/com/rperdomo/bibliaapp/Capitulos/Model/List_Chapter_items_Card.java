package com.rperdomo.bibliaapp.Capitulos.Model;

public class List_Chapter_items_Card {

    public List_Chapter_items_Card(int idChapter, int imagenBook) {
        ImagenBook = imagenBook;
        IdChapter = idChapter;

    }

    public int getIdChapter() {
        return IdChapter;
    }

    public void setIdChapter(int idChapter) {
        IdChapter = idChapter;
    }

    private int IdChapter;

    public int getImagenBook() {
        return ImagenBook;
    }

    public void setImagenBook(int imagenBook) {
        ImagenBook = imagenBook;
    }

    private int ImagenBook;
}
