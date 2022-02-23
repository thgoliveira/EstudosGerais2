package com.br.estudos.Exercicio3.service;

import org.springframework.stereotype.Service;

@Service
public class FatorialService {


    public Integer calculaFatorial(Integer valor) {
        var fatorial = 1;
        if(valor == 0){
            valor = 1;
            return valor;
        }
        for (int i = 1; i < valor; i++) {
            fatorial = fatorial * (i+1);
        }

        return fatorial;
    }
}
