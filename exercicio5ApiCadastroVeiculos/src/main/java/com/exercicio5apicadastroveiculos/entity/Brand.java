package com.exercicio5apicadastroveiculos.entity;

public enum Brand {
    TOYOTA,
    HONDA,
    FORD,
    CHEVROLET,
    VOLKSWAGEN,
    BMW,
    FIAT,
    HYUNDAI,
    NISSAN,
    RENAULT;

    public static boolean contains(String value){
        for (Brand brand : values()) {
            if (brand.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}