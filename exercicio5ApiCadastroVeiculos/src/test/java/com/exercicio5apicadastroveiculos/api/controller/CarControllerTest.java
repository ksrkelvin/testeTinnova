package com.exercicio5apicadastroveiculos.api.controller;

import com.exercicio5apicadastroveiculos.domain.model.Car;
import com.exercicio5apicadastroveiculos.domain.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CarRepository carRepository;

    @BeforeEach
    void setup() {
        Mockito.reset(carRepository);
    }

    @Test
    void listar() throws Exception {
        Car car = new Car("Civic", "Honda", 1990, "branco", false);
        List<Car> cars = new ArrayList<>();
        cars.add(car);
        Mockito.when(carRepository.filterList(null, null, null, null)).thenReturn(cars);

        mockMvc.perform(get("/veiculos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].veiculo").value("Civic"))
                .andExpect(jsonPath("$[0].marca").value("Honda"));
    }

    @Test
    void buscar() throws Exception {
        Car car = new Car("Civic", "Honda", 1990, "branco", false);
        Mockito.when(carRepository.buscarPorId(1L)).thenReturn(car);

        mockMvc.perform(get("/veiculos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.veiculo").value("Civic"))
                .andExpect(jsonPath("$.marca").value("Honda"));
    }

    @Test
    void cadastrar() throws Exception {
        Car carSalvo = new Car("Civic", "Honda", 1990, "branco", false);
        carSalvo.setId(1L); // <-- Simulando ID retornado ao salvar

        Mockito.when(carRepository.salvar(any())).thenReturn(carSalvo);

        mockMvc.perform(post("/veiculos")
                        .contentType("application/json")
                        .content("""
                                {
                                    "veiculo": "Civic",
                                    "marca": "Honda",
                                    "ano": 1990,
                                    "cor": "branco",
                                    "vendido": false
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void deletar() throws Exception {
        Car currentCar = new Car("Civic", "Honda", 1990, "branco", false);
        Mockito.when(carRepository.buscarPorId(1L)).thenReturn(currentCar);

        mockMvc.perform(delete("/veiculos/1"))
                .andExpect(status().isNoContent());

        verify(carRepository).deletar(currentCar);
    }
}
