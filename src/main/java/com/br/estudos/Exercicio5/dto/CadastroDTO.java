package com.br.estudos.Exercicio5.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CadastroDTO {

    private String veiculo;
    private Integer ano;
    private String descricao;

}
