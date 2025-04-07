package com.exercicio5apicadastroveiculos;

import com.exercicio5apicadastroveiculos.repository.VehicleCriteriaRepository;
import com.exercicio5apicadastroveiculos.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@ActiveProfiles("test")
class Exercicio5ApiCadastroVeiculosApplicationTests {

    @MockitoBean
    private VehicleRepository veichleRepository;
    @MockitoBean
    private VehicleCriteriaRepository vehicleCriteriaRepository;

    @Test
    void contextLoads() {
        System.out.println("Application started with mocks.");
    }
}
