package com.br.estudos.Exercicio5.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "VEICULO", schema = "VEICULOSDB")
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String veiculo;
    private String marca;
    private Integer ano;
    private String descricao;
    private Boolean status;
    private LocalDate criacao;
    private LocalDate atualizacao;

}
