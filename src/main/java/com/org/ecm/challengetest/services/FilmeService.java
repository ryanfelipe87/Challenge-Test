package com.org.ecm.challengetest.services;

import com.org.ecm.challengetest.dtos.FilmeDto;
import com.org.ecm.challengetest.dtos.GeneroDto;
import com.org.ecm.challengetest.enums.ClassificacaoIndicativa;
import com.org.ecm.challengetest.exceptions.BadRequestException;
import com.org.ecm.challengetest.exceptions.InternalServerErrorException;
import com.org.ecm.challengetest.exceptions.NotFoundException;
import com.org.ecm.challengetest.models.Filme;
import com.org.ecm.challengetest.models.Genero;
import com.org.ecm.challengetest.repositories.FilmeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        validarCodigo(filmeDto);
        Filme filme = convertToEntity(filmeDto);
        filme = filmeRepository.save(filme);
        return convertToDto(filme);
    }

    private void validarCodigo(FilmeDto filmeDto) {
        String regex = "^[A-Z]{3}-[0-9]{3}(?<!000)$";
        if(!Pattern.matches(regex, filmeDto.getCodigo()))
            throw new BadRequestException("O código do filme não está no formato válido");

        Filme filmeResponse = filmeRepository.findByCodigo(filmeDto.getCodigo());
        if(filmeResponse != null)
            throw new BadRequestException("Filme já existente com esse código: " + filmeDto.getCodigo());
    }

    public FilmeDto atualizarFilme(FilmeDto filmeDto){
        Optional<Filme> filmeOptional = filmeRepository.findById(filmeDto.getId());
        if(filmeOptional.isPresent()){
            Filme filme = convertToEntity(filmeDto);
            filme = filmeRepository.save(filme);
            return convertToDto(filme);
        }
        throw new InternalServerErrorException("Filme não está disponível para atualização");
    }

    public void deletarFilme(Long id){
        Optional<Filme> filmeOptional = filmeRepository.findById(id);
        if(filmeOptional.isPresent()){
            filmeRepository.deleteById(id);
        }
    }

    private FilmeDto convertToDto(Filme filme){
        FilmeDto filmeDto = new FilmeDto();
        BeanUtils.copyProperties(filme, filmeDto);
        setGeneroNoFilmeDto(filme, filmeDto);
        filmeDto.setClassificacaoIndicativa(filme.getClassificacaoIndicativa().getCodigo());
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
        validarAnoLancamento(filmeDto.getAnoLancamento());
        Filme filme = new Filme();
        BeanUtils.copyProperties(filmeDto, filme);
        setGenerosNoFilme(filmeDto, filme);
        ClassificacaoIndicativa classificacaoIndicativa = ClassificacaoIndicativa.getByCodigo(filmeDto.getClassificacaoIndicativa());
        filme.setClassificacaoIndicativa(classificacaoIndicativa);
        return filme;
    }

    private void setGenerosNoFilme(FilmeDto filmeDto, Filme filme) {
        Set<Genero> generos = new HashSet<>();
        for (GeneroDto generoDto : filmeDto.getGeneros()) {
            Genero genero = generoService.buscarPorNome(generoDto.getNome());
            generos.add(genero);
        }
        filme.setGeneros(new ArrayList<>(generos));
    }

    public List<FilmeDto> filtrarFilmePorNome(String nome) {
        List<Filme> filmes = filmeRepository.findByNomeContaining(nome);
        return filmes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<FilmeDto> filtroDisponiveisPorGenero(String nomeGenero) {
        List<Filme> filmes = filmeRepository.findByGenerosNomeAndDisponibilidade(nomeGenero, true);
        return filmes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<FilmeDto> filtroTodosIndisponiveis() {
        List<Filme> filmes = filmeRepository.findByDisponibilidade(false);
        return filmes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private void validarAnoLancamento(Integer anoLancamento){
        if(anoLancamento < 1900 || anoLancamento > LocalDate.now().getYear() || anoLancamento.toString().length() > 4){
            throw new BadRequestException("Ano de lançamento inválido " + anoLancamento);
        }
    }
}
