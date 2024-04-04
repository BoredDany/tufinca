package web.rent.tufinca.servicesTests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import web.rent.tufinca.dtos.RentDTO;
import web.rent.tufinca.entities.Rent;
import web.rent.tufinca.repositories.RepositoryRent;
import web.rent.tufinca.services.RentService;

class RentServiceTest {

    private RentService rentService;
    private RepositoryRent repositoryRent;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        repositoryRent = mock(RepositoryRent.class);
        modelMapper = mock(ModelMapper.class); // Mock el ModelMapper
        rentService = new RentService(repositoryRent, modelMapper); // Inicializa rentService
    }

    @Test
    void testGetById() {
        Rent rent = new Rent();
        rent.setIdRent(1L);
        when(repositoryRent.findById(1L)).thenReturn(Optional.of(rent));
        when(modelMapper.map(rent, RentDTO.class)).thenReturn(new RentDTO());

        RentDTO result = rentService.get(1L);

        assertNotNull(result);
        assertEquals(rent.getIdRent(), result.getIdRent());
    }

    @Test
    void testGetAll() {
        Rent rent1 = new Rent();
        rent1.setIdRent(1L);
        List<Rent> rentList = Arrays.asList(rent1);
        when(repositoryRent.findAll()).thenReturn(rentList);
        when(modelMapper.map(rent1, RentDTO.class)).thenReturn(new RentDTO());

        List<RentDTO> resultList = rentService.get();

        assertFalse(resultList.isEmpty());
        assertEquals(rent1.getIdRent(), resultList.get(0).getIdRent());
    }

    @Test
    void testSave() {
        Rent rent = new Rent();
        rent.setIdRent(1L);
        RentDTO rentDTO = new RentDTO();
        when(repositoryRent.save(any(Rent.class))).thenReturn(rent);
        when(modelMapper.map(rentDTO, Rent.class)).thenReturn(rent);
        when(modelMapper.map(rent, RentDTO.class)).thenReturn(rentDTO);

        RentDTO savedDTO = rentService.save(rentDTO);

        assertNotNull(savedDTO);
        assertEquals(rent.getIdRent(), savedDTO.getIdRent());
    }

    @Test
    void testUpdate() {
        Rent rent = new Rent();
        rent.setIdRent(1L);
        RentDTO rentDTO = new RentDTO();
        rentDTO.setIdRent(1L);

        when(repositoryRent.findById(1L)).thenReturn(Optional.of(rent));
        when(repositoryRent.save(any(Rent.class))).thenReturn(rent);
        when(modelMapper.map(rentDTO, Rent.class)).thenReturn(rent);
        when(modelMapper.map(rent, RentDTO.class)).thenReturn(rentDTO);

        RentDTO updatedDTO = rentService.update(rentDTO);

        assertNotNull(updatedDTO);
        assertEquals(rent.getIdRent(), updatedDTO.getIdRent());
    }

    @Test
    void testDelete() {
        doNothing().when(repositoryRent).deleteById(1L);

        rentService.delete(1L);

        verify(repositoryRent).deleteById(1L);
    }
}
