package com.exercicio5apicadastroveiculos.api.controller;

import com.exercicio5apicadastroveiculos.domain.model.Mark;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MarkController.class)
class MarkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listar_deveRetornarListaDeMarks() throws Exception {
        mockMvc.perform(get("/marcas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(Mark.values().length))
                .andExpect(jsonPath("$[0]").value(Mark.values()[0].name()));
    }
}
