package com.exercicio5apicadastroveiculos.dto;

import com.exercicio5apicadastroveiculos.entity.VehicleEntity;
import com.exercicio5apicadastroveiculos.validation.ValidateBrand;
import jakarta.persistence.Id;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class VehicleDTO {

    @Id
    private Long id;

    @NotBlank(message = "{vehicle.brand.required}")
    @ValidateBrand
    private String brand;

    @NotBlank(message = "{vehicle.vehicle.required}")
    private String vehicle;

    @NotBlank(message = "{vehicle.color.required}")
    private String color;

    @NotNull(message = "{vehicle.year.required}")
    private Integer year;

    private boolean sold;

    public VehicleDTO() {}

    public VehicleDTO(VehicleEntity entity) {
        this.id = entity.getId();
        this.brand = entity.getBrand();
        this.color = entity.getColor();
        this.year = entity.getYear();
        this.sold = entity.isSold();
    }


    public void setBrand(String brand) {
        this.brand = brand != null ? brand.toUpperCase() : null;
    }
    public void setColor(String brand) {
        this.color = brand != null ? brand.toUpperCase() : null;
    }

}
