package com.exercicio5apicadastroveiculos.api.controller;


import com.exercicio5apicadastroveiculos.domain.model.Mark;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/marcas")
public class MarkController {
    @GetMapping
    public Mark[] Listar(){
        return Mark.values();
    }
}
