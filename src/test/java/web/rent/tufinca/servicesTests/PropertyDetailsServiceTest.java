package web.rent.tufinca.servicesTests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Optional;

import web.rent.tufinca.dtos.PropertyDetailDTO;
import web.rent.tufinca.entities.PropertyDetail;
import web.rent.tufinca.repositories.RepositoryPropertyDetail;
import web.rent.tufinca.services.PropertyDetailService;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


class PropertyDetailsServiceTest {

    @InjectMocks
    private PropertyDetailService propertyDetailService;

    @Mock
    private RepositoryPropertyDetail repositoryPropertyDetail;

    @Mock
    private ModelMapper modelMapper;

    private PropertyDetailDTO propertyDetailDTO;
    private PropertyDetail propertyDetail;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        propertyDetailDTO = new PropertyDetailDTO();
        propertyDetailDTO.setIdPropertyDetail(1L);

        propertyDetail = new PropertyDetail();
        propertyDetail.setIdPropertyDetail(1L);
    }

    //Prueba 36: Verificar que se obtiene un detalle de propiedad por su id, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, ModelMapper, PropertyDetailService, PropertyDetailDTO, PropertyDetail, RepositoryPropertyDetail
    @Test
    void testGetById() {
        when(repositoryPropertyDetail.findById(1L)).thenReturn(Optional.of(propertyDetail));
        when(modelMapper.map(propertyDetail, PropertyDetailDTO.class)).thenReturn(propertyDetailDTO);

        PropertyDetailDTO result = propertyDetailService.get(1L);

        assertEquals(propertyDetailDTO, result);
    }

    //Prueba 37: Verificar que se obtienen todos los detalles de propiedad, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, ModelMapper, PropertyDetailService, PropertyDetailDTO, PropertyDetail, RepositoryPropertyDetail
    @Test
    void testGetAll() {
        when(repositoryPropertyDetail.findAll()).thenReturn(Arrays.asList(propertyDetail));
        when(modelMapper.map(propertyDetail, PropertyDetailDTO.class)).thenReturn(propertyDetailDTO);

        assertEquals(Arrays.asList(propertyDetailDTO), propertyDetailService.get());
    }

    //Prueba 38: Verificar que se guarda un detalle de propiedad, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, ModelMapper, PropertyDetailService, PropertyDetailDTO, PropertyDetail, RepositoryPropertyDetail
    @Test
    void testSave() {
        when(modelMapper.map(propertyDetailDTO, PropertyDetail.class)).thenReturn(propertyDetail);
        when(repositoryPropertyDetail.save(propertyDetail)).thenReturn(propertyDetail);

        PropertyDetailDTO result = propertyDetailService.save(propertyDetailDTO);

        assertEquals(propertyDetailDTO, result);
    }

    //Prueba 39: Verificar que se actualiza un detalle de propiedad, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, ModelMapper, PropertyDetailService, PropertyDetailDTO, PropertyDetail, RepositoryPropertyDetail
    @Test
    void testUpdate() {
        when(repositoryPropertyDetail.findById(1L)).thenReturn(Optional.of(propertyDetail));
        when(modelMapper.map(propertyDetail, PropertyDetailDTO.class)).thenReturn(propertyDetailDTO);
        when(modelMapper.map(propertyDetailDTO, PropertyDetail.class)).thenReturn(propertyDetail);
        when(repositoryPropertyDetail.save(propertyDetail)).thenReturn(propertyDetail);

        PropertyDetailDTO result = propertyDetailService.update(propertyDetailDTO);

        assertEquals(propertyDetailDTO, result);
    }

    //Prueba 40: Verificar que se elimina un detalle de propiedad, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, ModelMapper, PropertyDetailService, PropertyDetailDTO, PropertyDetail, RepositoryPropertyDetail
    @Test
    void testDelete() {
        doNothing().when(repositoryPropertyDetail).deleteById(1L);

        propertyDetailService.delete(1L);

        verify(repositoryPropertyDetail, times(1)).deleteById(1L);
    }
}
