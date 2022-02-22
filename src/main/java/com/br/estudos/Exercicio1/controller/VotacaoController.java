package com.br.estudos.Exercicio1.controller;

import com.br.estudos.Exercicio1.service.VotacaoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(VotacaoController.URL_MAPPING)
public class VotacaoController {

    public static final String URL_MAPPING = "/votacao";

    private final VotacaoService votacaoService;

    public VotacaoController(VotacaoService votacaoService) {
        this.votacaoService = votacaoService;
    }

    @ApiOperation(value = "Retorna o percentual de votos válidos em relação ao total de eleitores.")
    @GetMapping("/validos")
    public ResponseEntity calculaVotosValidos(@RequestParam final Integer totalEleitores,
                                              @RequestParam final Integer eleitoresValidos){
        var response = votacaoService.calculaVotosValidos(totalEleitores, eleitoresValidos);
        return ResponseEntity.ok("O percentual de votos válidos em relação ao total de eleitores é de: " + response + "%.");
    }

    @ApiOperation(value = "Retorna o percentual de votos brancos em relação ao total de eleitores.")
    @GetMapping("/brancos")
    public ResponseEntity calculaVotosBrancos(@RequestParam final Integer totalEleitores,
                                              @RequestParam final Integer votosBrancos){
        var response = votacaoService.calculaVotosBrancos(totalEleitores, votosBrancos);
        return ResponseEntity.ok("O percentual de votos brancos em relação ao total de eleitores é de: " + response + "%.");
    }

    @ApiOperation(value = "Retorna o percentual de votos nulos em relação ao total de eleitores.")
    @GetMapping("/nulos")
    public ResponseEntity calculaVotosNulos(@RequestParam final Integer totalEleitores,
                                            @RequestParam final Integer votosNulos){
        var response = votacaoService.calculaVotosNulos(totalEleitores, votosNulos);
        return ResponseEntity.ok("O percentual de votos nulos em relação ao total de eleitores é de: " + response + "%.");
    }
}
