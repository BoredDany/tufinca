package web.rent.tufinca.servicesTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import web.rent.tufinca.dtos.RentDTO;
import web.rent.tufinca.entities.Rent;
import web.rent.tufinca.repositories.RepositoryRent;
import web.rent.tufinca.services.RentService;

class RentServiceTest {

    @InjectMocks
    private RentService rentService;

    @Mock
    private RepositoryRent repositoryRent;

    @Mock
    private ModelMapper modelMapper;

    private RentDTO rentDTO;
    private Rent rent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        rentDTO = new RentDTO();
        rentDTO.setIdRent(1L);

        rent = new Rent();
        rent.setIdRent(1L);
    }

    //Prueba 51: Verificar que se obtiene un alquiler por su id, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, ModelMapper, RentService, RentDTO, Rent, RepositoryRent
    @Test
    void testGetById() {
        when(repositoryRent.findById(1L)).thenReturn(Optional.of(rent));
        when(modelMapper.map(rent, RentDTO.class)).thenReturn(rentDTO);

        RentDTO result = rentService.get(1L);

        assertEquals(rentDTO, result);
    }

    //Prueba 52: Verificar que se obtienen todos los alquileres, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, ModelMapper, RentService, RentDTO, Rent, RepositoryRent
    @Test
    void testGetAll() {
        when(repositoryRent.findAll()).thenReturn(Arrays.asList(rent));
        when(modelMapper.map(rent, RentDTO.class)).thenReturn(rentDTO);

        assertEquals(Arrays.asList(rentDTO), rentService.get());
    }

    //Prueba 53: Verificar que se guarda un alquiler, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, ModelMapper, RentService, RentDTO, Rent, RepositoryRent
    @Test
    void testSave() {
        when(modelMapper.map(rentDTO, Rent.class)).thenReturn(rent);
        when(repositoryRent.save(rent)).thenReturn(rent);

        RentDTO result = rentService.save(rentDTO);

        assertEquals(rentDTO, result);
    }

    //Prueba 54: Verificar que se actualiza un alquiler, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, ModelMapper, RentService, RentDTO, Rent, RepositoryRent
    @Test
    void testUpdate() {
        when(repositoryRent.findById(1L)).thenReturn(Optional.of(rent));
        when(modelMapper.map(rent, RentDTO.class)).thenReturn(rentDTO);
        when(modelMapper.map(rentDTO, Rent.class)).thenReturn(rent);
        when(repositoryRent.save(rent)).thenReturn(rent);

        RentDTO result = rentService.update(rentDTO);

        assertEquals(rentDTO, result);
    }

    //Prueba 55: Verificar que se elimina un alquiler, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, RentService, RepositoryRent
    @Test
    void testDelete() {
        doNothing().when(repositoryRent).deleteById(1L);

        rentService.delete(1L);

        verify(repositoryRent, times(1)).deleteById(1L);
    }
}