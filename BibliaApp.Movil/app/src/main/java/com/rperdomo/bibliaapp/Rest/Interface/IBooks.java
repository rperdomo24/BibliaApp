package com.rperdomo.bibliaapp.Rest.Interface;

import com.rperdomo.bibliaapp.Libros.Model.bible_books;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface IBooks {
    @GET("/api/Biblia/AllBooks")
    Call<List<bible_books>> GetAllBooks();
}
