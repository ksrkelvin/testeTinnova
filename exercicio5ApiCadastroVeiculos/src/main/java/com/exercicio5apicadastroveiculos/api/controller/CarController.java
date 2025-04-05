package com.exercicio5apicadastroveiculos.api.controller;

import com.exercicio5apicadastroveiculos.api.model.CarModelApi;
import com.exercicio5apicadastroveiculos.domain.model.Car;
import com.exercicio5apicadastroveiculos.domain.model.Mark;
import com.exercicio5apicadastroveiculos.domain.repository.CarRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public ResponseEntity<CarModelApi> Listar(@RequestParam(required = false) String marca, @RequestParam(required = false) Integer ano, @RequestParam(required = false) String cor, @RequestParam(required = false) Boolean vendido) {
        if (marca != null) {
            if(!Mark.Contains(marca)){
                return ResponseEntity.badRequest().build();
            }
            marca = marca.toUpperCase();
        }
        if (ano != null) {
            cor = cor.toUpperCase();
        }
        List<Car> resultado = carRepository.FilterList(marca, ano, cor, vendido);

        CarModelApi payload = new CarModelApi(resultado);
        payload.GroupDecade();
        payload.GroupManufacturers();

        return ResponseEntity.ok(payload);
    }

    @GetMapping("/{carId}")
    public ResponseEntity<Car> Buscar(@PathVariable Long carId) {
        if (carId == null) {
            return ResponseEntity.badRequest().build();
        }
        Car car =carRepository.BuscarPorId(carId);
        if (car == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Car> Cadastrar(@RequestBody Car car) {
        if(!Mark.Contains(car.getMarca())){
            return ResponseEntity.badRequest().build();
        }
        car.setMarca(car.getMarca().toUpperCase());
        car.setCor(car.getCor().toUpperCase());
        Car savedCar = carRepository.Salvar(car);
        URI location = URI.create("/veiculos/" + savedCar.getId());
        return ResponseEntity.created(location).body(savedCar);
    }

    @PutMapping("/{carId}")
    public ResponseEntity<Car> Atualizar(@PathVariable Long carId, @RequestBody Car newCar) {
        if(!Mark.Contains(newCar.getMarca())){
            return ResponseEntity.badRequest().build();
        }
        newCar.setMarca(newCar.getMarca().toUpperCase());
        newCar.setCor(newCar.getCor().toUpperCase());
        Car currentCar = carRepository.BuscarPorId(carId);
        if (currentCar == null) {
            return ResponseEntity.notFound().build();
        }
        BeanUtils.copyProperties(newCar, currentCar, "id", "created");
        return ResponseEntity.ok(carRepository.Salvar( currentCar));
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<Car> Atualizar(@PathVariable Long carId ) {
        Car currentCar = carRepository.BuscarPorId(carId);
        if (currentCar == null) {
            return ResponseEntity.notFound().build();
        }
        carRepository.Deletar( currentCar);
        return ResponseEntity.noContent().build();
    }
}
