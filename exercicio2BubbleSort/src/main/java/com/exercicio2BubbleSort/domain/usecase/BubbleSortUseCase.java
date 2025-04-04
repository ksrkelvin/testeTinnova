package com.exercicio2BubbleSort.domain.usecase;

import com.exercicio2BubbleSort.domain.model.BubbleSort;

public class BubbleSortUseCase {

    public void sort(BubbleSort bubbleSort) {
        int[] vetor = bubbleSort.getVetor();

        if (vetor.length <= 0) {
            System.out.println("Vetor vazio");
        }


        for (int i = 0; i < vetor.length - 1; i++){
            for (int j = 0; j <vetor.length - 1 - i; j++){
                if (vetor[j] > vetor[j+1]){
                    int temp = vetor[j];
                    vetor[j] = vetor[j+1];
                    vetor[j+1] = temp;
                }
            }
        }

        bubbleSort.setVetor(vetor);
    }
}
