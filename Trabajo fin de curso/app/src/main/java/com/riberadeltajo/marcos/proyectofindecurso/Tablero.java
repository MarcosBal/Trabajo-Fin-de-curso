package com.riberadeltajo.marcos.proyectofindecurso;

import java.util.ArrayList;
import java.util.Random;

public class Tablero {

    public enum Ficha {Vacio, X, O}

    private Ficha[][] tablero;
    private Ficha jugador, IA, jugador2;
    private int filasColumnas;

    public Tablero(int filasColumnas) {
        this.filasColumnas = filasColumnas;
        rellenarTablero();
    }

    public Ficha[][] getTablero() {
        return tablero;
    }

    public void setTablero(Ficha[][] tablero) {
        this.tablero = tablero;
    }

    public int getFilasColumnas() {
        return filasColumnas;
    }

    public void setFilasColumnas(int filasColumnas) {
        this.filasColumnas = filasColumnas;
    }

    public Ficha getJugador() {
        return jugador;
    }

    public void setJugador(Ficha jugador) {
        this.jugador = jugador;
    }

    public Ficha getJugador2() {
        return jugador2;
    }

    public void setJugador2(Ficha jugador2) {
        this.jugador2 = jugador2;
    }

    public Ficha getIA() {
        return IA;
    }

    public void setIA(Ficha IA) {
        this.IA = IA;
    }

    private void rellenarTablero() {
        tablero = new Ficha[filasColumnas][filasColumnas];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = Ficha.Vacio;
            }
        }
    }

    public String turno() {
        Random r = new Random();
        if (r.nextInt(2) == 0)
            return "IA";
        else
            return "J";
    }

    public String turnoJugadores() {
        Random r = new Random();
        if (r.nextInt(2) == 0)
            return "J1";
        else
            return "J2";
    }

    public String cambiarTurno(String turno) {
        if (turno.equals("J1")){
            return "J2";
        }else{
            return "J1";
        }
    }

    public void movimiento(int x, int y, Ficha s, Ficha[][] tab) {
        tab[x][y] = s;
    }

    public Posicion movimientoIA(Ficha[][] tabl) {
        Posicion posi = new Posicion();
        if (jugador == Ficha.X) {
            IA = Ficha.O;
        } else {
            IA = Ficha.X;
        }
        Ficha[][] copiaTablero;
        //Compruebo que la ia pueda ganar en el siguiente mov
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copiaTablero = getCopiaTablero(tabl);
                if (copiaTablero[i][j] == Ficha.Vacio) {
                    movimiento(i, j, IA, copiaTablero);
                    if (comprobarVictoria(copiaTablero, IA, i, j)) {
                        posi.setX(i);
                        posi.setY(j);
                        return posi;
                    }
                }
            }
        }

        //Compruebo que el jugador pueda ganar en el siguente mov para bloquerlo
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copiaTablero = getCopiaTablero(tabl);
                if (copiaTablero[i][j] == Ficha.Vacio) {
                    movimiento(i, j, jugador, copiaTablero);
                    if (comprobarVictoria(copiaTablero, jugador, i, j)) {
                        posi.setX(i);
                        posi.setY(j);
                        return posi;
                    }
                }
            }
        }

        //Intenta coger las esquinas si estan libres
        posi = escogerEsquina(tabl);
        if (posi != null) {
            return posi;
        }

        //Intenta coger el centro si está libre
        if (tabl[1][1] == Ficha.Vacio) {
            posi.setX(1);
            posi.setY(1);
            return posi;
        }

        //Intenta coger los lados si entan libres
        posi = escogerLado(tabl);
        //movimiento(posi.getX(), posi.getY(), IA, tabl);
        return posi;
    }

    private Ficha[][] getCopiaTablero(Ficha[][] tab) {
        Ficha[][] copia = new Ficha[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copia[i][j] = tab[i][j];
            }
        }
        return copia;
    }

    public boolean comprobarVictoria(Ficha[][] tab, Ficha s, int x, int y) {
        //Comprobar columnas
        for (int i = 0; i < filasColumnas; i++) {
            if (tab[x][i] != s)
                break;
            if (i == filasColumnas - 1) {
                //report win for s
                return true;
            }
        }

        //Comprobar filas
        for (int i = 0; i < filasColumnas; i++) {
            if (tab[i][y] != s)
                break;
            if (i == filasColumnas - 1) {
                //report win for s
                return true;
            }
        }

        //Comprobar diagonal
        if (x == y) {
            //we're on a diagonal
            for (int i = 0; i < filasColumnas; i++) {
                if (tab[i][i] != s)
                    break;
                if (i == filasColumnas - 1) {
                    //report win for s
                    return true;
                }
            }
        }

        //Comprobar la otra diagonal
        if (x + y == filasColumnas - 1) {
            for (int i = 0; i < filasColumnas; i++) {
                if (tab[i][(filasColumnas - 1) - i] != s)
                    break;
                if (i == filasColumnas - 1) {
                    //report win for s
                    return true;
                }
            }
        }
        return false;
    }

    private Posicion escogerLado(Ficha[][] tab) {
        ArrayList<Posicion> posis = new ArrayList<>();
        if (tab[0][1] == Ficha.Vacio){
            Posicion posi = new Posicion();
            posi.setX(0);
            posi.setY(1);
            posis.add(posi);
        }

        if (tab[1][0] == Ficha.Vacio){
            Posicion posi = new Posicion();
            posi.setX(1);
            posi.setY(0);
            posis.add(posi);
        }

        if (tab[2][1] == Ficha.Vacio){
            Posicion posi = new Posicion();
            posi.setX(2);
            posi.setY(1);
            posis.add(posi);
        }

        if (tab[1][2] == Ficha.Vacio){
            Posicion posi = new Posicion();
            posi.setX(1);
            posi.setY(2);
            posis.add(posi);
        }
        if (posis.size() != 0){
            Random r = new Random();
            return posis.get(r.nextInt(posis.size()));
        }else{
            return null;
        }
    }

    private Posicion escogerEsquina(Ficha[][] tab) {
        ArrayList<Posicion> posis = new ArrayList<>();
        if (tab[0][0] == Ficha.Vacio){
            Posicion posi = new Posicion();
            posi.setX(0);
            posi.setY(0);
            posis.add(posi);
        }

        if (tab[0][2] == Ficha.Vacio){
            Posicion posi = new Posicion();
            posi.setX(0);
            posi.setY(2);
            posis.add(posi);
        }

        if (tab[2][0] == Ficha.Vacio){
            Posicion posi = new Posicion();
            posi.setX(2);
            posi.setY(0);
            posis.add(posi);
        }

        if (tab[2][2] == Ficha.Vacio){
            Posicion posi = new Posicion();
            posi.setX(2);
            posi.setY(2);
            posis.add(posi);
        }
        if (posis.size() != 0){
            Random r = new Random();
            return posis.get(r.nextInt(posis.size()));
        }else{
            return null;
        }
    }

    public boolean comprobarEmpate(int numeroMov) {
        return numeroMov == (Math.pow(filasColumnas, 2));
    }

    public void reiniciar() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = Ficha.Vacio;
            }
        }
    }

    public boolean comprobarPosi(int x, int y) {
        return tablero[x][y] == Ficha.Vacio;
    }
}
