package com.exercicio5apicadastroveiculos.api.controller;


import com.exercicio5apicadastroveiculos.controller.VehicleController;
import com.exercicio5apicadastroveiculos.dto.InfoDTO;
import com.exercicio5apicadastroveiculos.dto.VehicleDTO;
import com.exercicio5apicadastroveiculos.service.DeleteCarsService;
import com.exercicio5apicadastroveiculos.service.FindByIdCarsService;
import com.exercicio5apicadastroveiculos.service.InfoCarsService;
import com.exercicio5apicadastroveiculos.service.ListCarsService;
import com.exercicio5apicadastroveiculos.service.SaveCarsService;
import com.exercicio5apicadastroveiculos.service.UpdateItemCarsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VehicleControllerTest {

    private SaveCarsService saveCarService;
    private DeleteCarsService deleteCarService;
    private FindByIdCarsService findByIdCarService;
    private ListCarsService listCarsService;
    private InfoCarsService infoCarsService;
    private UpdateItemCarsService updateItemCarsService;
    private VehicleController controller;

    @BeforeEach
    void setUp() {
        saveCarService = mock(SaveCarsService.class);
        deleteCarService = mock(DeleteCarsService.class);
        findByIdCarService = mock(FindByIdCarsService.class);
        listCarsService = mock(ListCarsService.class);
        infoCarsService = mock(InfoCarsService.class);
        updateItemCarsService = mock(UpdateItemCarsService.class);

        controller = new VehicleController(
                saveCarService,
                deleteCarService,
                findByIdCarService,
                listCarsService,
                infoCarsService,
                updateItemCarsService
        );
    }

    @Test
    void testCreate() {
        VehicleDTO input = new VehicleDTO();
        VehicleDTO saved = new VehicleDTO();
        saved.setId(1L);

        when(saveCarService.execute(null, input)).thenReturn(saved);

        ResponseEntity<VehicleDTO> response = controller.create(input);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(saved, response.getBody());
        verify(saveCarService).execute(null, input);
    }

    @Test
    void testDelete() {
        Long id = 1L;

        ResponseEntity<Void> response = controller.delete(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(deleteCarService).execute(id);
    }

    @Test
    void testGetById() {
        Long id = 1L;
        VehicleDTO dto = new VehicleDTO();
        dto.setId(id);

        when(findByIdCarService.execute(id)).thenReturn(dto);

        ResponseEntity<VehicleDTO> response = controller.getById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
        verify(findByIdCarService).execute(id);
    }

    @Test
    void testList() {
        List<VehicleDTO> list = List.of(new VehicleDTO(), new VehicleDTO());

        when(listCarsService.execute("vermelho", 2020, "Toyota", true)).thenReturn(list);

        ResponseEntity<List<VehicleDTO>> response = controller.list("vermelho", 2020, "Toyota", true);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());
        verify(listCarsService).execute("vermelho", 2020, "Toyota", true);
    }

    @Test
    void testGetInfo() {
        InfoDTO info = new InfoDTO();
        when(infoCarsService.execute()).thenReturn(info);

        ResponseEntity<InfoDTO> response = controller.getInfo();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(info, response.getBody());
        verify(infoCarsService).execute();
    }
}
