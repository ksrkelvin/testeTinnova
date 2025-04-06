package com.exercicio5apicadastroveiculos;

import com.exercicio5apicadastroveiculos.domain.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@ActiveProfiles("test")
class Exercicio5ApiCadastroVeiculosApplicationTests {

    @MockitoBean
    private CarRepository carRepository;

    @Test
    void contextLoads() {
        System.out.println("Application started with mocks.");
    }
}
