package com.rperdomo.bibliaapp.Rest.Interface;

import com.rperdomo.bibliaapp.Capitulos.Model.chapter;
import com.rperdomo.bibliaapp.Libros.Model.bible_books;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface IBooks {
    @GET("/api/Biblia/AllBooks")
    Call<List<bible_books>> GetAllBooks();

    @GET("/api/Biblia/AllBooksTestament/{testament}")
    Call<List<bible_books>> AllBooksTestament(@Path("testament") String testament);

    @GET("/api/Biblia/AllChapter/{capitulo}")
    Call<List<chapter>> AllChapter(@Path("capitulo") int capitulo);

    @GET("/api/Biblia/ReadChapter/{idbook}/{chapter}")
    Call<List<chapter>> ReadChapter(@Path("testament") int idbook, @Path("chapter") int chapter);

}
