package com.exercicio5apicadastroveiculos.domain.model;

public enum Mark {
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
        for (Mark mark : values()) {
            if (mark.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}