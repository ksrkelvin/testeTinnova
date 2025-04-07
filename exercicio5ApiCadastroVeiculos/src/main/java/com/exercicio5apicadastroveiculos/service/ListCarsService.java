package com.exercicio5apicadastroveiculos.service;

import com.exercicio5apicadastroveiculos.dto.VehicleDTO;
import com.exercicio5apicadastroveiculos.entity.VehicleEntity;
import com.exercicio5apicadastroveiculos.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.criteria.Predicate;

@Service
public class ListCarsService {

    private final VehicleRepository vehicleRepository;

    public ListCarsService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<VehicleDTO> execute(String color, Integer year, String brand, Boolean sold) {
        List<VehicleEntity> vehicles = vehicleRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (color != null) {
                predicates.add(cb.equal(cb.upper(root.get("color")), color.toUpperCase()));
            }
            if (year != null) {
                predicates.add(cb.equal(root.get("year"), year));
            }
            if (brand != null) {
                predicates.add(cb.equal(cb.upper(root.get("brand")), brand.toUpperCase()));
            }
            if (sold != null) {
                predicates.add(cb.equal(root.get("sold"), sold));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });

        return vehicles.stream()
                .map(VehicleDTO::new)
                .collect(Collectors.toList());
    }

}