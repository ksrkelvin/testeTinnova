package com.exercicio5apicadastroveiculos.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;


@Data
public class InfoDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<QtyDecadeDTO> decadas;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<QtyManufacturersDTO> fabricantes;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public long vendidos;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<VehicleDTO> ultimosCadastrados;



}
