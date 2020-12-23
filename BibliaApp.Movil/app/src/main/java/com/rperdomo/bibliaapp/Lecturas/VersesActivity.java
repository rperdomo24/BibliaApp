package com.rperdomo.bibliaapp.Lecturas;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.rperdomo.bibliaapp.Capitulos.ChapterActivity;
import com.rperdomo.bibliaapp.Capitulos.Model.chapter;
import com.rperdomo.bibliaapp.Lecturas.Adapter.VersesAdapter;
import com.rperdomo.bibliaapp.Lecturas.Model.List_Verses_items_Card;
import com.rperdomo.bibliaapp.Lecturas.Model.bible_verses;
import com.rperdomo.bibliaapp.Libros.Adapter.BooksAdapter;
import com.rperdomo.bibliaapp.Libros.BooksActivity;
import com.rperdomo.bibliaapp.Libros.Model.List_Books_items_Card;
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

public class VersesActivity extends AppCompatActivity {

    private VersesAdapter mAdapter;
    private RecyclerView mRecylerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private IBooks mServices;
    int IdCapitulos =0;
    int IdLibro = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verses);

       ArrayList<List_Verses_items_Card> ListaVersos = new ArrayList<>();

        mServices = HelperApi.GetAllBooks();

        mRecylerView = findViewById(R.id.recyclerView2);
        mRecylerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new VersesAdapter(ListaVersos);

        mRecylerView.setLayoutManager(mLayoutManager);
        mRecylerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new VersesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                List_Verses_items_Card data = mAdapter.getItem(position);
               // Toast.makeText(VersesActivity.this, String.valueOf(data.getTextVerse()), Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getIntent();

        if (intent != null)
        {
            IdLibro =  parseInt(intent.getStringExtra(getString(R.string.Idbook2)));

            int data123 = intent.getExtras().getInt(getString(R.string.IdCapitulosNew),0);

            IdCapitulos =  data123;

            Toast.makeText(this,String.valueOf(data123), Toast.LENGTH_SHORT).show();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        loadAnswers(IdCapitulos,IdLibro);
    }

    public void loadAnswers(int idCapitulos, int idLibro) {
        mServices.ReadChapter(idLibro,idCapitulos).enqueue(new Callback<List<bible_verses>>() {
            @Override
            public void onResponse(Call<List<bible_verses>> call, Response<List<bible_verses>> response) {
                if(response.isSuccessful()) {

                    mAdapter.updateAnswers((ArrayList<bible_verses>) response.body());

                    Log.e("MainActivity", "posts loaded from API");
                }else {
                    int statusCode  = response.code();
                    Log.e("MainActivity", String.valueOf(statusCode));
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<List<bible_verses>> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
            }
        });
    }
}
