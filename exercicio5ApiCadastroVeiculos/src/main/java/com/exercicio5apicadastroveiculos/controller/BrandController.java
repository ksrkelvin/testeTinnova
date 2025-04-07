package com.exercicio5apicadastroveiculos.controller;


import com.exercicio5apicadastroveiculos.entity.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/marcas")
public class BrandController {
    @GetMapping
    public Brand[] list(){
        return Brand.values();
    }
}
