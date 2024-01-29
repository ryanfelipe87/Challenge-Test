package com.org.ecm.challengetest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.ecm.challengetest.dtos.GeneroDto;
import com.org.ecm.challengetest.services.GeneroService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GeneroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GeneroService generoService;


    @Test
    void cadastrar() throws Exception {
        GeneroDto generoDto = new GeneroDto();
        generoDto.setNome("Ação");
        generoDto.setId(1L);

        Mockito.when(generoService.cadastrarGenero(Mockito.any(GeneroDto.class)))
                .thenReturn(generoDto);

        this.mockMvc.perform(post("/genero")
                        .content(asJsonString(generoDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Ação"));
    }


    @Test
    void atualizar()throws Exception{
        GeneroDto generoDtoMockService = new GeneroDto();
        generoDtoMockService.setNome("Ação");
        generoDtoMockService.setId(1L);

        GeneroDto generoDtoResponse = new GeneroDto();
        generoDtoResponse.setNome("Suspense");
        generoDtoResponse.setId(1L);

        Mockito.when(generoService.atualizarGenero(Mockito.any(GeneroDto.class)))
                .thenReturn(generoDtoMockService);

        this.mockMvc.perform(put("/genero")
                        .content(asJsonString(generoDtoResponse))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Ação"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}