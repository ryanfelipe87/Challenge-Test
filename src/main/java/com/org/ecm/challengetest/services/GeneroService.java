package com.org.ecm.challengetest.services;

import com.org.ecm.challengetest.dtos.GeneroDto;
import com.org.ecm.challengetest.exceptions.BadRequestException;
import com.org.ecm.challengetest.exceptions.NotFoundException;
import com.org.ecm.challengetest.models.Genero;
import com.org.ecm.challengetest.repositories.GeneroRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    public GeneroDto cadastrarGenero(GeneroDto generoDto){
        Genero generoResponse = generoRepository.findByNome(generoDto.getNome());
        if(generoResponse != null)
            throw new BadRequestException("Genero existente com esse nome: " + generoDto.getNome());

        Genero genero = convertToEntity(generoDto);
        genero = generoRepository.save(genero);
        return convertToDto(genero);
    }

    public List<GeneroDto> listarTodosGeneros(){
        List<Genero> generoList = generoRepository.findAll();
        return generoList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public GeneroDto atualizarGenero(GeneroDto generoDto){
        Optional<Genero> generoOptional = generoRepository.findById(generoDto.getId());
        if(generoOptional.isPresent()){
            Genero genero = convertToEntity(generoDto);
            genero = generoRepository.save(genero);

            return convertToDto(genero);
        }
        throw new NotFoundException("Genero indisponível para atualização");
    }

    public void deletarGenero(Long id){
        generoRepository.deleteById(id);
    }

    private GeneroDto convertToDto(Genero genero){
        GeneroDto generoDto = new GeneroDto();
        BeanUtils.copyProperties(genero, generoDto);
        return generoDto;
    }

    private Genero convertToEntity(GeneroDto generoDto){
        Genero genero = new Genero();
        BeanUtils.copyProperties(generoDto, genero);
        return genero;
    }

    public Genero buscarPorNome(String nomeGenero) {
        return generoRepository.findByNome(nomeGenero);
    }
}
