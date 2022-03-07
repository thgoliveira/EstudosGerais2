package com.br.estudos.Exercicio5.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.br.estudos.Exercicio5.Exception.FabricanteNaoEncontradoException;
import com.br.estudos.Exercicio5.dto.VeiculoDTO;
import com.br.estudos.Exercicio5.entity.VeiculoEntity;
import com.br.estudos.Exercicio5.entity.constant.FabricanteEnum;
import com.br.estudos.Exercicio5.repository.VeiculoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {

    private final VeiculoRepository repository;

    public VeiculoService(VeiculoRepository repository) {
        this.repository = repository;
    }

    public List<VeiculoDTO> retornaTodosVeiculos() {
        var veiculoEntity = repository.findAll();
        return veiculoEntity.stream()
                .map(this::entidadeParaDto)
                .collect(Collectors.toList());
    }

    public VeiculoDTO cadastroDeVeiculo(final VeiculoDTO veiculoDTO) {
        if(veiculoDTO.getId() == null){
            veiculoDTO.setCriacao(LocalDate.now());
            return entidadeParaDto(repository.save(dtoParaEntidade(veiculoDTO)));
        }

        var veiculo = retornaVeiculoPorId(veiculoDTO.getId());
        if(veiculo != null){
            veiculo.setAtualizacao(LocalDate.now());
            return entidadeParaDto(repository.save(veiculo));
        } else {
            throw new RuntimeException("Erro ao cadastro ou alterar o veiculo!");
        }
    }

    public void removerVeiculo(final Long id) {
        repository.deleteById(id);
    }

    private VeiculoEntity retornaVeiculoPorId(final Long id){
        return repository.getById(id);
    }

    public List<VeiculoDTO> retornaVeiculosNaoVendidos() {
        return repository.retornaVeiculosNaoVendidos()
                .stream()
                .map(this::entidadeParaDto)
                .collect(Collectors.toList());
    }

    public List<VeiculoDTO> retornaVeiculosPorDecada(final Integer ano) {
        var anoString = "";
        var array = ano.toString().split("");
        for (int i = 0; i < array.length - 1; i++) {
            anoString = anoString.concat(array[i]);
        }
        var inicioDecada = Integer.valueOf(anoString.concat("0"));
        var fimDecada = Integer.valueOf(anoString.concat("9"));

        return repository.retornaVeiculosPorDecada(inicioDecada, fimDecada)
                .stream()
                .map(this::entidadeParaDto)
                .collect(Collectors.toList());
    }

    public String retornaVeiculosPorFabricante(final String marca) {
        return "A fabricante: " + marca.toUpperCase() + " tem " + repository.retornaVeiculosPorFabricante(marca.toLowerCase()) + " veiculo(s) registrado(s).";
    }

    public List<VeiculoDTO> retornaVeiculosRegistradosComSeteDias() {
        var dataAtual = LocalDate.now();
        return repository.retornaVeiculosRegistradosComSeteDias(dataAtual.minusWeeks(1), dataAtual)
                .stream()
                .map(this::entidadeParaDto)
                .collect(Collectors.toList());
    }

    private VeiculoDTO entidadeParaDto(final VeiculoEntity veiculoEntity){
        return VeiculoDTO.builder()
                .id(veiculoEntity.getId())
                .veiculo(veiculoEntity.getVeiculo())
                .marca(veiculoEntity.getMarca())
                .ano(veiculoEntity.getAno())
                .descricao(veiculoEntity.getDescricao())
                .status(veiculoEntity.getStatus())
                .criacao(veiculoEntity.getCriacao())
                .atualizacao(veiculoEntity.getAtualizacao())
                .build();
    }

    private VeiculoEntity dtoParaEntidade(final VeiculoDTO veiculoDTO){
        return VeiculoEntity.builder()
                .veiculo(veiculoDTO.getVeiculo())
                .marca(veiculoDTO.getMarca())
                .ano(veiculoDTO.getAno())
                .descricao(veiculoDTO.getDescricao())
                .status(veiculoDTO.getStatus())
                .criacao(veiculoDTO.getCriacao())
                .atualizacao(veiculoDTO.getAtualizacao())
                .build();
    }
}
