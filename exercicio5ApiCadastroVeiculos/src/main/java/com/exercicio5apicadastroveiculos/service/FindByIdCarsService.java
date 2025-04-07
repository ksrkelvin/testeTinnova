package com.exercicio5apicadastroveiculos.service;

import com.exercicio5apicadastroveiculos.dto.VehicleDTO;
import com.exercicio5apicadastroveiculos.entity.VehicleEntity;
import com.exercicio5apicadastroveiculos.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FindByIdCarsService {

    private final VehicleRepository repository;

    public FindByIdCarsService(VehicleRepository repository) {
        this.repository = repository;
    }

    public VehicleDTO execute(Long id) {
        VehicleEntity vehicle = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with ID: " + id));

        return new VehicleDTO(vehicle);
    }
}
