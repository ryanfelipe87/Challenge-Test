package com.org.ecm.challengetest.controllers;

import com.org.ecm.challengetest.dtos.FilmeDto;
import com.org.ecm.challengetest.services.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/filme")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @PostMapping
    public FilmeDto createFilme(@RequestBody FilmeDto filmeDto){
        return filmeService.criarFilme(filmeDto);
    }

//    @GetMapping(path = "/byNome")
//    public List<FilmeDto> listFilmePorNome(FilmeDto filmeDto){
//        return (List<FilmeDto>) filmeService.listarFilmePorNome(filmeDto);
//    }
//
//    @GetMapping(path = "/byGenero")
//    public List<FilmeDto> listarPorGenero(Genero genero){
//        return filmeService.listarFilmePorGenero(genero);
//    }

    @PutMapping
    public FilmeDto atualizar(@RequestBody FilmeDto filmeDto){
        return filmeService.atualizarFilme(filmeDto);
    }

    @DeleteMapping("/{codigo}")
    void deletar(@PathVariable String codigo){
        filmeService.deletarFilme(codigo);
    }
}
