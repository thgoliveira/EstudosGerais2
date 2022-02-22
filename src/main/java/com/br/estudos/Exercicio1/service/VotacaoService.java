package com.br.estudos.Exercicio1.service;

import org.springframework.stereotype.Service;

@Service
public class VotacaoService {

    public Double calculaVotosValidos(final Integer totalEleitores, final Integer eleitoresValidos) {
        return (converteValores(eleitoresValidos) / converteValores(totalEleitores)) * 100;
    }

    public Double calculaVotosBrancos(final Integer totalEleitores, final Integer votosBrancos) {
        return (converteValores(votosBrancos) / converteValores(totalEleitores)) * 100;
    }

    public Double calculaVotosNulos(final Integer totalEleitores, final Integer votosNulos) {
        return (converteValores(votosNulos) / converteValores(totalEleitores)) * 100;
    }

    private Double converteValores(Integer integer){
        return Double.valueOf(integer);
    }
}
