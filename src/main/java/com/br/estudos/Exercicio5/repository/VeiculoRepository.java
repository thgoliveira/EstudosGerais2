package com.br.estudos.Exercicio5.repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.br.estudos.Exercicio5.dto.VeiculoDTO;
import com.br.estudos.Exercicio5.entity.VeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<VeiculoEntity, Long> {

    @Query("Select v FROM VeiculoEntity v WHERE v.status = false")
    List<VeiculoEntity> retornaVeiculosNaoVendidos();

    @Query("Select v FROM VeiculoEntity v WHERE v.ano BETWEEN :inicioDecada AND :fimDecada")
    List<VeiculoEntity> retornaVeiculosPorDecada(final Integer inicioDecada, final Integer fimDecada);

    @Query("Select count(v) FROM VeiculoEntity v WHERE lower(v.marca) = :marca")
    Integer retornaVeiculosPorFabricante(final String marca);

    @Query("Select distinct lower(v.marca) FROM VeiculoEntity v")
    ArrayList<String> retornaMarcasRegistradas();

    @Query("Select v FROM VeiculoEntity v WHERE v.criacao >= :ultimaSemana AND v.criacao <= :dataAtual")
    List<VeiculoEntity> retornaVeiculosRegistradosComSeteDias(LocalDate ultimaSemana, LocalDate dataAtual);
}
