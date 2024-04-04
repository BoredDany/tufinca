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
        modelMapper = new ModelMapper();
    }

    @Test
    void testGetById() {
        RentRequest rentRequest = new RentRequest();
        rentRequest.setIdRentRequest(1L);
        when(repositoryRentRequest.findById(1L)).thenReturn(Optional.of(rentRequest));

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

        List<RentRequestDTO> resultList = rentRequestService.get();

        assertFalse(resultList.isEmpty());
        assertEquals(rentRequest1.getIdRentRequest(), resultList.get(0).getIdRentRequest());
    }

    @Test
    void testSave() {
        RentRequest rentRequest = new RentRequest();
        rentRequest.setIdRentRequest(1L);
        when(repositoryRentRequest.save(any(RentRequest.class))).thenReturn(rentRequest);

        RentRequestDTO rentRequestDTO = new RentRequestDTO();
        rentRequestDTO = rentRequestService.save(rentRequestDTO);

        assertNotNull(rentRequestDTO);
        assertEquals(rentRequest.getIdRentRequest(), rentRequestDTO.getIdRentRequest());
    }

    @Test
    void testUpdate() {
        RentRequest rentRequest = new RentRequest();
        rentRequest.setIdRentRequest(1L);
        when(repositoryRentRequest.findById(1L)).thenReturn(Optional.of(rentRequest));
        when(repositoryRentRequest.save(any(RentRequest.class))).thenReturn(rentRequest);

        RentRequestDTO rentRequestDTO = new RentRequestDTO();
        rentRequestDTO.setIdRentRequest(1L);
        rentRequestDTO = rentRequestService.update(rentRequestDTO);

        assertNotNull(rentRequestDTO);
        assertEquals(rentRequest.getIdRentRequest(), rentRequestDTO.getIdRentRequest());
    }

    @Test
    void testDelete() {
        doNothing().when(repositoryRentRequest).deleteById(1L);

        rentRequestService.delete(1L);

        verify(repositoryRentRequest).deleteById(1L);
    }
}