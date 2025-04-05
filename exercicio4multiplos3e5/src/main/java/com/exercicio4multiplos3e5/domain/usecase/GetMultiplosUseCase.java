package com.exercicio4multiplos3e5.domain.usecase;

import com.exercicio4multiplos3e5.domain.model.Multiplos;

import java.util.ArrayList;

public class GetMultiplosUseCase {

    public void getMultiplos3(Multiplos multiplos) {

        ArrayList<Double> multiplos3 = new ArrayList<>();

        for (double i = 1; i < multiplos.getValor(); i++) {
            if (i % 3 == 0) {
                multiplos3.add(i);
            }
        }
        multiplos.setMultiplos3(multiplos3);


    }

    public void getMultiplos5(Multiplos multiplos) {
        ArrayList<Double> multiplos5 = new ArrayList<>();

        for (double i = 1; i < multiplos.getValor(); i++) {
            if (i % 5 == 0) {
                multiplos5.add(i);
            }
        }
        multiplos.setMultiplos5(multiplos5);

    }

    public double getMultiplosSoma(Multiplos multiplos) {
        double somaMultiplo3 = 0;
        double somaMultiplos5 = 0;

        for (double valor3 : multiplos.getMultiplos3()) {
            somaMultiplo3 += valor3;
        }
        for (double valor5 : multiplos.getMultiplos5()) {
            somaMultiplos5 += valor5;
        }

        return somaMultiplo3 + somaMultiplos5;
    }
}
