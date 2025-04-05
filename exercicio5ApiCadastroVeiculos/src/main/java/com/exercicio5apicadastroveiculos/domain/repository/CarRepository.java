package com.exercicio5apicadastroveiculos.domain.repository;

import com.exercicio5apicadastroveiculos.domain.model.Car;
import com.exercicio5apicadastroveiculos.domain.model.QtyDecade;
import com.exercicio5apicadastroveiculos.domain.model.QtyManufacturers;

import java.util.List;

public interface CarRepository {

    List<Car> Listar();

    List<Car> FilterList(String marca, Integer ano, String cor, Boolean vendido);

    Car BuscarPorId(Long id);

    Car Salvar(Car car);

    void Deletar(Car car);

    long CountVendidos();

    List<Car> ListarUltimosCadastrados();

    List<QtyManufacturers> GroupByMarca();

    List<QtyDecade> GroupByAno();
}
