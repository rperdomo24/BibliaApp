package com.rperdomo.bibliaapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.rperdomo.bibliaapp.Libros.BooksActivity;
import com.rperdomo.bibliaapp.Utilidades.ClasesUtiles;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // La App esta en ejecución
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            // Explicamos porque necesitamos el permiso
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.INTERNET)) {

                // Acá continuamos el procesos deseado a hacer

            } else {

                // El usuario no necesitas explicación, puedes solicitar el permiso:
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.INTERNET},1);
                //
            }
        }
    }

    public void onClickLibros(View v) {
        String IdTestamento = "";
        Intent intent = new Intent (this, BooksActivity.class);
        if (v.getId() == R.id.imageButton1)
        {
            IdTestamento = getString(R.string.AntiguoTestamento);
        }
        else if(v.getId() == R.id.imageButton2)
        {
            IdTestamento = getString(R.string.NuevoTestamento);
        }
        else
            {
               //Logic
            }
        if (!ClasesUtiles.isNullOrBlank(IdTestamento))
        {
            intent.putExtra(getString(R.string.TipoTestamento), IdTestamento);
        }
        startActivity(intent);
    }
}
