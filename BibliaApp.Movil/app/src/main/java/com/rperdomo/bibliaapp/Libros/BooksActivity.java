package com.rperdomo.bibliaapp.Libros;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.rperdomo.bibliaapp.Libros.Adapter.BooksAdapter;
import com.rperdomo.bibliaapp.Libros.Model.List_Books_items_Card;
import com.rperdomo.bibliaapp.Libros.Model.bible_books;
import com.rperdomo.bibliaapp.R;
import com.rperdomo.bibliaapp.Rest.Imp.HelperApi;
import com.rperdomo.bibliaapp.Rest.Interface.IBooks;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class BooksActivity extends AppCompatActivity {

    private RecyclerView mRecylerView;
    private BooksAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    SearchView sv;
    private IBooks mServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        sv=(SearchView) findViewById(R.id.SearchLibros);
        ArrayList<List_Books_items_Card> ListaLibros = new ArrayList<>();

        mServices = HelperApi.GetAllBooks();

                //ListaLibros.add(new List_Books_items_Card(R.drawable.ic_book_libros,1,"Mateo"));
               // ListaLibros.add(new List_Books_items_Card(R.drawable.ic_book_libros,"Marcos"));
                //ListaLibros.add(new List_Books_items_Card(R.drawable.ic_book_libros,"Lucas"));

        mRecylerView = findViewById(R.id.recyclerView);
        mRecylerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new BooksAdapter(ListaLibros);

        mRecylerView.setLayoutManager(mLayoutManager);
        mRecylerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BooksAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                List_Books_items_Card data = mAdapter.getItem(position);
                Toast.makeText(BooksActivity.this, String.valueOf(data.getIdBook()), Toast.LENGTH_SHORT).show();
            }
        });

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                sv.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        loadAnswers();
    }
    public void loadAnswers() {
        mServices.GetAllBooks().enqueue(new Callback<List<bible_books>>() {
            @Override
            public void onResponse(Call<List<bible_books>> call, Response<List<bible_books>> response) {

                if(response.isSuccessful()) {

                    mAdapter.updateAnswers((ArrayList<bible_books>) response.body());

                    Log.d("MainActivity", "posts loaded from API");
                }else {
                    int statusCode  = response.code();
                    Log.d("MainActivity", String.valueOf(statusCode));
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<List<bible_books>> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");

            }
        });
    }

}
