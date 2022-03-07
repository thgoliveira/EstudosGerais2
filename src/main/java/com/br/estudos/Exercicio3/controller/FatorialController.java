package com.br.estudos.Exercicio3.controller;

import com.br.estudos.Exercicio3.service.FatorialService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(FatorialController.URL_MAPPING)
public class FatorialController {

    public static final String URL_MAPPING = "/fatorial";

    private final FatorialService fatorialService;

    public FatorialController(FatorialService fatorialService) {
        this.fatorialService = fatorialService;
    }

    @ApiOperation(value = "Retorna o calculo fatorial de um número qualquer.")
    @GetMapping("/vetor")
    public ResponseEntity calculaFatorial(@RequestParam final Integer valor){
        var response = fatorialService.calculaFatorial(valor);
        return ResponseEntity.ok(response);
    }
}
