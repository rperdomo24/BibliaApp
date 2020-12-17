package com.rperdomo.bibliaapp;

import android.app.LauncherActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.rperdomo.bibliaapp.Model.Librositem;

import java.util.ArrayList;

public class LibrosActivity extends AppCompatActivity {
 private RecyclerView mRecylerView;
 private LibrosAdapter mAdapter;
 private RecyclerView.LayoutManager mLayoutManager;
 SearchView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libros);

        sv=(SearchView) findViewById(R.id.SearchLibros);

        ArrayList<Librositem> ListaLibros = new ArrayList<>();
        ListaLibros.add(new Librositem(R.drawable.ic_book_libros,"Mateo"));
        ListaLibros.add(new Librositem(R.drawable.ic_book_libros,"Marcos"));
        ListaLibros.add(new Librositem(R.drawable.ic_book_libros,"Lucas"));

        mRecylerView = findViewById(R.id.recyclerView);
        mRecylerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new LibrosAdapter(ListaLibros);

        mRecylerView.setLayoutManager(mLayoutManager);
        mRecylerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new LibrosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

              Librositem data = mAdapter.getItem(position);
                Toast.makeText(LibrosActivity.this, data.getName(), Toast.LENGTH_SHORT).show();
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
    }
}

