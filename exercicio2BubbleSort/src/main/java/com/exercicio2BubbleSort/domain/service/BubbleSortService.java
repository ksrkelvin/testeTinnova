package com.exercicio2BubbleSort.domain.service;

import com.exercicio2BubbleSort.domain.model.BubbleSort;
import com.exercicio2BubbleSort.domain.usecase.BubbleSortUseCase;

import java.util.Arrays;

public class BubbleSortService {

    private final BubbleSortUseCase bubbleSortUseCase;

    public BubbleSortService() {
        this.bubbleSortUseCase = new BubbleSortUseCase();
    }

    public void ExibirResultado(BubbleSort bubbleSort) {
        System.out.println("Vetor antes do BubbleSort: " + Arrays.toString(bubbleSort.getVetor()));

        bubbleSortUseCase.sort(bubbleSort);

        System.out.println("Vetor depois do BubbleSort: " + Arrays.toString(bubbleSort.getVetor()));


    }

}
