package com.exercicio4multiplos3e5.domain.service;

import com.exercicio4multiplos3e5.domain.model.Multiplos;
import com.exercicio4multiplos3e5.domain.usecase.GetMultiplosUseCase;

public class MultiplosService {

    private final GetMultiplosUseCase getMultiplosUseCase;

    public MultiplosService() {
        this.getMultiplosUseCase = new GetMultiplosUseCase();
    }

    public void ExibirMultiplos(Multiplos multiplos) {

        getMultiplosUseCase.getMultiplos5(multiplos);
        getMultiplosUseCase.getMultiplos3(multiplos);

        System.out.println("Multiplos de 3: " + multiplos.getMultiplos3());
        System.out.println("Multiplos de 5: " + multiplos.getMultiplos5());
        System.out.println("Soma dos multplos de 3 e 5: " + getMultiplosUseCase.getMultiplosSoma(multiplos));
    }

}
