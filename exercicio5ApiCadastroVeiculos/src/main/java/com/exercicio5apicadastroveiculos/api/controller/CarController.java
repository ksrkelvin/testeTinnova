package com.exercicio5apicadastroveiculos.api.controller;

import com.exercicio5apicadastroveiculos.api.model.CarInfoModelApi;
import com.exercicio5apicadastroveiculos.domain.model.QtyDecade;
import com.exercicio5apicadastroveiculos.domain.model.QtyManufacturers;
import com.exercicio5apicadastroveiculos.domain.model.Car;
import com.exercicio5apicadastroveiculos.domain.model.Mark;
import com.exercicio5apicadastroveiculos.domain.repository.CarRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.ReflectionUtils;


import java.lang.reflect.Field;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/veiculos")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public ResponseEntity<List<Car>> Listar(@RequestParam(required = false) String marca, @RequestParam(required = false) Integer ano, @RequestParam(required = false) String cor, @RequestParam(required = false) Boolean vendido) {
        if (marca != null) {
            if (!Mark.Contains(marca)) {
                return ResponseEntity.badRequest().build();
            }
            marca = marca.toUpperCase();
        }

        if (cor != null) {
            cor = cor.toUpperCase();
        }
        List<Car> payload = carRepository.FilterList(marca, ano, cor, vendido);

        return ResponseEntity.ok(payload);
    }

    @GetMapping("/{carId}")
    public ResponseEntity<Car> Buscar(@PathVariable Long carId) {
        if (carId == null) {
            return ResponseEntity.badRequest().build();
        }
        Car car = carRepository.BuscarPorId(carId);
        if (car == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @GetMapping("/info")
    public ResponseEntity<CarInfoModelApi> ListarInfo() {
        long vendidos = carRepository.CountVendidos();
        List<Car> ultimosCadastrados = carRepository.ListarUltimosCadastrados();
        List<QtyDecade> qtyDecade= carRepository.GroupByAno();
        List<QtyManufacturers> qtyManufacturers = carRepository.GroupByMarca();

        CarInfoModelApi payload = new CarInfoModelApi(ultimosCadastrados, vendidos, qtyDecade,qtyManufacturers);
        return ResponseEntity.ok(payload);

    }

    @PostMapping
    public ResponseEntity<Car> Cadastrar(@RequestBody Car car) {
        if (car.getMarca() == null || car.getMarca().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (!Mark.Contains(car.getMarca())) {
            return ResponseEntity.badRequest().build();
        }
        car.setMarca(car.getMarca().toUpperCase());
        car.setCor(car.getCor().toUpperCase());

        if (car.getAno() == null || car.getAno() == 0) {
            return ResponseEntity.badRequest().build();
        }


        Car savedCar = carRepository.Salvar(car);
        URI location = URI.create("/veiculos/" + savedCar.getId());
        return ResponseEntity.created(location).body(savedCar);
    }

    @PutMapping("/{carId}")
    public ResponseEntity<Car> AtualizarTudo(@PathVariable Long carId, @RequestBody Car car) {
        if (car.getMarca() == null || car.getMarca().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (!Mark.Contains(car.getMarca())) {
            return ResponseEntity.badRequest().build();
        }
        car.setMarca(car.getMarca().toUpperCase());
        if (car.getCor() != null) {
            car.setCor(car.getCor().toUpperCase());
        }
        if (car.getAno() == null || car.getAno() == 0) {
            return ResponseEntity.badRequest().build();
        }

        Car currentCar = carRepository.BuscarPorId(carId);
        if (currentCar == null) {
            return ResponseEntity.notFound().build();
        }
        BeanUtils.copyProperties(car, currentCar, "id", "created");
        return ResponseEntity.ok(carRepository.Salvar(currentCar));
    }

    @PatchMapping("/{carId}")
    public ResponseEntity<Car> AtualizarParcial(@PathVariable Long carId, @RequestBody Map<String, Object> payload) {
        Car currentCar = carRepository.BuscarPorId(carId);
        if (currentCar == null) {
            return ResponseEntity.notFound().build();
        }

        payload.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Car.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, currentCar, value);
            }
        });

        if (currentCar.getMarca() != null) {
            currentCar.setMarca(currentCar.getMarca().toUpperCase());
        }
        if (currentCar.getCor() != null) {
            currentCar.setCor(currentCar.getCor().toUpperCase());
        }
        if (currentCar.getMarca() != null && !Mark.Contains(currentCar.getMarca())) {
            return ResponseEntity.badRequest().build();
        }
        if (currentCar.getAno() == null || currentCar.getAno() == 0) {
            return ResponseEntity.badRequest().build();
        }

        Car atualizado = carRepository.Salvar(currentCar);
        return ResponseEntity.ok(atualizado);
    }


    @DeleteMapping("/{carId}")
    public ResponseEntity<Car> Deletar(@PathVariable Long carId) {
        Car currentCar = carRepository.BuscarPorId(carId);
        if (currentCar == null) {
            return ResponseEntity.notFound().build();
        }
        carRepository.Deletar(currentCar);
        return ResponseEntity.noContent().build();
    }

}
