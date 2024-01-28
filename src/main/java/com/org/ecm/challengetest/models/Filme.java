package com.org.ecm.challengetest.models;

import com.org.ecm.challengetest.enums.ClassificacaoIndicativa;
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

    @Column(unique = true, nullable = false)
    private String codigo;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sinopse;

    @Column(nullable = false)
    private Integer anoLancamento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClassificacaoIndicativa classificacaoIndicativa;

    @Column(nullable = false)
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
