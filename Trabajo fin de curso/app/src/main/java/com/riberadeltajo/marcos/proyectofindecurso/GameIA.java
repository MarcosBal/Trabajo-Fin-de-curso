package com.riberadeltajo.marcos.proyectofindecurso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameIA extends AppCompatActivity implements View.OnClickListener {

    Tablero t;
    int numeroFilasYColumnas = 3;
    int movi = 0, pIA = 0, pJ = 0, empates = 0;
    boolean win = false;
    ImageView t1,t2,t3,t4,t5,t6,t7,t8,t9,ivAtras,ivIAIcon,ivPlayerIcon;
    Button btnReplay;
    TextView tvPlayerP,tvIAP,tvNombre;
    FirebaseController firebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_ia);
        firebase = new FirebaseController();
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
        tvIAP = findViewById(R.id.tvIAP);
        ivPlayerIcon = findViewById(R.id.ivPlayerIcon);
        ivIAIcon = findViewById(R.id.ivIAIcon);
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
        String nombre = getIntent().getStringExtra("nombrePlayer");
        if (nombre != null && !nombre.equals("")) {
            tvNombre.setText(nombre);
        }
        String jugador = getIntent().getStringExtra("player");
        if (jugador.equals("x")){
            t.setJugador(Tablero.Ficha.X);
            ivIAIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.o_icon));
            ivPlayerIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.x_icon));
        }else{
            t.setJugador(Tablero.Ficha.O);
            ivIAIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.x_icon));
            ivPlayerIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.o_icon));
        }
        if (t.turno().equals("IA")){
            movimientoIA();
        }
    }


    @Override
    public void onClick(View v) {
        if (!win) {
            if (v.getId() == t1.getId()) {
                if (t.comprobarPosi(0,2)) {
                    t1.setImageDrawable(pintarJugador());
                    movimientoJugador(0, 2);
                }
            }
            if (v.getId() == t2.getId()) {
                if (t.comprobarPosi(1,2)) {
                    t2.setImageDrawable(pintarJugador());
                    movimientoJugador(1, 2);
                }
            }
            if (v.getId() == t3.getId()) {
                if (t.comprobarPosi(2,2)) {
                    t3.setImageDrawable(pintarJugador());
                    movimientoJugador(2, 2);
                }
            }
            if (v.getId() == t4.getId()) {
                if (t.comprobarPosi(0,1)) {
                    t4.setImageDrawable(pintarJugador());
                    movimientoJugador(0, 1);
                }
            }
            if (v.getId() == t5.getId()) {
                if (t.comprobarPosi(1,1)) {
                    t5.setImageDrawable(pintarJugador());
                    movimientoJugador(1, 1);
                }
            }
            if (v.getId() == t6.getId()) {
                if (t.comprobarPosi(2,1)) {
                    t6.setImageDrawable(pintarJugador());
                    movimientoJugador(2, 1);
                }
            }
            if (v.getId() == t7.getId()) {
                if (t.comprobarPosi(0,0)) {
                    t7.setImageDrawable(pintarJugador());
                    movimientoJugador(0, 0);
                }
            }
            if (v.getId() == t8.getId()) {
                if (t.comprobarPosi(1,0)) {
                    t8.setImageDrawable(pintarJugador());
                    movimientoJugador(1, 0);
                }
            }
            if (v.getId() == t9.getId()) {
                if (t.comprobarPosi(2,0)) {
                    t9.setImageDrawable(pintarJugador());
                    movimientoJugador(2, 0);
                }
            }
        }
        if (v.getId() == btnReplay.getId()){
            reiniciar();
        }
        if (v.getId() == ivAtras.getId()){
            onBackPressed();
        }
    }

    private Drawable pintarJugador(){
        if (t.getJugador() == Tablero.Ficha.X){
            return ContextCompat.getDrawable(this, R.drawable.x_icon);
        }else{
            return ContextCompat.getDrawable(this, R.drawable.o_icon);
        }
    }

    private Drawable pintarIA(){
        if (t.getIA() == Tablero.Ficha.X){
            return ContextCompat.getDrawable(this, R.drawable.x_icon);
        }else{
            return ContextCompat.getDrawable(this, R.drawable.o_icon);
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
        movi++;
        t.movimiento(x, y, t.getJugador(), t.getTablero());
        if(!t.comprobarVictoria(t.getTablero(), t.getJugador(), x, y)){
            if (!t.comprobarEmpate(movi)){
                movimientoIA();
            }else{
                Toast.makeText(this, "Has quedado EMPATE", Toast.LENGTH_SHORT).show();
                empates++;
                win = true;
                firebase.actualizarDatos(0,0,1);
            }
        }else{
            Toast.makeText(this, "Has GANADO a la IA", Toast.LENGTH_SHORT).show();
            pJ++;
            tvPlayerP.setText(pJ+"");
            win = true;
            firebase.actualizarDatos(1,0,0);
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
                empates++;
                win = true;
                firebase.actualizarDatos(0,0,1);
            }
        }else{
            Toast.makeText(this, "La IA te ha GANADO", Toast.LENGTH_SHORT).show();
            pIA++;
            tvIAP.setText(pIA+"");
            win = true;
            firebase.actualizarDatos(0,1,0);
        }
    }

    private void pintarIA(Posicion p) {
        if(p.getX() == 0 && p.getY() == 2){
            t1.setImageDrawable(pintarIA());
        }
        if(p.getX() == 1 && p.getY() == 2){
            t2.setImageDrawable(pintarIA());
        }
        if(p.getX() == 2 && p.getY() == 2){
            t3.setImageDrawable(pintarIA());
        }
        if(p.getX() == 0 && p.getY() == 1){
            t4.setImageDrawable(pintarIA());
        }
        if(p.getX() == 1 && p.getY() == 1){
            t5.setImageDrawable(pintarIA());
        }
        if(p.getX() == 2 && p.getY() == 1){
            t6.setImageDrawable(pintarIA());
        }
        if(p.getX() == 0 && p.getY() == 0){
            t7.setImageDrawable(pintarIA());
        }
        if(p.getX() == 1 && p.getY() == 0){
            t8.setImageDrawable(pintarIA());
        }
        if(p.getX() == 2 && p.getY() == 0){
            t9.setImageDrawable(pintarIA());
        }
    }
}