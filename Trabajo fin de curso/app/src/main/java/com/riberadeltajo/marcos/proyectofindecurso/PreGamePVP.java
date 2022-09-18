package com.riberadeltajo.marcos.proyectofindecurso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PreGamePVP extends AppCompatActivity implements View.OnClickListener {

    ConstraintLayout btnOk,xLayout,oLayout;
    ImageView btnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game_pvp);
        btnOk = findViewById(R.id.btnOk);
        btnAtras = findViewById(R.id.ivAtras);
        btnOk.setOnClickListener(this);
        btnAtras.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnOk.getId()){
            irJuego();
        }
        if (v.getId() == btnAtras.getId()){
            onBackPressed();
        }
    }

    public void irJuego(){
        Intent i = new Intent(this, GamePVP.class);
        startActivity(i);
        finish();
    }
}