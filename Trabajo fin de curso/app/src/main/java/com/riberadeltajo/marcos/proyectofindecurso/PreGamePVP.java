package com.riberadeltajo.marcos.proyectofindecurso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class PreGamePVP extends AppCompatActivity implements View.OnClickListener {

    ConstraintLayout btnOk,xLayout,oLayout;
    ImageView btnAtras;
    EditText edNombre, edNombre2;
    String icon = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game_pvp);
        btnOk = findViewById(R.id.btnOk);
        btnAtras = findViewById(R.id.ivAtras);
        xLayout = findViewById(R.id.xLayout);
        oLayout = findViewById(R.id.oLayout);
        edNombre = findViewById(R.id.edNombre);
        edNombre2 = findViewById(R.id.edNombre2);
        btnOk.setOnClickListener(this);
        xLayout.setOnClickListener(this);
        oLayout.setOnClickListener(this);
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
        if (v.getId() == oLayout.getId()){
            xLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.borde_negro));
            icon="o";
            oLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.borde_amarillo));
        }
        if (v.getId() == xLayout.getId()){
            oLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.borde_negro));
            icon="x";
            xLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.borde_amarillo));
        }
    }

    public void irJuego(){
        if (icon != null && !icon.equals("")){
            Intent i = new Intent(this, GamePVP.class);
            i.putExtra("player1",icon);
            i.putExtra("nombreP1", edNombre.getText().toString());
            i.putExtra("nombreP2", edNombre2.getText().toString());
            startActivity(i);
            finish();
        }else{
            Toast.makeText(this, "Tienes que elegir con que figuera jugará el jugador 1", Toast.LENGTH_SHORT).show();
        }
    }
}