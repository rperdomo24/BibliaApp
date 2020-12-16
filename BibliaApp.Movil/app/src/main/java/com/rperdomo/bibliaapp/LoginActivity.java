package com.rperdomo.bibliaapp;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void onClickRegistrer(View v) {
        Intent intent = new Intent (this, RegistrarActivity.class);
        startActivityForResult(intent, 0);
    }

    public void onClickChangePassword(View v) {
        Intent intent = new Intent (this, RecuperarContrasennaActivity.class);
        startActivityForResult(intent, 0);
    }

    public void onClickLogin(View v) {
        Intent intent = new Intent (this, MainActivity.class);
        startActivityForResult(intent, 0);
    }

}
