package com.br.estudos.Exercicio4.service;

import org.springframework.stereotype.Service;

@Service
public class MultiplosService {

    public Integer retornaMultiplos(Integer valor) {
        var somaValores = 0;
        for (int i = 0; i < valor; i++) {
            if(i % 3 == 0 || i % 5 == 0){
                somaValores += i;
            }
        }
        return somaValores;
    }
}
