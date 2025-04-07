package com.exercicio5apicadastroveiculos.service;

import com.exercicio5apicadastroveiculos.dto.VehicleDTO;
import com.exercicio5apicadastroveiculos.entity.VehicleEntity;
import com.exercicio5apicadastroveiculos.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SaveCarsService {

    private final VehicleRepository repository;

    public SaveCarsService(VehicleRepository repository) {
        this.repository = repository;
    }

    public VehicleDTO execute(Long id, VehicleDTO dto) {
        VehicleEntity vehicle;

        if (id == null) {
            vehicle = new VehicleEntity();
        } else {
            vehicle = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Veículo não encontrado com ID: " + id));
        }
        vehicle.setBrand(dto.getBrand());
        vehicle.setVehicle(dto.getVehicle());
        vehicle.setYear(dto.getYear());
        vehicle.setColor(dto.getColor());
        vehicle.setSold(dto.getSold());
        vehicle = repository.save(vehicle);

        return new VehicleDTO(vehicle);
    }
}
