package com.exercicio5apicadastroveiculos.service;

import com.exercicio5apicadastroveiculos.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DeleteCarsService {

    private final VehicleRepository repository;


    public DeleteCarsService(VehicleRepository repository) {
        this.repository = repository;
    }

    public void execute(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Vehicle not found with ID: " + id);
        }
        repository.deleteById(id);
    }
}
