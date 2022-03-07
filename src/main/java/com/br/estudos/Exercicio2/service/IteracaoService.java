package com.br.estudos.Exercicio2.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class IteracaoService {

    public List ordenarVetorNumerico(final int[] arrayInteger) {
        Arrays.sort(arrayInteger);
        return List.of(arrayInteger);
    }

    public List ordenarVetorNumericoProposto(final int[] arrayInteger){
        var temp = 0;
        for (int i = 0; i < arrayInteger.length; i++) {
            for (int j = 0; j < arrayInteger.length - 1; j++) {
                if(arrayInteger[j] > arrayInteger[j + 1]){
                    temp = arrayInteger[j];
                    arrayInteger[j] = arrayInteger[j + 1];
                    arrayInteger[j + 1] = temp;
                }
            }
        }
        return Arrays.asList(arrayInteger);
    }
}
