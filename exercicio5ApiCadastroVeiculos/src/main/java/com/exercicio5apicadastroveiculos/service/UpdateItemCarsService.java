package com.exercicio5apicadastroveiculos.service;
import com.exercicio5apicadastroveiculos.dto.VehicleDTO;
import com.exercicio5apicadastroveiculos.entity.VehicleEntity;
import com.exercicio5apicadastroveiculos.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;


@Service
public class UpdateItemCarsService {

    private final VehicleRepository repository;

    @Autowired
    public UpdateItemCarsService(VehicleRepository repository) {
        this.repository = repository;
    }

    public VehicleDTO execute(Long id, VehicleDTO dto) {
        VehicleEntity vehicle = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with ID: " + id));

        Field[] fields = VehicleDTO.class.getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(dto);

                if (value != null) {
                    Field entityField = VehicleEntity.class.getDeclaredField(field.getName());
                    entityField.setAccessible(true);
                    entityField.set(vehicle, value);
                }

            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("{error.invalid}: " + field.getName(), e);
            }
        }

        VehicleEntity updated = repository.save(vehicle);
        return new VehicleDTO(updated);
    }

}
