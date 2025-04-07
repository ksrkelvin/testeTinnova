package com.exercicio5apicadastroveiculos.service;

import com.exercicio5apicadastroveiculos.dto.InfoDTO;
import com.exercicio5apicadastroveiculos.repository.VehicleCriteriaRepository;
import org.springframework.stereotype.Service;

@Service
public class InfoCarsService {

    private final VehicleCriteriaRepository repository;

    public InfoCarsService(VehicleCriteriaRepository repository) {
        this.repository = repository;
    }

    public InfoDTO execute() {

        InfoDTO infoDTO = new InfoDTO();

        infoDTO.setUltimosCadastrados(repository.listLastCreated());
        infoDTO.setVendidos(repository.countSold());
        infoDTO.setFabricantes(repository.groupByBrands());
        infoDTO.setDecadas(repository.groupByYear());


        return infoDTO;
    }

}
