package com.rperdomo.bibliaapp.Capitulos;

import android.content.Intent;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.rperdomo.bibliaapp.Capitulos.Adapter.ChapterAdapter;
import com.rperdomo.bibliaapp.Capitulos.Model.List_Chapter_items_Card;
import com.rperdomo.bibliaapp.Capitulos.Model.chapter;
import com.rperdomo.bibliaapp.Lecturas.VersesActivity;
import com.rperdomo.bibliaapp.Libros.Adapter.BooksAdapter;
import com.rperdomo.bibliaapp.Libros.BooksActivity;
import com.rperdomo.bibliaapp.Libros.Model.List_Books_items_Card;
import com.rperdomo.bibliaapp.Libros.Model.bible_books;
import com.rperdomo.bibliaapp.R;
import com.rperdomo.bibliaapp.Rest.Imp.HelperApi;
import com.rperdomo.bibliaapp.Rest.Interface.IBooks;
import com.rperdomo.bibliaapp.Utilidades.ClasesUtiles;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ChapterActivity extends AppCompatActivity {

    private RecyclerView mRecylerView;
    private ChapterAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private IBooks mServices;
    int _IdBook = 0;
//recyclerViewChapter
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        ArrayList<List_Chapter_items_Card> Lista = new ArrayList<>();

        mServices = HelperApi.GetAllBooks();

        mRecylerView = findViewById(R.id.recyclerView1);
        mRecylerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ChapterAdapter(Lista);

        mRecylerView.setLayoutManager(mLayoutManager);
        mRecylerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ChapterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                List_Chapter_items_Card data = mAdapter.getItem(position);
                //Toast.makeText(ChapterActivity.this, String.valueOf(data.getIdChapter()), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent (ChapterActivity.this, VersesActivity.class);

                int data123= data.getIdChapter();

                intent.putExtra(getString(R.string.IdCapitulosNew), data123);
                intent.putExtra(getString(R.string.Idbook2), String.valueOf(_IdBook));

                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        if (intent != null)
        {
            _IdBook =  parseInt(intent.getStringExtra(getString(R.string.Idbook)));
        }

        loadAnswers(_IdBook);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void loadAnswers(int IdBook) {
        mServices.AllChapter(IdBook).enqueue(new Callback<List<chapter>>() {
            @Override
            public void onResponse(Call<List<chapter>> call, Response<List<chapter>> response) {
                if(response.isSuccessful()) {

                    mAdapter.updateAnswers((ArrayList<chapter>) response.body());

                    Log.e("MainActivity", "posts loaded from API");
                }else {
                    int statusCode  = response.code();
                    Log.e("MainActivity", String.valueOf(statusCode));
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<List<chapter>> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
            }
        });

    }

}
