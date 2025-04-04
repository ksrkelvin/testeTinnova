package com.exercicio3Fatorial.usecase;

import com.exercicio3Fatorial.model.Fatorial;

public class CalculeFatorialUseCase {

    public void GenerateFatorialUseCase(Fatorial fatorial) {
        int valorFat = fatorial.getValor();
        StringBuilder textFat  = new StringBuilder();
        int result = 1;

        if (fatorial.getValor() == 0){
            fatorial.setFatorial("0!");
            fatorial.setValor(result);
            return;
        }
        textFat.append(valorFat).append( "! = ");


        for (int i = valorFat; i >= 1 ; i--){
            result *= i;
            textFat.append(i);
            if (i > 1){
                textFat.append(" X ");
            }

        }
        fatorial.setFatorial(textFat.toString());
        fatorial.setValor(result);


    }


}
