package com.exercicio5apicadastroveiculos.api.model;

import com.exercicio5apicadastroveiculos.domain.model.Car;
import com.exercicio5apicadastroveiculos.domain.model.QtyDecade;
import com.exercicio5apicadastroveiculos.domain.model.QtyManufacturers;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class CarInfoModelApi {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<QtyDecade> decadas;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<QtyManufacturers> fabricantes;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public long vendidos;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<Car> ultimosCadastrados;

    public CarInfoModelApi(List<Car> veiculos, long vendidos, List<QtyDecade> qtyDecade, List<QtyManufacturers> qtyManufacturers) {
        this.ultimosCadastrados = veiculos;
        this.decadas = qtyDecade;
        this.fabricantes = qtyManufacturers;
        this.vendidos = vendidos;
    }


}
