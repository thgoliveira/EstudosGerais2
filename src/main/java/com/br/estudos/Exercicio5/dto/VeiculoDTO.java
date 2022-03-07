package com.br.estudos.Exercicio5.dto;

import java.time.LocalDate;

import com.br.estudos.Exercicio5.entity.constant.FabricanteEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VeiculoDTO {

    private Long id;
    private String veiculo;
    private String marca;
    private Integer ano;
    private String descricao;
    private Boolean status;
    private LocalDate criacao;
    private LocalDate atualizacao;

}
