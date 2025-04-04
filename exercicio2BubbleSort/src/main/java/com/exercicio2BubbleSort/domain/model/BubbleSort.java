package com.exercicio2BubbleSort.domain.model;

import java.lang.reflect.Array;

public class BubbleSort {
    int[] vetor = new int[8];

    public BubbleSort(int[] vetor) {
        this.vetor = vetor;
    }
    public int[] getVetor() {
        return vetor;
    }
    public void setVetor(int[] vetor) {
        this.vetor = vetor;
    }
}
