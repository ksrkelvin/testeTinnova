package com.exercicio5apicadastroveiculos.dto;

import com.exercicio5apicadastroveiculos.entity.VehicleEntity;
import com.exercicio5apicadastroveiculos.validation.ValidateBrand;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class VehiclePatchDTO {

    @Id
    private Long id;

    @JsonProperty("veiculo")
    private String vehicle;

    @JsonProperty("marca")
    @ValidateBrand
    private String brand;

    @JsonProperty("cor")
    private String color;

    @JsonProperty("ano")
    private Integer year;

    @JsonProperty("vendido")
    private boolean sold;


    public Boolean getSold() {
        return sold;
    }
    public VehiclePatchDTO() {}

    public VehiclePatchDTO(VehicleEntity entity) {
        this.id = entity.getId();
        setVehicle(entity.getVehicle());
        setYear(entity.getYear());
        setSold(entity.isSold());
        setBrand(entity.getBrand().toUpperCase());
        setColor(entity.getColor().toUpperCase());

    }

}
