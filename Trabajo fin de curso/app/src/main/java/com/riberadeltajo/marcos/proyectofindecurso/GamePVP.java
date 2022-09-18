package com.riberadeltajo.marcos.proyectofindecurso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GamePVP extends AppCompatActivity implements View.OnClickListener {

    Tablero t;
    int numeroFilasYColumnas = 3;
    int movi = 0, pJ = 0, pJ2 = 0;
    boolean win = false;
    ImageView t1,t2,t3,t4,t5,t6,t7,t8,t9,ivAtras,ivPlayerIcon,ivPlayerIcon2;
    Button btnReplay;
    TextView tvPlayerP,tvNombre2,tvNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_pvp);
        instanciarVistas();
        empezarJuego(numeroFilasYColumnas);
    }

    private void instanciarVistas(){
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);
        t6 = findViewById(R.id.t6);
        t7 = findViewById(R.id.t7);
        t8 = findViewById(R.id.t8);
        t9 = findViewById(R.id.t9);
        tvNombre = findViewById(R.id.tvNombre);
        tvPlayerP = findViewById(R.id.tvPlayerP);
        //tvIAP = findViewById(R.id.tvIAP);
        ivPlayerIcon = findViewById(R.id.ivPlayerIcon);
        //ivIAIcon = findViewById(R.id.ivIAIcon);
        ivAtras = findViewById(R.id.ivAtras);
        btnReplay = findViewById(R.id.replay);
        btnReplay.setOnClickListener(this);
        t1.setOnClickListener(this);
        t2.setOnClickListener(this);
        t3.setOnClickListener(this);
        t4.setOnClickListener(this);
        t5.setOnClickListener(this);
        t6.setOnClickListener(this);
        t7.setOnClickListener(this);
        t8.setOnClickListener(this);
        t9.setOnClickListener(this);
        ivAtras.setOnClickListener(this);
    }

    private void empezarJuego(int numeroFilasYColumnas){
        t = new Tablero(numeroFilasYColumnas);
    }

    @Override
    public void onClick(View view) {

    }
}