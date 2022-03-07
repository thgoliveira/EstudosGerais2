package com.br.estudos.Exercicio5.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.br.estudos.Exercicio5.dto.VeiculoDTO;
import com.br.estudos.Exercicio5.entity.VeiculoEntity;
import com.br.estudos.Exercicio5.repository.VeiculoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class VeiculoServiceTest {

    private final VeiculoRepository repository = mock(VeiculoRepository.class);
    private final VeiculoService service = new VeiculoService(this.repository);

    @Test
    void retornaTodosVeiculos() {
        when(repository.findAll()).thenReturn(List.of(veiculoEntity1()));

        assertEquals(List.of(veiculoDTO(veiculoEntity1())), service.retornaTodosVeiculos());
    }

    @Test
    void cadastroDeVeiculo() {
        var veiculoEntity = VeiculoEntity.builder()
                .veiculo("Sandero")
                .ano(2019)
                .atualizacao(LocalDate.now())
                .criacao(LocalDate.now())
                .descricao("Meu carro")
                .marca("Renault")
                .status(false)
                .build();

        var veiculoAlterado = VeiculoEntity.builder()
                .id(4L)
                .veiculo("Sandero")
                .ano(2019)
                .atualizacao(LocalDate.now())
                .criacao(LocalDate.now())
                .descricao("Meu carro novo")
                .marca("Renault")
                .status(true)
                .build();

        when(repository.save(veiculoEntity)).thenReturn(veiculoEntity1());

        assertEquals(veiculoDTO(veiculoEntity1()), service.cadastroDeVeiculo(veiculoDTO(veiculoEntity)));

        when(repository.getById(veiculoAlterado.getId())).thenReturn(veiculoAlterado);
        when(repository.save(veiculoAlterado)).thenReturn(veiculoAlterado);

        assertEquals(veiculoDTO(veiculoAlterado), service.cadastroDeVeiculo(veiculoDTO(veiculoAlterado)));
    }

    @Test
    void retornaVeiculosNaoVendidos() {
        when(repository.retornaVeiculosNaoVendidos()).thenReturn(List.of(veiculoEntity1()));

        assertEquals(List.of(veiculoDTO(veiculoEntity1())), service.retornaVeiculosNaoVendidos());
    }

    @Test
    void retornaVeiculosPorDecada() {
        when(repository.retornaVeiculosPorDecada(2010, 2019)).thenReturn(List.of(veiculoEntity1(), veiculoEntity2()));

        assertEquals(List.of(veiculoDTO(veiculoEntity1()), veiculoDTO(veiculoEntity2())), service.retornaVeiculosPorDecada(2015));
    }

    @Test
    void retornaVeiculosPorFabricante() {
        assertEquals("A fabricante: RENAULT tem 0 veiculo(s) registrado(s).", service.retornaVeiculosPorFabricante(veiculoEntity1().getMarca()));
    }

    @Test
    void retornaVeiculosRegistradosComSeteDias() {
        var dataAtual = LocalDate.now();
        when(repository.retornaVeiculosRegistradosComSeteDias(dataAtual.minusWeeks(1), dataAtual)).thenReturn(List.of(veiculoEntity1(), veiculoEntity2()));

        assertEquals(List.of(veiculoDTO(veiculoEntity1()), veiculoDTO(veiculoEntity2())), service.retornaVeiculosRegistradosComSeteDias());
    }

    private VeiculoEntity veiculoEntity1() {
        return VeiculoEntity.builder()
                .id(1L)
                .veiculo("Sandero")
                .ano(2019)
                .atualizacao(LocalDate.now())
                .criacao(LocalDate.now())
                .descricao("Meu carro")
                .marca("Renault")
                .status(false)
                .build();
    }

    private VeiculoEntity veiculoEntity2() {
        return VeiculoEntity.builder()
                .id(2L)
                .veiculo("Fiesta")
                .ano(2015)
                .atualizacao(LocalDate.now())
                .criacao(LocalDate.now())
                .descricao("Outro carro")
                .marca("Ford")
                .status(true)
                .build();
    }

    private VeiculoDTO veiculoDTO(final VeiculoEntity veiculoEntity){
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
}