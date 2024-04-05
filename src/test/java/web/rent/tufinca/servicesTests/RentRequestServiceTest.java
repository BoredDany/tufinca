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

import web.rent.tufinca.dtos.RentRequestDTO;
import web.rent.tufinca.entities.RentRequest;
import web.rent.tufinca.repositories.RepositoryRentRequest;
import web.rent.tufinca.services.RentRequestService;

class RentRequestServiceTest {

    @InjectMocks
    private RentRequestService rentRequestService;

    @Mock
    private RepositoryRentRequest repositoryRentRequest;

    @Mock
    private ModelMapper modelMapper;

    private RentRequestDTO rentRequestDTO;
    private RentRequest rentRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        rentRequestDTO = new RentRequestDTO();
        rentRequestDTO.setIdRentRequest(1L);

        rentRequest = new RentRequest();
        rentRequest.setIdRentRequest(1L);
    }

    //Prueba 46: Verificar que se obtiene una solicitud de alquiler por su id, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, ModelMapper, RentRequestService, RentRequestDTO, RentRequest, RepositoryRentRequest
    @Test
    void testGetById() {
        when(repositoryRentRequest.findById(1L)).thenReturn(Optional.of(rentRequest));
        when(modelMapper.map(rentRequest, RentRequestDTO.class)).thenReturn(rentRequestDTO);

        RentRequestDTO result = rentRequestService.get(1L);

        assertEquals(rentRequestDTO, result);
    }

    //Prueba 47: Verificar que se obtienen todas las solicitudes de alquiler, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, ModelMapper, RentRequestService, RentRequestDTO, RentRequest, RepositoryRentRequest
    @Test
    void testGetAll() {
        when(repositoryRentRequest.findAll()).thenReturn(Arrays.asList(rentRequest));
        when(modelMapper.map(rentRequest, RentRequestDTO.class)).thenReturn(rentRequestDTO);

        assertEquals(Arrays.asList(rentRequestDTO), rentRequestService.get());
    }

    //Prueba 48: Verificar que se guarda una solicitud de alquiler, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, ModelMapper, RentRequestService, RentRequestDTO, RentRequest, RepositoryRentRequest
    @Test
    void testSave() {
        when(modelMapper.map(rentRequestDTO, RentRequest.class)).thenReturn(rentRequest);
        when(repositoryRentRequest.save(rentRequest)).thenReturn(rentRequest);

        RentRequestDTO result = rentRequestService.save(rentRequestDTO);

        assertEquals(rentRequestDTO, result);
    }

    //Prueba 49: Verificar que se actualiza una solicitud de alquiler, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, ModelMapper, RentRequestService, RentRequestDTO, RentRequest, RepositoryRentRequest
    /*@Test
    void testUpdate() {
        when(repositoryRentRequest.findById(1L)).thenReturn(Optional.of(rentRequest));
        when(modelMapper.map(rentRequest, RentRequestDTO.class)).thenReturn(rentRequestDTO);
        when(modelMapper.map(rentRequestDTO, RentRequest.class)).thenReturn(rentRequest);
        when(repositoryRentRequest.save(rentRequest)).thenReturn(rentRequest);

        RentRequestDTO result = rentRequestService.update(rentRequestDTO);

        assertEquals(rentRequestDTO, result);
    }*/

    //Prueba 50: Verificar que se elimina una solicitud de alquiler, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, RentRequestService, RepositoryRentRequest
    @Test
    void testDelete() {
        doNothing().when(repositoryRentRequest).deleteById(1L);

        rentRequestService.delete(1L);

        verify(repositoryRentRequest, times(1)).deleteById(1L);
    }
}