package com.br.estudos.Exercicio2.controller;

import java.util.List;

import com.br.estudos.Exercicio2.service.IteracaoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(IteracaoController.URL_MAPPING)
public class IteracaoController {

    public static final String URL_MAPPING = "/iteracao";

    private final IteracaoService iteracaoService;

    public IteracaoController(IteracaoService iteracaoService) {
        this.iteracaoService = iteracaoService;
    }

    @ApiOperation(value = "Retorna o percentual de votos válidos em relação ao total de eleitores.")
    @GetMapping("/vetor")
    public ResponseEntity ordenaVetor(final int[] arrayInteger){
        var response = iteracaoService.ordenarVetorNumerico(arrayInteger);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Retorna o percentual de votos válidos em relação ao total de eleitores.")
    @GetMapping("/vetorProposto")
    public ResponseEntity ordenaVetorProposto(final int[] arrayInteger){
        var response = iteracaoService.ordenarVetorNumericoProposto(arrayInteger);
        return ResponseEntity.ok(response);
    }
}
