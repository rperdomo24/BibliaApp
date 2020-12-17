package com.rperdomo.bibliaapp;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickLibros(View v) {
        Intent intent = new Intent (this, LibrosActivity.class);
        startActivityForResult(intent, 0);
    }
}
