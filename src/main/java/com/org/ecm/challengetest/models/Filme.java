package com.org.ecm.challengetest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @Column(unique = true)
    private String codigo;

    @Column
    private String nome;

    @Column
    private String sinopse;

    @Column
    private Integer anoLancamento;

    @Column
    private String classificacaoIndicativa;

    @Column
    private Long duracao;

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
