package com.riberadeltajo.marcos.proyectofindecurso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class InicioActivity extends AppCompatActivity implements View.OnClickListener, Callback {

    ConstraintLayout constraintPvIA;
    ConstraintLayout constraintPvP;
    ImageView btnAtras,ivStats;
    FirebaseAuth mAuth;
    FirebaseController firebase;
    static boolean active = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        mAuth = FirebaseAuth.getInstance();
        firebase = new FirebaseController();
        constraintPvIA = findViewById(R.id.constraintPvIA);
        constraintPvP = findViewById(R.id.constraintPvP);
        ivStats = findViewById(R.id.ivStats);
        btnAtras = findViewById(R.id.ivAtras);
        ivStats.setOnClickListener(this);
        btnAtras.setOnClickListener(this);
        constraintPvP.setOnClickListener(this);
        constraintPvIA.setOnClickListener(this);
        if (mAuth.getCurrentUser() == null){
            ivStats.setVisibility(View.GONE);
        }
        firebase.comprobarRegistros();
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
            firebase.obtenerDatos(this);
        }
    }

    @Override
    public void onCallback(Estadisticas e) {
        if(active) {
            DialogFragment modal = new DialogEstadisticas(e);
            modal.show(getSupportFragmentManager(), "Modal estadisticas");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        active = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        active = false;
    }
}