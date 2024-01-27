package com.org.ecm.challengetest.controllers;

import com.org.ecm.challengetest.dtos.GeneroDto;
import com.org.ecm.challengetest.services.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/genero")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @PostMapping
    public GeneroDto criar(@RequestBody GeneroDto generoDto){
        return generoService.criarGenero(generoDto);
    }

    @GetMapping
    public List<GeneroDto> listaTodos(){
        return generoService.listarTodosGeneros();
    }

    @PutMapping("/{id}")
    public GeneroDto atualizar(@PathVariable Long id, @RequestBody GeneroDto generoDto){
        return generoService.atualizarGenero(id, generoDto);
    }

    @DeleteMapping("/{id}")
    void deletar(@PathVariable Long id){
        generoService.deletarGenero(id);
    }
}
