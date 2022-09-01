package com.riberadeltajo.marcos.proyectofindecurso;

import java.util.Random;

public class Tablero {

    public enum estado {Vacio, X, O}
    private estado[][] tablero;
    private estado jugador, IA;
    private int filasColumnas;

    public Tablero(int filasColumnas) {
        this.filasColumnas = filasColumnas;
        rellenarTablero();
    }

    public estado[][] getTablero() {
        return tablero;
    }

    public void setTablero(estado[][] tablero) {
        this.tablero = tablero;
    }

    public int getFilasColumnas() {
        return filasColumnas;
    }

    public void setFilasColumnas(int filasColumnas) {
        this.filasColumnas = filasColumnas;
    }

    public estado getJugador() {
        return jugador;
    }

    public void setJugador(estado jugador) {
        this.jugador = jugador;
    }

    public estado getIA() {
        return IA;
    }

    public void setIA(estado IA) {
        this.IA = IA;
    }

    private void rellenarTablero(){
        tablero = new estado[filasColumnas][filasColumnas];
        for (int i = 0; i <3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = estado.Vacio;
            }
        }
    }

    public String turno(){
        Random r = new Random();
        if(r.nextInt(2) == 0)
            return "IA";
        else
            return "J";
    }

    public void movimiento(int x, int y, estado s, estado[][] tab){
        tab[x][y] = s;
    }

    public Posicion movimientoIA(estado[][] tabl){
        Posicion posi = new Posicion();
        if(jugador == estado.X){
            IA = estado.O;
        }else{
            IA = estado.X;
        }
        estado[][] copiaTablero;
        //Compruebo que la ia pueda ganar en el siguiente mov
        for (int i = 0; i <3; i++) {
            for (int j = 0; j < 3; j++) {
                copiaTablero = getCopiaTablero(tabl);
                if(copiaTablero[i][j] == estado.Vacio){
                    movimiento(i, j, IA, copiaTablero);
                    if(comprobarVictoria(copiaTablero, IA, i, j)){
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
                if(copiaTablero[i][j] == estado.Vacio){
                    movimiento(i, j, jugador, copiaTablero);
                    if(comprobarVictoria(copiaTablero, jugador, i, j)){
                        posi.setX(i);
                        posi.setY(j);
                        return posi;
                    }
                }
            }
        }

        //Intenta coger las esquinas si estan libres
        posi = escogerEsquina(tabl);
        if (posi != null){
            //movimiento(posi.getX(), posi.getY(), IA, tabl);
            return posi;
        }

        //Intenta coger el centro si está libre
        if (tabl[1][1] == estado.Vacio) {
            //movimiento(1, 1, IA, tabl);
            posi.setX(1);
            posi.setY(1);
            return posi;
        }

        //Intenta coger los lados si entan libres
        posi = escogerLado(tabl);
        //movimiento(posi.getX(), posi.getY(), IA, tabl);
        return posi;
    }

    private estado[][] getCopiaTablero (estado[][] tab){
        estado[][] copia = new estado[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copia[i][j] = tab[i][j];
            }
        }
        return copia;
    }

    public boolean comprobarVictoria(estado[][] tab, estado s, int x, int y) {
        //Comprobar columnas
        for(int i = 0; i < filasColumnas; i++){
            if(tab[x][i] != s)
                break;
            if(i == filasColumnas-1){
                //report win for s
                return true;
            }
        }

        //Comprobar filas
        for(int i = 0; i < filasColumnas; i++){
            if(tab[i][y] != s)
                break;
            if(i == filasColumnas-1){
                //report win for s
                return true;
            }
        }

        //Comprobar diagonal
        if(x == y){
            //we're on a diagonal
            for(int i = 0; i < filasColumnas; i++){
                if(tab[i][i] != s)
                    break;
                if(i == filasColumnas-1){
                    //report win for s
                    return true;
                }
            }
        }

        //Comprobar la otra diagonal
        if(x + y == filasColumnas - 1){
            for(int i = 0; i < filasColumnas; i++){
                if(tab[i][(filasColumnas-1)-i] != s)
                    break;
                if(i == filasColumnas-1){
                    //report win for s
                    return true;
                }
            }
        }
        return false;
    }

    private Posicion escogerLado(estado[][] tab) {
        Posicion posi = new Posicion();
        if (tab[0][1] == estado.Vacio){
            posi.setX(0);
            posi.setY(1);
            return posi;
        }

        if (tab[1][0] == estado.Vacio){
            posi.setX(1);
            posi.setY(0);
            return posi;
        }

        if (tab[2][1] == estado.Vacio){
            posi.setX(2);
            posi.setY(1);
            return posi;
        }

        if (tab[1][2] == estado.Vacio){
            posi.setX(1);
            posi.setY(2);
            return posi;
        }

        return null;
    }

    private Posicion escogerEsquina(estado[][] tab) {
        Posicion posi = new Posicion();
        if (tab[0][0] == estado.Vacio){
            posi.setX(0);
            posi.setY(0);
            return posi;
        }

        if (tab[0][2] == estado.Vacio){
            posi.setX(0);
            posi.setY(2);
            return posi;
        }

        if (tab[2][0] == estado.Vacio){
            posi.setX(2);
            posi.setY(0);
            return posi;
        }

        if (tab[2][2] == estado.Vacio){
            posi.setX(2);
            posi.setY(2);
            return posi;
        }

        return null;
    }

    public boolean comprobarEmpate(int numeroMov){
        return numeroMov == (Math.pow(filasColumnas, 2) - 1);
    }

    public void reiniciar(){
        for (int i = 0; i <3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = estado.Vacio;
            }
        }
    }

    public boolean comprobarPosi(int x, int y){
        return tablero[x][y] == estado.Vacio;
    }
}
