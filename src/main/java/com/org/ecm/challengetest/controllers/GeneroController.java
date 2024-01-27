package com.org.ecm.challengetest.controllers;

import com.org.ecm.challengetest.dtos.GeneroDto;
import com.org.ecm.challengetest.services.GeneroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "genero")
@RestController
@RequestMapping(path = "/genero")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @PostMapping
    @Operation(
            summary = "Criar um novo genero",
            description = "Controller para Genero"
    )@ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida para pré-definições de validação"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor não identificado")
    })
    public GeneroDto cadastrar(@RequestBody GeneroDto generoDto){
        return generoService.cadastrarGenero(generoDto);
    }

    @GetMapping
    @Operation(
            summary = "Listar todos os generos",
            description = "Controller para Genero"
    )@ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida para pré-definições de validação"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor não identificado")
    })
    public List<GeneroDto> listaTodos(){
        return generoService.listarTodosGeneros();
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar um genero",
            description = "Controller para Genero"
    )@ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida para pré-definições de validação"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor não identificado")
    })
    public GeneroDto atualizar(@PathVariable Long id, @RequestBody GeneroDto generoDto){
        return generoService.atualizarGenero(id, generoDto);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar um genero",
            description = "Controller para Genero"
    )@ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida para pré-definições de validação"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor não identificado")
    })
    void deletar(@PathVariable Long id){
        generoService.deletarGenero(id);
    }
}
