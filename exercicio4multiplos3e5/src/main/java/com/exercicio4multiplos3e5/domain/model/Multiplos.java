package com.exercicio4multiplos3e5.domain.model;

import java.util.ArrayList;

public class Multiplos {
    double valor;
    ArrayList<Double> multiplos3;
    ArrayList<Double> multiplos5;


    public Multiplos(double valor){
        this.valor = valor;
    }
    public double getValor() {
        return valor;
    }

    public  ArrayList<Double> getMultiplos3() {
        return multiplos3;
    }
    public  ArrayList<Double> getMultiplos5() {
        return multiplos5;
    }

    public  void setMultiplos3(ArrayList<Double> multiplos3) {
        this.multiplos3 = multiplos3;
    }
    public void setMultiplos5(ArrayList<Double> multiplos5) {
        this.multiplos5 = multiplos5;
    }

}
