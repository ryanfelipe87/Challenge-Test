package com.org.ecm.challengetest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nome;

//    @ManyToMany(mappedBy = "generos")
//    private Set<Filme> filmes = new HashSet<>();
}
