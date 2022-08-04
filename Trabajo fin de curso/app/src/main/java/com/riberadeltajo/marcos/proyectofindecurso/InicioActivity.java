package com.riberadeltajo.marcos.proyectofindecurso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InicioActivity extends AppCompatActivity implements View.OnClickListener {

    ConstraintLayout constraintPvIA;
    ConstraintLayout constraintPvP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        constraintPvIA = findViewById(R.id.constraintPvIA);
        constraintPvP = findViewById(R.id.constraintPvP);

        constraintPvP.setOnClickListener(this);
        constraintPvIA.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i = null;
        if (v.getId() == constraintPvIA.getId()){
           i = new Intent(this, GameIA.class);
        }
        startActivity(i);

    }
}