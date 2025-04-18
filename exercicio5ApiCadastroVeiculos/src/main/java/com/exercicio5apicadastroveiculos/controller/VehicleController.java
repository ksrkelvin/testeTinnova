package com.exercicio5apicadastroveiculos.controller;

import com.exercicio5apicadastroveiculos.dto.InfoDTO;
import com.exercicio5apicadastroveiculos.dto.VehicleDTO;
import com.exercicio5apicadastroveiculos.dto.VehiclePatchDTO;
import com.exercicio5apicadastroveiculos.service.DeleteCarsService;
import com.exercicio5apicadastroveiculos.service.FindByIdCarsService;
import com.exercicio5apicadastroveiculos.service.InfoCarsService;
import com.exercicio5apicadastroveiculos.service.ListCarsService;
import com.exercicio5apicadastroveiculos.service.SaveCarsService;
import com.exercicio5apicadastroveiculos.service.PatchCarsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Validated
@RestController
@RequestMapping("/veiculos")
public class VehicleController {

    private final SaveCarsService saveCarService;
    private final DeleteCarsService deleteCarService;
    private final FindByIdCarsService findByIdCarService;
    private final ListCarsService listCarsService;
    private final InfoCarsService infoCarsService;
    private final PatchCarsService patchCarsService;


    public VehicleController(SaveCarsService saveCarService,
                             DeleteCarsService deleteCarService,
                             FindByIdCarsService findByIdCarService,
                             ListCarsService listCarsService,
                             InfoCarsService infoCarsService,
                             PatchCarsService patchCarsService) {
        this.saveCarService = saveCarService;
        this.deleteCarService = deleteCarService;
        this.findByIdCarService = findByIdCarService;
        this.listCarsService = listCarsService;
        this.infoCarsService = infoCarsService;
        this.patchCarsService = patchCarsService;
    }

    @PostMapping
    public ResponseEntity<VehicleDTO> create(@RequestBody @Valid VehicleDTO dto) {
        VehicleDTO result = saveCarService.execute(null, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDTO> update(@PathVariable Long id, @RequestBody @Valid VehicleDTO dto) {
        VehicleDTO result = saveCarService.execute(id, dto);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VehicleDTO> updateItem(@PathVariable Long id, @RequestBody @Valid VehiclePatchDTO dto) {
        VehicleDTO result = patchCarsService.execute(id, dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteCarService.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(findByIdCarService.execute(id));
    }

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> list(
            @RequestParam(required = false) String cor,
            @RequestParam(required = false) Integer ano,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) Boolean vendido) {
        return ResponseEntity.ok(listCarsService.execute(cor, ano, marca, vendido));
    }

    @GetMapping("/info")
    public ResponseEntity<InfoDTO> getInfo(){
        InfoDTO info = infoCarsService.execute();
        return ResponseEntity.ok(info);
    }

}
