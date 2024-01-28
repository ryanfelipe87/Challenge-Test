package com.org.ecm.challengetest.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GeneroDto {

    private Long id;

    @NotBlank
    private String nome;
}
