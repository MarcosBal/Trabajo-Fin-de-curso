package com.riberadeltajo.marcos.proyectofindecurso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GamePVP extends AppCompatActivity implements View.OnClickListener {

    Tablero t;
    int numeroFilasYColumnas = 3;
    int movi = 0, pJ = 0, pJ2 = 0, empates = 0;
    boolean win = false;
    String turno, nombre, nombre2;
    ConstraintLayout xLayout,oLayout;
    ImageView t1,t2,t3,t4,t5,t6,t7,t8,t9,ivAtras,ivPlayerIcon,ivPlayerIcon2;
    Button btnReplay;
    TextView tvPlayerP, tvPlayerP2, tvNombre2, tvNombre;
    FirebaseController firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_pvp);
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
        xLayout = findViewById(R.id.xLayout);
        oLayout = findViewById(R.id.oLayout);
        tvNombre = findViewById(R.id.tvNombre);
        tvPlayerP = findViewById(R.id.tvPlayerP);
        tvPlayerP2 = findViewById(R.id.tvPlayerP2);
        tvNombre2 = findViewById(R.id.tvNombre2);
        ivPlayerIcon = findViewById(R.id.ivPlayerIcon);
        ivPlayerIcon2 = findViewById(R.id.ivIAIcon);
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
        nombre = getIntent().getStringExtra("nombreP1");
        if (nombre != null && !nombre.equals("")) {
            tvNombre.setText(nombre);
        }
        nombre2 = getIntent().getStringExtra("nombreP2");
        if (nombre2 != null && !nombre2.equals("")) {
            tvNombre2.setText(nombre2);
        }
        String jugador = getIntent().getStringExtra("player1");
        if (jugador.equals("x")){
            t.setJugador(Tablero.Ficha.X);
            t.setJugador2(Tablero.Ficha.O);
            ivPlayerIcon2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.o_icon));
            ivPlayerIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.x_icon));
        }else{
            t.setJugador(Tablero.Ficha.O);
            t.setJugador2(Tablero.Ficha.X);
            ivPlayerIcon2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.x_icon));
            ivPlayerIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.o_icon));
        }
        if (t.turnoJugadores().equals("J1")){
            turno = "J1";
            xLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.borde_amarillo));
            oLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.borde_negro));
        }else{
            turno = "J2";
            xLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.borde_negro));
            oLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.borde_amarillo));
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnReplay.getId()){
            reiniciar();
        }
        if (v.getId() == ivAtras.getId()){
            onBackPressed();
        }

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
    }

    private Drawable pintarJugador(){
        if (turno.equals("J1")) {
            if (t.getJugador() == Tablero.Ficha.X) {
                return ContextCompat.getDrawable(this, R.drawable.x_icon);
            } else {
                return ContextCompat.getDrawable(this, R.drawable.o_icon);
            }
        }else{
            if (t.getJugador2() == Tablero.Ficha.X) {
                return ContextCompat.getDrawable(this, R.drawable.x_icon);
            } else {
                return ContextCompat.getDrawable(this, R.drawable.o_icon);
            }
        }
    }

    private void movimientoJugador(int x, int y){
        movi++;
        if (turno.equals("J1")){
            turno = t.cambiarTurno("J1");
            PintarTurno();
            t.movimiento(x, y, t.getJugador(), t.getTablero());
            if(!t.comprobarVictoria(t.getTablero(), t.getJugador(), x, y)){
                if (t.comprobarEmpate(movi)){
                    empates++;
                    Toast.makeText(this, "Has quedado EMPATE", Toast.LENGTH_SHORT).show();
                    win = true;
                    firebase.actualizarDatos(pJ,pJ2,empates);
                }
            }else{
                if (nombre!= null && !nombre.equals("") && nombre2!= null && !nombre2.equals("")) {
                    Toast.makeText(this, nombre + " ha GANADO a " + nombre2, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Jugador1 ha GANADO a Jugador2", Toast.LENGTH_SHORT).show();
                }
                pJ++;
                tvPlayerP.setText(pJ+"");
                win = true;
                firebase.actualizarDatos(1,0,0);
            }
        }else {
            turno = t.cambiarTurno("J2");
            PintarTurno();
            t.movimiento(x, y, t.getJugador2(), t.getTablero());
            if(!t.comprobarVictoria(t.getTablero(), t.getJugador2(), x, y)){
                if (t.comprobarEmpate(movi)){
                    empates++;
                    Toast.makeText(this, "Habeis quedado EMPATE", Toast.LENGTH_SHORT).show();
                    win = true;
                    firebase.actualizarDatos(0,0,1);
                }
            }else{
                if (nombre!= null && !nombre.equals("") && nombre2!= null && !nombre2.equals("")) {
                    Toast.makeText(this, nombre2 + " ha GANADO a " + nombre, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Jugador2 ha GANADO a Jugador1", Toast.LENGTH_SHORT).show();
                }
                pJ2++;
                tvPlayerP2.setText(pJ2+"");
                win = true;
                firebase.actualizarDatos(0,1,0);
            }
        }

    }

    public void PintarTurno(){
        if (turno.equals("J1")){
            xLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.borde_amarillo));
            oLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.borde_negro));
        }else{
            xLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.borde_negro));
            oLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.borde_amarillo));
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
        if (t.turnoJugadores().equals("J1")){
            turno = "J1";
            xLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.borde_amarillo));
            oLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.borde_negro));
        }else{
            turno = "J2";
            xLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.borde_negro));
            oLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.borde_amarillo));
        }
    }

}