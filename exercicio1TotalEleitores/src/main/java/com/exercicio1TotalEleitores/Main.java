package com.exercicio1TotalEleitores;

import com.exercicio1TotalEleitores.domain.model.Eleitores;
import com.exercicio1TotalEleitores.domain.service.EleicaoService;
import com.exercicio1TotalEleitores.domain.usecase.CalulateEleitoresUseCase;

public class Main {
    public static void main(String[] args) {

       Eleitores eleitores = new Eleitores(1000, 800, 150, 50);
       EleicaoService eleicaoService = new EleicaoService();

       eleicaoService.ExibirResultados(eleitores);
    }
}