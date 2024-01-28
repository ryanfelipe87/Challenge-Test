package com.org.ecm.challengetest.controllers;

import com.org.ecm.challengetest.dtos.FilmeDto;
import com.org.ecm.challengetest.services.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/filme")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @PostMapping
    public FilmeDto createFilme(@RequestBody FilmeDto filmeDto){
        return filmeService.criarFilme(filmeDto);
    }

    @GetMapping(path = "/filtroPorNome/{nome}")
    public List<FilmeDto> listFilmePorNome(@PathVariable String nome){
        return filmeService.filtrarFilmePorNome(nome);
    }

    @GetMapping(path = "/filtroDisponiveisPorGenero/{nomeGenero}")
    public List<FilmeDto> filtroDisponiveisPorGenero(@PathVariable String nomeGenero){
        return filmeService.filtroDisponiveisPorGenero(nomeGenero);
    }

    @GetMapping(path = "/filtroTodosIndisponiveis")
    public List<FilmeDto> filtroTodosIndisponiveis(){
        return filmeService.filtroTodosIndisponiveis();
    }

    @PutMapping
    public FilmeDto atualizar(@RequestBody FilmeDto filmeDto){
        return filmeService.atualizarFilme(filmeDto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        filmeService.deletarFilme(id);
    }
}
