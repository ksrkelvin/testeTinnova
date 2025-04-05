package com.exercicio4multiplos3e5;

import com.exercicio4multiplos3e5.domain.model.Multiplos;
import com.exercicio4multiplos3e5.domain.service.MultiplosService;

public class Main {
    public static void main(String[] args) {
        double valor = 0;
        if (args.length == 0) {
            System.out.println("Por favor, insira um valor");
        }

        try{
            valor = Double.parseDouble(args[0]);
            System.out.println("Valor: " + valor);
        }catch (NumberFormatException e){
            System.out.println("Valor invalido "+ e.getMessage());
        }


        Multiplos multiplos = new Multiplos(valor);
        MultiplosService multiplosService = new MultiplosService();


        multiplosService.ExibirMultiplos(multiplos);

    }
}