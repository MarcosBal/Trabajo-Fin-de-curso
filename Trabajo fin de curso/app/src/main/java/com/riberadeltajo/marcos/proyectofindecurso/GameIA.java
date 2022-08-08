package com.riberadeltajo.marcos.proyectofindecurso;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

public class GameIA extends AppCompatActivity {

    Tablero t;
    int numeroFilasYColumnas = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_ia);
        empezarJuego(numeroFilasYColumnas);
    }

    private void empezarJuego(int numeroFilasYColumnas){
        t = new Tablero(numeroFilasYColumnas);
        comprobarTamañoPantalla(t.getFilasColumnas());
    }

    private void comprobarTamañoPantalla(int filasYColunas){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        
    }
}