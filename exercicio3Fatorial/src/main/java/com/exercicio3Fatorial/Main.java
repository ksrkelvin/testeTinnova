package com.exercicio3Fatorial;

import com.exercicio3Fatorial.model.Fatorial;
import com.exercicio3Fatorial.service.FatorialService;

public class Main {
    public static void main(String[] args) {

        Fatorial fatorial1 = new Fatorial(0);
        Fatorial fatorial2 = new Fatorial(1);
        Fatorial fatorial3 = new Fatorial(2);
        Fatorial fatorial4 = new Fatorial(3);
        Fatorial fatorial5 = new Fatorial(4);
        Fatorial fatorial6 = new Fatorial(5);
        Fatorial fatorial7 = new Fatorial(6);

        FatorialService fatorialService = new FatorialService();

        Fatorial[] fatorials = {fatorial1, fatorial2, fatorial3,fatorial4,fatorial5,fatorial6,fatorial7};

        for (Fatorial fatorial : fatorials){
            fatorialService.ExibirFatorial(fatorial);
        }



    }
}