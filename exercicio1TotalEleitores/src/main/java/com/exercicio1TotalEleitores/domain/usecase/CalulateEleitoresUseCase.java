package com.exercicio1TotalEleitores.domain.usecase;

import com.exercicio1TotalEleitores.domain.model.Eleitores;

public class CalulateEleitoresUseCase {

    public double PercentualValidao(Eleitores eleitores) {
        return (eleitores.getVotosValidos()/eleitores.getTotal())*100;
    }

    public double PercentualBrancos(Eleitores eleitores) {
        return (eleitores.getVotosBrancos()/eleitores.getTotal())*100;
    }

    public double PercentualNulos(Eleitores eleitores) {
        return (eleitores.getVotosNulos()/eleitores.getTotal())*100;
    }

}
