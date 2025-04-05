package com.exercicio5apicadastroveiculos.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QtyDecade{
    public Integer decada;
    public Integer quantidade;

    public QtyDecade(Integer key, int i) {
        decada=key;
        quantidade=i;
    }
}

