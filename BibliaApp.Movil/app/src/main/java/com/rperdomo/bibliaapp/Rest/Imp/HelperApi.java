package com.rperdomo.bibliaapp.Rest.Imp;
import java.util.List;

import com.rperdomo.bibliaapp.Rest.Interface.IBooks;
import com.rperdomo.bibliaapp.Rest.remote.RetrofitClient;
import retrofit2.Call;
import retrofit2.http.GET;


public class HelperApi {
    public static final String BASE_URL = "http://30d3391d2a50.ngrok.io ";

    public static IBooks GetAllBooks() {
        return RetrofitClient.getClient(BASE_URL).create(IBooks.class);
    }

}
