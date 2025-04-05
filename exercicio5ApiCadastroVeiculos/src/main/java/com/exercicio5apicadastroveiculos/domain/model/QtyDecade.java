package com.exercicio5apicadastroveiculos.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QtyDecade{
    public Integer decada;
    public Long quantidade;

    public QtyDecade(Integer key, long i) {
        decada=key;
        quantidade=i;
    }
}

