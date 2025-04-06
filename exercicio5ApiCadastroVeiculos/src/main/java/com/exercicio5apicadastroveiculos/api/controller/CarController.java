package com.exercicio5apicadastroveiculos.api.controller;

import com.exercicio5apicadastroveiculos.api.model.CarInfoModelApi;
import com.exercicio5apicadastroveiculos.domain.model.QtyDecade;
import com.exercicio5apicadastroveiculos.domain.model.QtyManufacturers;
import com.exercicio5apicadastroveiculos.domain.model.Car;
import com.exercicio5apicadastroveiculos.domain.model.Mark;
import com.exercicio5apicadastroveiculos.domain.repository.CarRepository;
import com.exercicio5apicadastroveiculos.infrastructure.exception.BadRequestException;
import com.exercicio5apicadastroveiculos.infrastructure.exception.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.ReflectionUtils;


import java.lang.reflect.Field;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/veiculos")
public class CarController {

    @Autowired
    private CarRepository carRepository;


    @GetMapping
    public ResponseEntity<List<Car>> listar(@RequestParam(required = false) String marca, @RequestParam(required = false) Integer ano, @RequestParam(required = false) String cor, @RequestParam(required = false) Boolean vendido) {
        if (marca != null) {
            if (!Mark.contains(marca)) {
                throw new BadRequestException("Marca '" + marca +"' não é válida.");
            }
            marca = marca.toUpperCase();
        }

        if (cor != null) {
            cor = cor.toUpperCase();
        }
        List<Car> payload = carRepository.filterList(marca, ano, cor, vendido);

        return ResponseEntity.ok(payload);
    }

    @GetMapping("/{carId}")
    public ResponseEntity<Car> buscar(@PathVariable Long carId) {
        if (carId == null) {
            throw new BadRequestException("Veículo ID não é válida.");
        }
        Car car = carRepository.buscarPorId(carId);
        if (car == null) {
            throw new NotFoundException("Veículo '"+ carId +"' não encontrado");
        }
        return ResponseEntity.ok(car);
    }

    @GetMapping("/info")
    public ResponseEntity<CarInfoModelApi> listarInfo() {
        long vendidos = carRepository.countVendidos();
        List<Car> ultimosCadastrados = carRepository.listarUltimosCadastrados();
        List<QtyDecade> qtyDecade= carRepository.groupByAno();
        List<QtyManufacturers> qtyManufacturers = carRepository.groupByMarca();

        CarInfoModelApi payload = new CarInfoModelApi(ultimosCadastrados, vendidos, qtyDecade,qtyManufacturers);
        return ResponseEntity.ok(payload);

    }

    @PostMapping
    public ResponseEntity<Car> cadastrar(@RequestBody Car car) {
        if (car.getMarca() == null || car.getMarca().isEmpty()) {
            throw new BadRequestException("O campo marca não pode ser vazio.");
        }
        if (!Mark.contains(car.getMarca())) {
            throw new BadRequestException("Marca '" + car.getMarca() +"' não é válida.");

        }
        car.setMarca(car.getMarca().toUpperCase());
        car.setCor(car.getCor().toUpperCase());

        if (car.getAno() == null || car.getAno() == 0) {
            throw new BadRequestException("O campo ano não pode ser vazio.");
        }

        Car savedCar = carRepository.salvar(car);
        URI location = URI.create("/veiculos/" + savedCar.getId());
        return ResponseEntity.created(location).body(savedCar);
    }

    @PutMapping("/{carId}")
    public ResponseEntity<Car> atualizarTudo(@PathVariable Long carId, @RequestBody Car car) {
        Car currentCar = carRepository.buscarPorId(carId);
        if (currentCar == null) {
            throw new NotFoundException("Veículo '"+ carId +"' não encontrado");

        }
        if (car.getMarca() == null || car.getMarca().isEmpty()) {
            throw new BadRequestException("O campo marca não pode ser vazio.");
        }
        if (!Mark.contains(car.getMarca())) {
            throw new BadRequestException("Marca '" + car.getMarca() +"' não é válida.");
        }
        car.setMarca(car.getMarca().toUpperCase());
        if (car.getCor() != null) {
            car.setCor(car.getCor().toUpperCase());
        }
        if (car.getAno() == null || car.getAno() == 0) {
            throw new BadRequestException("O campo ano não pode ser vazio.");
        }

        BeanUtils.copyProperties(car, currentCar, "id", "created");
        return ResponseEntity.ok(carRepository.salvar(currentCar));
    }

    @PatchMapping("/{carId}")
    public ResponseEntity<Car> atualizarParcial(@PathVariable Long carId, @RequestBody Map<String, Object> payload) {
        Car currentCar = carRepository.buscarPorId(carId);
        if (currentCar == null) {
            throw new NotFoundException("Veículo '"+ carId +"' não encontrado");

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
        if (currentCar.getMarca() == null || currentCar.getMarca().isEmpty()) {
            throw new BadRequestException("O campo marca não pode ser vazio.");
        }
        if (!Mark.contains(currentCar.getMarca())) {
            throw new BadRequestException("Marca '" + currentCar.getMarca() +"' não é válida.");
        }
        if (currentCar.getAno() == null || currentCar.getAno() == 0) {
            throw new BadRequestException("O campo ano não pode ser vazio.");
        }

        Car atualizado = carRepository.salvar(currentCar);
        return ResponseEntity.ok(atualizado);
    }


    @DeleteMapping("/{carId}")
    public ResponseEntity<Car> deletar(@PathVariable Long carId) {
        Car currentCar = carRepository.buscarPorId(carId);
        if (currentCar == null) {
            throw new NotFoundException("Veículo '"+ carId +"' não encontrado");
        }
        carRepository.deletar(currentCar);
        return ResponseEntity.noContent().build();
    }

}
