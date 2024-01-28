package com.org.ecm.challengetest.repositories;

import com.org.ecm.challengetest.models.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    Filme findByCodigo(String codigo);

    List<Filme> findByNomeContaining(String nome);
    List<Filme> findByGenerosNomeAndDisponibilidade(String nome, boolean disponibilidade);

    List<Filme> findByDisponibilidade(boolean disponibilidade);
}
