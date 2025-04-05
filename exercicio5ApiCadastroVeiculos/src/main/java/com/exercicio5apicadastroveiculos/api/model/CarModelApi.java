package com.exercicio5apicadastroveiculos.api.model;

import com.exercicio5apicadastroveiculos.domain.model.Car;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
public class CarModelApi {
    public List<QtyDecade> decadas;
    public List<QtyManufacturers> fabricantes;
    public List<Car> veiculos;

    public CarModelApi(List<Car> car) {
        this.veiculos = car;
    }


    public void GroupDecade() {
        if (veiculos == null) {
            veiculos = new ArrayList<>();
        }

        if (decadas == null) {
            decadas = new ArrayList<>();
        }

        List<QtyDecade> decades = veiculos.stream()
                .filter(c -> c.getAno() != null && c.getAno() > 0)
                .collect(Collectors.groupingBy(
                        c -> (c.getAno() / 10) * 10,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .map(entry -> new QtyDecade(entry.getKey(), entry.getValue().intValue()))
                .sorted(Comparator.comparing(QtyDecade::getDecada))
                .collect(Collectors.toList());

        decadas.clear();              // limpa se já tinha dados anteriores
        decadas.addAll(decades);      // adiciona os novos elementos
    }

    public void GroupManufacturers() {
        if (veiculos == null) {
            veiculos = new ArrayList<>();
        }

        if (fabricantes == null) {
            fabricantes = new ArrayList<>();
        }

        List<QtyManufacturers> manufacturer = veiculos.stream()
                .filter(c -> c.getMarca() != null && c.getMarca() != "")
                .collect(Collectors.groupingBy(
                        c -> c.getMarca(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .map(entry -> new QtyManufacturers(entry.getKey(), entry.getValue().intValue()))
                .sorted(Comparator.comparing(QtyManufacturers::getFabricante))
                .collect(Collectors.toList());

        fabricantes.clear();              // limpa se já tinha dados anteriores
        fabricantes.addAll(manufacturer);      // adiciona os novos elementos
    }
}
