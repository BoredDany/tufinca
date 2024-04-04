package web.rent.tufinca.servicesTests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import web.rent.tufinca.dtos.RentRequestDTO;
import web.rent.tufinca.entities.RentRequest;
import web.rent.tufinca.repositories.RepositoryRentRequest;
import web.rent.tufinca.services.RentRequestService;

class RentRequestServiceTest {

    private RentRequestService rentRequestService;
    private RepositoryRentRequest repositoryRentRequest;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        repositoryRentRequest = mock(RepositoryRentRequest.class);
        modelMapper = mock(ModelMapper.class); // Usar mock para ModelMapper
        rentRequestService = new RentRequestService(repositoryRentRequest, modelMapper); // Inicialización correcta de rentRequestService
    }

    @Test
    void testGetById() {
        RentRequest rentRequest = new RentRequest();
        rentRequest.setIdRentRequest(1L);
        when(repositoryRentRequest.findById(1L)).thenReturn(Optional.of(rentRequest));
        when(modelMapper.map(rentRequest, RentRequestDTO.class)).thenReturn(new RentRequestDTO());

        RentRequestDTO result = rentRequestService.get(1L);

        assertNotNull(result);
        assertEquals(rentRequest.getIdRentRequest(), result.getIdRentRequest());
    }

    @Test
    void testGetAll() {
        RentRequest rentRequest1 = new RentRequest();
        rentRequest1.setIdRentRequest(1L);
        List<RentRequest> rentRequestList = Arrays.asList(rentRequest1);
        when(repositoryRentRequest.findAll()).thenReturn(rentRequestList);
        when(modelMapper.map(rentRequest1, RentRequestDTO.class)).thenReturn(new RentRequestDTO());

        List<RentRequestDTO> resultList = rentRequestService.get();

        assertFalse(resultList.isEmpty());
        assertEquals(rentRequest1.getIdRentRequest(), resultList.get(0).getIdRentRequest());
    }

    @Test
    void testSave() {
        RentRequest rentRequest = new RentRequest();
        rentRequest.setIdRentRequest(1L);
        when(repositoryRentRequest.save(any(RentRequest.class))).thenReturn(rentRequest);
        when(modelMapper.map(any(RentRequestDTO.class), eq(RentRequest.class))).thenReturn(rentRequest);
        when(modelMapper.map(rentRequest, RentRequestDTO.class)).thenReturn(new RentRequestDTO());

        RentRequestDTO rentRequestDTO = new RentRequestDTO();
        RentRequestDTO result = rentRequestService.save(rentRequestDTO);

        assertNotNull(result);
        assertEquals(rentRequest.getIdRentRequest(), result.getIdRentRequest());
    }

    @Test
    void testUpdate() {
        RentRequest rentRequest = new RentRequest();
        rentRequest.setIdRentRequest(1L);
        when(repositoryRentRequest.findById(1L)).thenReturn(Optional.of(rentRequest));
        when(repositoryRentRequest.save(any(RentRequest.class))).thenReturn(rentRequest);
        when(modelMapper.map(any(RentRequestDTO.class), eq(RentRequest.class))).thenReturn(rentRequest);
        when(modelMapper.map(rentRequest, RentRequestDTO.class)).thenReturn(new RentRequestDTO());

        RentRequestDTO rentRequestDTO = new RentRequestDTO();
        rentRequestDTO.setIdRentRequest(1L);
        RentRequestDTO updatedResult = rentRequestService.update(rentRequestDTO);

        assertNotNull(updatedResult);
        assertEquals(rentRequest.getIdRentRequest(), updatedResult.getIdRentRequest());
    }

    @Test
    void testDelete() {
        doNothing().when(repositoryRentRequest).deleteById(1L);

        rentRequestService.delete(1L);

        verify(repositoryRentRequest).deleteById(1L);
    }
}
