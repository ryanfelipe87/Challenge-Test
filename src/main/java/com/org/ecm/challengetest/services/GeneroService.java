package com.org.ecm.challengetest.services;

import com.org.ecm.challengetest.dtos.GeneroDto;
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

    public GeneroDto atualizarGenero(Long id, GeneroDto generoDto){
        Optional<Genero> generoOptional = generoRepository.findById(id);
        if(generoOptional.isPresent() && id.equals(generoDto.getId())){
            Genero genero = convertToEntity(generoDto);
            genero = generoRepository.save(genero);

            return convertToDto(genero);
        }
        return null;
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
