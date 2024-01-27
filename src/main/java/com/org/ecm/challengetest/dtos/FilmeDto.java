package com.org.ecm.challengetest.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmeDto {

    private Long id;
    private String codigo;
    private String nome;
    private String sinopse;
    private int anoLancamento;
    private String classificacaoIndicativa;
    private long duracao;
    private boolean disponibilidade;
}
