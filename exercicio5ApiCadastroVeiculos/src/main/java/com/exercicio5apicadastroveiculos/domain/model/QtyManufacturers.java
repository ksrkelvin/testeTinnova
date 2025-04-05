package com.exercicio5apicadastroveiculos.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QtyManufacturers {
    public String fabricante;
    public Long quantidade;

    public QtyManufacturers(String key, long i) {
        fabricante = key;
        quantidade = i;
    }
}