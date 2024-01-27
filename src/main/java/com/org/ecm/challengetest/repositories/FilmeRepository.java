package com.org.ecm.challengetest.repositories;

import com.org.ecm.challengetest.dtos.FilmeDto;
import com.org.ecm.challengetest.models.Filme;
import com.org.ecm.challengetest.models.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    Optional<Filme> findByCodigo(String codigo);

    void deleteByCodigo(String codigo);

//    Filme findByNome(String nome);
//
//    List<FilmeDto> findByGeneros(Genero genero);
//
//    List<FilmeDto> buscarFilmesSemDisponibilidade();
}
