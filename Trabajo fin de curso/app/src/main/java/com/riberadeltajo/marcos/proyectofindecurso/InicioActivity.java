package com.riberadeltajo.marcos.proyectofindecurso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class InicioActivity extends AppCompatActivity implements View.OnClickListener {

    ConstraintLayout constraintPvIA;
    ConstraintLayout constraintPvP;
    ImageView btnAtras,ivStats;
    DatabaseReference mRootReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        mRootReference = FirebaseDatabase.getInstance().getReference();
        constraintPvIA = findViewById(R.id.constraintPvIA);
        constraintPvP = findViewById(R.id.constraintPvP);
        ivStats = findViewById(R.id.ivStats);
        btnAtras = findViewById(R.id.ivAtras);
        ivStats.setOnClickListener(this);
        btnAtras.setOnClickListener(this);
        constraintPvP.setOnClickListener(this);
        constraintPvIA.setOnClickListener(this);
        subirDatosFirebase();
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
        if (v.getId() == ivStats.getId()){
            //Enseñar dialogo con estadisticas
        }
    }

    private void subirDatosFirebase(){
        Map<String, Object> datosUsuario = new HashMap<>();
        datosUsuario.put("Partidas Ganadas", 0);
        datosUsuario.put("Partidas Perdidas", 0);
        datosUsuario.put("Partidas Empatadas", 0);
        mRootReference.child("Usuarios").push().setValue(datosUsuario);
    }
}