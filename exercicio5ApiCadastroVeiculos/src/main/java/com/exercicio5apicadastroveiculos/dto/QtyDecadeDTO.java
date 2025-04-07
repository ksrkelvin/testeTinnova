package com.exercicio5apicadastroveiculos.dto;


import lombok.Data;

@Data
public class QtyDecadeDTO {
    public Integer decada;
    public Long quantidade;

    public QtyDecadeDTO(Integer key, long i) {
        decada=key;
        quantidade=i;
    }
}

