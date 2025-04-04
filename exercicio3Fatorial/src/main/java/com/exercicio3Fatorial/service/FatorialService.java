package com.exercicio3Fatorial.service;

import com.exercicio3Fatorial.model.Fatorial;
import com.exercicio3Fatorial.usecase.CalculeFatorialUseCase;

public class FatorialService {

    private final CalculeFatorialUseCase calculeFatorialUseCase;

    public FatorialService() {
        this.calculeFatorialUseCase = new CalculeFatorialUseCase();
    }

    public void ExibirFatorial(Fatorial fatorial){
        calculeFatorialUseCase.GenerateFatorialUseCase(fatorial);
        System.out.println(fatorial.getFatorial() +" = "+ fatorial.getValor());
    }

}
