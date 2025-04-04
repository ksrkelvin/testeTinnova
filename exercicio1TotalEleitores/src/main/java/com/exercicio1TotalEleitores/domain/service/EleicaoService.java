package com.exercicio1TotalEleitores.domain.service;

import com.exercicio1TotalEleitores.domain.model.Eleitores;
import com.exercicio1TotalEleitores.domain.usecase.CalulateEleitoresUseCase;

public class EleicaoService {
    private final CalulateEleitoresUseCase useCase;

    public EleicaoService() {
        this.useCase = new CalulateEleitoresUseCase();
    }

    public void ExibirResultados(Eleitores eleitores) {
        System.out.println("Total de votos: " + eleitores.getTotal());
        System.out.println("Total de votos validos: " + eleitores.getVotosValidos());
        System.out.println("Total de votos brancos: " + eleitores.getVotosBrancos());
        System.out.println("Total de votos nulos: " + eleitores.getVotosNulos());
        System.out.println("======================================");
        System.out.printf("Percentual votos validos:  %.2f%%%n", useCase.PercentualValidao(eleitores));
        System.out.printf("Percentual votos brancos: %.2f%%%n", useCase.PercentualBrancos(eleitores));
        System.out.printf("Percentual votos nulos: %.2f%%%n", useCase.PercentualNulos(eleitores));



    }

}
