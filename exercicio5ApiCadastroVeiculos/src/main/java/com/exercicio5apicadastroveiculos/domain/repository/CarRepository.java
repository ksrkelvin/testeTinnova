package com.exercicio5apicadastroveiculos.domain.repository;

import com.exercicio5apicadastroveiculos.domain.model.Car;
import com.exercicio5apicadastroveiculos.domain.model.QtyDecade;
import com.exercicio5apicadastroveiculos.domain.model.QtyManufacturers;

import java.util.List;

public interface CarRepository {

    List<Car> listar();

    List<Car> filterList(String marca, Integer ano, String cor, Boolean vendido);

    Car buscarPorId(Long id);

    Car salvar(Car car);

    void deletar(Car car);

    long countVendidos();

    List<Car> listarUltimosCadastrados();

    List<QtyManufacturers> groupByMarca();

    List<QtyDecade> groupByAno();
}
