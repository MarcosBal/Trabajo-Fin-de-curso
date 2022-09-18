package com.riberadeltajo.marcos.proyectofindecurso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class InicioActivity extends AppCompatActivity implements View.OnClickListener {

    ConstraintLayout constraintPvIA;
    ConstraintLayout constraintPvP;
    ImageView btnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        constraintPvIA = findViewById(R.id.constraintPvIA);
        constraintPvP = findViewById(R.id.constraintPvP);
        btnAtras = findViewById(R.id.ivAtras);
        btnAtras.setOnClickListener(this);
        constraintPvP.setOnClickListener(this);
        constraintPvIA.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == constraintPvIA.getId()){
            Intent i = new Intent(this, PreGameIA.class);
            startActivity(i);
        }
        if (v.getId() == constraintPvP.getId()){
            Intent i = new Intent(this, PreGamePVP.class);
            startActivity(i);
        }
        if (v.getId() == btnAtras.getId()){
            onBackPressed();
        }

    }
}