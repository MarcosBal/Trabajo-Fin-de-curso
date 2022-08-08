package com.riberadeltajo.marcos.proyectofindecurso;

public class Tablero {

    private enum estado {Vacio, X, O}
    private estado tablero[][];
    private int filasColumnas;
    private int numeroMovi;

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

    public int getNumeroMovi() {
        return numeroMovi;
    }

    public void setNumeroMovi(int numeroMovi) {
        this.numeroMovi = numeroMovi;
    }

    private void rellenarTablero(){
        tablero = new estado[filasColumnas][filasColumnas];
    }

    private void Move(int x, int y, estado s){
        if(tablero[x][y] == estado.Vacio){
            tablero[x][y] = s;
        }
        numeroMovi++;

        //Comprobar condicion de victoria

        //Comprobar columnas
        for(int i = 0; i < filasColumnas; i++){
            if(tablero[x][i] != s)
                break;
            if(i == filasColumnas-1){
                //report win for s
            }
        }

        //Comprobar filas
        for(int i = 0; i < filasColumnas; i++){
            if(tablero[i][y] != s)
                break;
            if(i == filasColumnas-1){
                //report win for s
            }
        }

        //Comprobar diagonal
        if(x == y){
            //we're on a diagonal
            for(int i = 0; i < filasColumnas; i++){
                if(tablero[i][i] != s)
                    break;
                if(i == filasColumnas-1){
                    //report win for s
                }
            }
        }

        //Comprobar la otra diagonal
        if(x + y == filasColumnas - 1){
            for(int i = 0; i < filasColumnas; i++){
                if(tablero[i][(filasColumnas-1)-i] != s)
                    break;
                if(i == filasColumnas-1){
                    //report win for s
                }
            }
        }

        //Comprobar empate
        if(numeroMovi == (Math.pow(filasColumnas, 2) - 1)){
            //report draw
        }
    }

    private void reiniciar(){
        rellenarTablero();
    }
}
