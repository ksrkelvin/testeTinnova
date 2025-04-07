package com.exercicio5apicadastroveiculos.dto;

import com.exercicio5apicadastroveiculos.entity.VehicleEntity;
import com.exercicio5apicadastroveiculos.validation.ValidateBrand;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VehicleDTO {

    @Id
    private Long id;

    @NotBlank(message = "{vehicle.vehicle.required}")
    @JsonProperty("veiculo")
    private String vehicle;

    @NotBlank(message = "{vehicle.brand.required}")
    @ValidateBrand
    @JsonProperty("marca")
    private String brand;


    @NotBlank(message = "{vehicle.color.required}")
    @JsonProperty("cor")
    private String color;

    @JsonProperty("ano")
    @NotNull(message = "{vehicle.year.required}")
    private Integer year;

    @NotNull(message = "{vehicle.year.required}")
    @JsonProperty("vendido")
    private boolean sold;

    @JsonProperty("data_cadastro")
    private LocalDateTime created;

    @JsonProperty("data_atualizacao")
    private LocalDateTime updated;

    public VehicleDTO() {}

    public VehicleDTO(VehicleEntity entity) {
        this.id = entity.getId();
        setVehicle(entity.getVehicle());
        setYear(entity.getYear());
        setSold(entity.isSold());
        setBrand(entity.getBrand().toUpperCase());
        setColor(entity.getColor().toUpperCase());
        setCreated(entity.getCreated());
        setUpdated(entity.getUpdated());

    }

}
