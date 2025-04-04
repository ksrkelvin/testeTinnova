package com.exercicio2BubbleSort;

import com.exercicio2BubbleSort.domain.model.BubbleSort;
import com.exercicio2BubbleSort.domain.service.BubbleSortService;

public class Main {
    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort(new int[]{5, 3, 2, 4, 7, 1, 0, 6});
        BubbleSortService bubbleSortService = new BubbleSortService();

        bubbleSortService.ExibirResultado(bubbleSort);


    }
}