package com.exercicio5apicadastroveiculos.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QtyManufacturers {
    public String fabricante;
    public Integer quantidade;

    public QtyManufacturers(String key, int i) {
        fabricante = key;
        quantidade = i;
    }
}