package com.exercicio1TotalEleitores.domain.model;

public class Eleitores {
    double total;
    double votosValidos;
    double votosBrancos;
    double votosNulos;

    public Eleitores(
            double total,
            double votosValidos,
            double votosBrancos,
            double votosNulos
    ) {
        this.total = total;
        this.votosValidos = votosValidos;
        this.votosBrancos = votosBrancos;
        this.votosNulos = votosNulos;
    }

    public double getTotal() {
        return total;
    }
    public double getVotosValidos() {
        return votosValidos;
    }
    public double getVotosBrancos() {
        return votosBrancos;
    }
    public double getVotosNulos() {
        return votosNulos;
    }

}

