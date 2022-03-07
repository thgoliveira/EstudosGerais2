package com.br.estudos.Exercicio5.controller;

import javax.websocket.server.PathParam;

import com.br.estudos.Exercicio5.Exception.FabricanteNaoEncontradoException;
import com.br.estudos.Exercicio5.dto.CadastroDTO;
import com.br.estudos.Exercicio5.dto.VeiculoDTO;
import com.br.estudos.Exercicio5.entity.constant.FabricanteEnum;
import com.br.estudos.Exercicio5.service.VeiculoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(VeiculoController.URL_MAPPING)
public class VeiculoController {

    public static final String URL_MAPPING = "/veiculos";

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @ApiOperation(value = "Retorna todos os veiculos registrados.")
    @GetMapping
    public ResponseEntity retornaTodosVeiculos(){
        var response = veiculoService.retornaTodosVeiculos();
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Inseri um novo veiculo a base de dados.")
    @PostMapping
    public VeiculoDTO cadastrarVeiculo(@RequestParam FabricanteEnum fabricanteEnum,
                                       @RequestBody CadastroDTO cadastroDTO){
        var veiculoDTO = VeiculoDTO.builder()
                .veiculo(cadastroDTO.getVeiculo())
                .ano(cadastroDTO.getAno())
                .descricao(cadastroDTO.getDescricao())
                .marca(fabricanteEnum.toString())
                .status(false)
                .build();
       return veiculoService.cadastroDeVeiculo(veiculoDTO);
    }

    @ApiOperation(value = "Alterar dados de um veiculo.")
    @PutMapping
    public ResponseEntity atualizarDadosVeiculo(@RequestBody VeiculoDTO veiculoDTO){
        var response = veiculoService.cadastroDeVeiculo(veiculoDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @ApiOperation(value = "Remove dados de um veiculo.")
    @DeleteMapping("/{id}")
    public ResponseEntity excluirVeiculo(@PathParam("id") final Long id){
        veiculoService.removerVeiculo(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Veiculo removido da base!");
    }

    @ApiOperation(value = "Retorna todos os veiculos não vendidos.")
    @GetMapping("/naoVendidos")
    public ResponseEntity retornaVeiculosNaoVendidos(){
        var response = veiculoService.retornaVeiculosNaoVendidos();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @ApiOperation(value = "Retorna todos os veiculos fabricados nessa década.")
    @GetMapping("/decada")
    public ResponseEntity retornaVeiculosPorDecada(@RequestParam final Integer ano){
        var response = veiculoService.retornaVeiculosPorDecada(ano);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @ApiOperation(value = "Retorna quantidade de veiculos do fabricante.")
    @GetMapping("/fabricante")
    public String retornaVeiculosPorFabricante(@RequestParam final FabricanteEnum fabricanteEnum){
        return veiculoService.retornaVeiculosPorFabricante(fabricanteEnum.toString());
    }

    @ApiOperation(value = "Retorna quantidade de veiculos registrados na ultima semana.")
    @GetMapping("/ultimaSemana")
    public ResponseEntity retornaVeiculosRegistradosComSeteDias() {
        var response = veiculoService.retornaVeiculosRegistradosComSeteDias();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
