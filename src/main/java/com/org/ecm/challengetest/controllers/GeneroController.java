package com.org.ecm.challengetest.controllers;

import com.org.ecm.challengetest.dtos.GeneroDto;
import com.org.ecm.challengetest.services.GeneroService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/genero")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @PostMapping
    @Operation(
            summary = "Criar um novo genero",
            description = "Controller para Genero"
    )
    public GeneroDto cadastrar(@RequestBody GeneroDto generoDto){
        return generoService.cadastrarGenero(generoDto);
    }

    @GetMapping
    @Operation(
            summary = "Listar todos os generos",
            description = "Controller para Genero"
    )
    public List<GeneroDto> listaTodos(){
        return generoService.listarTodosGeneros();
    }

    @PutMapping
    @Operation(
            summary = "Atualizar um genero",
            description = "Controller para Genero"
    )
    public GeneroDto atualizar(@RequestBody GeneroDto generoDto){
        return generoService.atualizarGenero(generoDto);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar um genero",
            description = "Controller para Genero"
    )
    void deletar(@PathVariable Long id){
        generoService.deletarGenero(id);
    }
}
