package com.br.estudos.Exercicio4.controller;

import com.br.estudos.Exercicio4.service.MultiplosService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MultiplosController.URL_MAPPING)
public class MultiplosController {

    public static final String URL_MAPPING = "/multiplos";

    private final MultiplosService multiplosService;

    public MultiplosController(MultiplosService multiplosService) {
        this.multiplosService = multiplosService;
    }

    @ApiOperation(value = "Retorna a soma dos números que são multiplos de 3 ou 5 e menor que o valor informado.")
    @GetMapping
    public ResponseEntity retornaMultiplos(@RequestParam final Integer valor){
        var response = multiplosService.retornaMultiplos(valor);
        return ResponseEntity.ok(response);
    }
}
