package com.rperdomo.bibliaapp.Libros.Model;

public class List_Books_items_Card {

    public List_Books_items_Card(int imagenBook, int idBook, String nameBook) {
        ImagenBook = imagenBook;
        IdBook = idBook;
        NameBook = nameBook;
    }

    public int getImagenBook() {
        return ImagenBook;
    }

    public void setImagenBook(int imagenBook) {
        ImagenBook = imagenBook;
    }

    public String getNameBook() {
        return NameBook;
    }

    public void setNameBook(String nameBook) {
        NameBook = nameBook;
    }

    public int getIdBook() {
        return IdBook;
    }

    public void setIdBook(int idBook) {
        IdBook = idBook;
    }


    private int ImagenBook;
    private String NameBook;
    private int IdBook;
}
