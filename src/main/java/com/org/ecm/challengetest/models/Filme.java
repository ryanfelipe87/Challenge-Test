package com.org.ecm.challengetest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String codigo;

    @NotBlank
    @Column
    private String nome;

    @NotBlank
    @Column
    private String sinopse;

    @Positive
    @NotNull
    @Column
    private Integer anoLancamento;

    @NotBlank
    @Column
    private String classificacaoIndicativa;

    @Positive
    @NotNull
    @Column
    private Long duracao;

    @NotNull
    @Column(columnDefinition = "BOOLEAN DEFAULT false", nullable = false)
    private Boolean disponibilidade;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "filme_genero",
            joinColumns = @JoinColumn(name = "filme_id"),
            inverseJoinColumns = @JoinColumn(name = "genero_id")
    )
    private List<Genero> generos;
}
