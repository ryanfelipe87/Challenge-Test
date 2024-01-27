package com.org.ecm.challengetest.services;

import com.org.ecm.challengetest.dtos.FilmeDto;
import com.org.ecm.challengetest.dtos.GeneroDto;
import com.org.ecm.challengetest.models.Filme;
import com.org.ecm.challengetest.models.Genero;
import com.org.ecm.challengetest.repositories.FilmeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class FilmeService {

    @Autowired
    private GeneroService generoService;

    @Autowired
    private FilmeRepository filmeRepository;

    public FilmeDto criarFilme(FilmeDto filmeDto){
        String regex = "^[A-Z]{3}-[0-9]{3}(?<!000)$";
        if(!Pattern.matches(regex, filmeDto.getCodigo()))
            throw new IllegalArgumentException("O código do filme não está no formato válido");//400

        Filme filme = convertToEntity(filmeDto);
        filme = filmeRepository.save(filme);
        return convertToDto(filme);
    }

//    public FilmeDto listarFilmePorNome(FilmeDto filmeDto){
//        Filme filme = filmeRepository.findByNome(filmeDto.getNome());
//        if(filme != null){
//            return convertToDto(filme);
//        }
//        return null;
//    }
//
//    public List<FilmeDto> listarFilmePorGenero(Genero genero){
//        return filmeRepository.findByGeneros(genero);
//    }
//
//    public List<FilmeDto> ListarFilmeIndisponivel(FilmeDto filmeDto){
//        return filmeRepository.buscarFilmesSemDisponibilidade();
//    }

    public FilmeDto atualizarFilme(FilmeDto filmeDto){
        Optional<Filme> filmeOptional = filmeRepository.findById(filmeDto.getId());
        if(filmeOptional.isPresent()){
            Filme filme = convertToEntity(filmeDto);
            filme = filmeRepository.save(filme);
            return convertToDto(filme);
        }
        return null;
    }

    public void deletarFilme(String codigo){
        filmeRepository.deleteByCodigo(codigo);
    }

    private FilmeDto convertToDto(Filme filme){
        FilmeDto filmeDto = new FilmeDto();
        BeanUtils.copyProperties(filme, filmeDto);
        setGeneroNoFilmeDto(filme, filmeDto);
        return filmeDto;
    }

    private void setGeneroNoFilmeDto(Filme filme, FilmeDto filmeDto) {
        Set<GeneroDto> generos = new HashSet<>();
        for (Genero genero : filme.getGeneros()) {
            GeneroDto generoDto = new GeneroDto();
            BeanUtils.copyProperties(genero, generoDto);
            generos.add(generoDto);
        }
        filmeDto.setGeneros(generos);
    }

    private Filme convertToEntity(FilmeDto filmeDto){
        Filme filme = new Filme();
        BeanUtils.copyProperties(filmeDto, filme);
        setGenerosNoFilme(filmeDto, filme);
        return filme;
    }

    private void setGenerosNoFilme(FilmeDto filmeDto, Filme filme) {
        List<Genero> generos = new ArrayList<>();
        for (GeneroDto generoDto : filmeDto.getGeneros()) {
            Genero genero = generoService.buscarPorNome(generoDto.getNome());
            generos.add(genero);
        }
        filme.setGeneros(generos);
    }
}
