package com.org.ecm.challengetest.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilmeDto {

    private Long id;

    @NotBlank
    private String codigo;

    @NotBlank
    private String nome;

    @NotBlank
    private String sinopse;

    @NotNull
    @Positive
    private Integer anoLancamento;

    @NotBlank
    private String classificacaoIndicativa;

    @Positive
    @NotNull
    private Long duracao;

    @NotNull
    private Boolean disponibilidade;

    @NotNull
    private Set<GeneroDto> generos;
}
