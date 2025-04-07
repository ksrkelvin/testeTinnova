package com.exercicio5apicadastroveiculos.api.controller;

import com.exercicio5apicadastroveiculos.controller.BrandController;
import com.exercicio5apicadastroveiculos.entity.Brand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BrandController.class)
class BrandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listar_deveRetornarListaDeMarks() throws Exception {
        mockMvc.perform(get("/marcas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(Brand.values().length))
                .andExpect(jsonPath("$[0]").value(Brand.values()[0].name()));
    }
}
