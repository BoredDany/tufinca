package web.rent.tufinca.servicesTests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import web.rent.tufinca.dtos.PropertyDetailDTO;
import web.rent.tufinca.entities.PropertyDetail;
import web.rent.tufinca.repositories.RepositoryPropertyDetail;
import web.rent.tufinca.services.PropertyDetailService;

class PropertyDetailsServiceTest {

    private PropertyDetailService propertyDetailService;
    private RepositoryPropertyDetail repositoryPropertyDetail;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        repositoryPropertyDetail = mock(RepositoryPropertyDetail.class);
        modelMapper = mock(ModelMapper.class);  // Usar mock para ModelMapper
        propertyDetailService = new PropertyDetailService(repositoryPropertyDetail, modelMapper); // Instanciar el servicio con los mocks
    }

    @Test
    void testGetById() {
        PropertyDetail propertyDetail = new PropertyDetail();
        propertyDetail.setIdPropertyDetail(1L);
        when(repositoryPropertyDetail.findById(1L)).thenReturn(Optional.of(propertyDetail));
        when(modelMapper.map(any(), eq(PropertyDetailDTO.class))).thenReturn(new PropertyDetailDTO()); // Mockear la conversión

        PropertyDetailDTO result = propertyDetailService.get(1L);

        assertNotNull(result);
        assertEquals(propertyDetail.getIdPropertyDetail(), result.getIdPropertyDetail());
    }

    @Test
    void testGetAll() {
        PropertyDetail propertyDetail = new PropertyDetail();
        propertyDetail.setIdPropertyDetail(1L);
        when(repositoryPropertyDetail.findAll()).thenReturn(Arrays.asList(propertyDetail));
        when(modelMapper.map(any(), eq(PropertyDetailDTO.class))).thenReturn(new PropertyDetailDTO()); // Mockear la conversión

        List<PropertyDetailDTO> result = propertyDetailService.get();

        assertFalse(result.isEmpty());
        assertEquals(propertyDetail.getIdPropertyDetail(), result.get(0).getIdPropertyDetail());
    }

    @Test
    void testSave() {
        PropertyDetail propertyDetail = new PropertyDetail();
        propertyDetail.setIdPropertyDetail(1L);
        when(repositoryPropertyDetail.save(any(PropertyDetail.class))).thenReturn(propertyDetail);
        when(modelMapper.map(any(), eq(PropertyDetail.class))).thenReturn(propertyDetail); // Mockear la conversión a PropertyDetail
        when(modelMapper.map(any(), eq(PropertyDetailDTO.class))).thenReturn(new PropertyDetailDTO()); // Mockear la conversión a PropertyDetailDTO

        PropertyDetailDTO propertyDetailDTO = new PropertyDetailDTO();
        propertyDetailDTO = propertyDetailService.save(propertyDetailDTO);

        assertNotNull(propertyDetailDTO);
        assertEquals(propertyDetail.getIdPropertyDetail(), propertyDetailDTO.getIdPropertyDetail());
    }

    @Test
    void testUpdate() {
        PropertyDetail propertyDetail = new PropertyDetail();
        propertyDetail.setIdPropertyDetail(1L);
        when(repositoryPropertyDetail.findById(1L)).thenReturn(Optional.of(propertyDetail));
        when(repositoryPropertyDetail.save(any(PropertyDetail.class))).thenReturn(propertyDetail);
        when(modelMapper.map(any(), eq(PropertyDetail.class))).thenReturn(propertyDetail); // Mockear la conversión a PropertyDetail
        when(modelMapper.map(any(), eq(PropertyDetailDTO.class))).thenReturn(new PropertyDetailDTO()); // Mockear la conversión a PropertyDetailDTO

        PropertyDetailDTO propertyDetailDTO = new PropertyDetailDTO();
        propertyDetailDTO.setIdPropertyDetail(1L);
        propertyDetailDTO = propertyDetailService.update(propertyDetailDTO);

        assertNotNull(propertyDetailDTO);
        assertEquals(propertyDetail.getIdPropertyDetail(), propertyDetailDTO.getIdPropertyDetail());
    }

    @Test
    void testDelete() {
        doNothing().when(repositoryPropertyDetail).deleteById(1L);

        propertyDetailService.delete(1L);

        verify(repositoryPropertyDetail).deleteById(1L);
    }
}
