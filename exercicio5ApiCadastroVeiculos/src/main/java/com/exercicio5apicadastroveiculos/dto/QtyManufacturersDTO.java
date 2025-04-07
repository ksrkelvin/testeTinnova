package com.exercicio5apicadastroveiculos.dto;

import lombok.Data;


@Data
public class QtyManufacturersDTO {
    public String fabricante;
    public Long quantidade;

    public QtyManufacturersDTO(String key, long i) {
        fabricante = key;
        quantidade = i;
    }
}