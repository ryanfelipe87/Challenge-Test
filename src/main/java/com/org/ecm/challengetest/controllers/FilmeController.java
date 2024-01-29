package com.org.ecm.challengetest.controllers;

import com.org.ecm.challengetest.dtos.FilmeDto;
import com.org.ecm.challengetest.services.FilmeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/filme")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @PostMapping
    @Operation(
            summary = "Criar um novo filme",
            description = "Controller para endpoint Filme"
    )
    public FilmeDto createFilme(@RequestBody FilmeDto filmeDto){
        return filmeService.criarFilme(filmeDto);
    }

    @GetMapping("/")
    public ResponseEntity<String> home(){
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping(path = "/filtrar-por-nome/{nome}")
    @Operation(
            summary = "Listar filmes por nome",
            description = "Controller para endpoint Filme"
    )
    public List<FilmeDto> listarFilmesPorNome(@PathVariable String nome){
        return filmeService.filtrarFilmePorNome(nome);
    }

    @GetMapping(path = "/filtrar-disponiveis-por-genero/{nomeGenero}")
    @Operation(
            summary = "Listar filmes disponíveis por genero",
            description = "Controller para endpoint Filme"
    )
    public List<FilmeDto> listarDisponiveisPorGenero(@PathVariable String nomeGenero){
        return filmeService.filtroDisponiveisPorGenero(nomeGenero);
    }

    @GetMapping(path = "/filtrar-todos-indisponiveis")
    @Operation(
            summary = "Listar filmes indisponíveis",
            description = "Controller para endpoint Filme"
    )
    public List<FilmeDto> listarTodosIndisponiveis(){
        return filmeService.filtroTodosIndisponiveis();
    }

    @PutMapping
    @Operation(
            summary = "Atualizar filme",
            description = "Controller para endpoint Filme"
    )
    public FilmeDto atualizarFilme(@RequestBody FilmeDto filmeDto){
        return filmeService.atualizarFilme(filmeDto);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(
            summary = "Deletar um filme",
            description = "Controller para endpoint Filme"
    )
    public void deletarFilme(@PathVariable Long id){
        filmeService.deletarFilme(id);
    }
}
