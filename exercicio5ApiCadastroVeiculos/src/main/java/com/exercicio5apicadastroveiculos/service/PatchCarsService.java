package com.exercicio5apicadastroveiculos.service;

import com.exercicio5apicadastroveiculos.dto.VehicleDTO;
import com.exercicio5apicadastroveiculos.dto.VehiclePatchDTO;
import com.exercicio5apicadastroveiculos.entity.VehicleEntity;
import com.exercicio5apicadastroveiculos.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PatchCarsService {

    private final VehicleRepository repository;

    public PatchCarsService(VehicleRepository repository) {
        this.repository = repository;
    }

    public VehicleDTO execute(Long id, VehiclePatchDTO dto) {
        VehicleEntity vehicle = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Veículo não encontrado com ID: " + id));

        if (dto.getBrand() != null) {
            vehicle.setBrand(dto.getBrand());
        }
        if (dto.getVehicle() != null) {
            vehicle.setVehicle(dto.getVehicle());
        }
        if (dto.getYear() != null) {
            vehicle.setYear(dto.getYear());
        }
        if (dto.getColor() != null) {
            vehicle.setColor(dto.getColor());
        }
        if (dto.getSold() != null) {
            vehicle.setSold(dto.getSold());
        }

        vehicle = repository.save(vehicle);
        return new VehicleDTO(vehicle);
    }
}
