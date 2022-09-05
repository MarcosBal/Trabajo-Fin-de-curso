package com.riberadeltajo.marcos.proyectofindecurso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Arrays;

public class GameIA extends AppCompatActivity implements View.OnClickListener {

    Tablero t;
    int numeroFilasYColumnas = 3;
    int movi = 0;
    boolean win = false;
    ImageView t1,t2,t3,t4,t5,t6,t7,t8,t9;
    Button btnReplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_ia);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);
        t6 = findViewById(R.id.t6);
        t7 = findViewById(R.id.t7);
        t8 = findViewById(R.id.t8);
        t9 = findViewById(R.id.t9);
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
        empezarJuego(numeroFilasYColumnas);

    }

    private void empezarJuego(int numeroFilasYColumnas){
        t = new Tablero(numeroFilasYColumnas);
        t.setJugador(Tablero.estado.X);
        if (t.turno().equals("IA")){
            movimientoIA();
        }
    }


    @Override
    public void onClick(View v) {
        if (!win) {
            if (v.getId() == t1.getId()) {
                if (t.comprobarPosi(0,2)) {
                    t1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.x_icon));
                    movimientoJugador(0, 2);
                }
            }
            if (v.getId() == t2.getId()) {
                if (t.comprobarPosi(1,2)) {
                    t2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.x_icon));
                    movimientoJugador(1, 2);
                }
            }
            if (v.getId() == t3.getId()) {
                if (t.comprobarPosi(2,2)) {
                    t3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.x_icon));
                    movimientoJugador(2, 2);
                }
            }
            if (v.getId() == t4.getId()) {
                if (t.comprobarPosi(0,1)) {
                    t4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.x_icon));
                    movimientoJugador(0, 1);
                }
            }
            if (v.getId() == t5.getId()) {
                if (t.comprobarPosi(1,1)) {
                    t5.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.x_icon));
                    movimientoJugador(1, 1);
                }
            }
            if (v.getId() == t6.getId()) {
                if (t.comprobarPosi(2,1)) {
                    t6.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.x_icon));
                    movimientoJugador(2, 1);
                }
            }
            if (v.getId() == t7.getId()) {
                if (t.comprobarPosi(0,0)) {
                    t7.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.x_icon));
                    movimientoJugador(0, 0);
                }
            }
            if (v.getId() == t8.getId()) {
                if (t.comprobarPosi(1,0)) {
                    t8.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.x_icon));
                    movimientoJugador(1, 0);
                }
            }
            if (v.getId() == t9.getId()) {
                if (t.comprobarPosi(2,0)) {
                    t9.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.x_icon));
                    movimientoJugador(2, 0);
                }
            }
        }
        if (v.getId() == btnReplay.getId()){
            reiniciar();
        }
    }

    private void reiniciar(){
        win = false;
        movi = 0;
        t1.setImageBitmap(null);
        t2.setImageBitmap(null);
        t3.setImageBitmap(null);
        t4.setImageBitmap(null);
        t5.setImageBitmap(null);
        t6.setImageBitmap(null);
        t7.setImageBitmap(null);
        t8.setImageBitmap(null);
        t9.setImageBitmap(null);
        t.reiniciar();
        if (t.turno().equals("IA")){
            movimientoIA();
        }
    }

    private void movimientoJugador(int x, int y){
        Posicion p;
        movi++;
        t.movimiento(x, y, t.getJugador(), t.getTablero());
        if(!t.comprobarVictoria(t.getTablero(), t.getJugador(), x, y)){
            if (!t.comprobarEmpate(movi)){
                movimientoIA();
            }else{
                Toast.makeText(this, "Has quedado EMPATE", Toast.LENGTH_SHORT).show();
                win = true;
            }
        }else{
            Toast.makeText(this, "Has GANADO a la IA", Toast.LENGTH_SHORT).show();
            win = true;
        }
    }

    private void movimientoIA(){
        movi++;
        Posicion p;
        p = t.movimientoIA(t.getTablero());
        t.movimiento(p.getX(),p.getY(), t.getIA(),t.getTablero());
        pintarIA(p);
        if(!t.comprobarVictoria(t.getTablero(), t.getIA(), p.getX(),p.getY())){
            if (t.comprobarEmpate(movi)){
                Toast.makeText(this, "Has quedado EMPATE", Toast.LENGTH_SHORT).show();
                win = true;
            }
        }else{
            Toast.makeText(this, "La IA te ha GANADO", Toast.LENGTH_SHORT).show();
            win = true;
        }
    }

    private void pintarIA(Posicion p) {
        if(p.getX() == 0 && p.getY() == 2){
            t1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.o_icon));
        }
        if(p.getX() == 1 && p.getY() == 2){
            t2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.o_icon));
        }
        if(p.getX() == 2 && p.getY() == 2){
            t3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.o_icon));
        }
        if(p.getX() == 0 && p.getY() == 1){
            t4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.o_icon));
        }
        if(p.getX() == 1 && p.getY() == 1){
            t5.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.o_icon));
        }
        if(p.getX() == 2 && p.getY() == 1){
            t6.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.o_icon));
        }
        if(p.getX() == 0 && p.getY() == 0){
            t7.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.o_icon));
        }
        if(p.getX() == 1 && p.getY() == 0){
            t8.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.o_icon));
        }
        if(p.getX() == 2 && p.getY() == 0){
            t9.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.o_icon));
        }
    }
}