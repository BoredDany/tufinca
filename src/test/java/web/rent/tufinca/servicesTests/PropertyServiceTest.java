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

import web.rent.tufinca.dtos.PropertyDTO;
import web.rent.tufinca.entities.Property;
import web.rent.tufinca.repositories.RepositoryProperty;
import web.rent.tufinca.services.PropertyService;

class PropertyServiceTest {

    @InjectMocks
    private PropertyService propertyService;

    @Mock
    private RepositoryProperty repositoryProperty;

    @Mock
    private ModelMapper modelMapper;

    private PropertyDTO propertyDTO;
    private Property property;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        propertyDTO = new PropertyDTO();
        propertyDTO.setIdProperty(1L);

        property = new Property();
        property.setIdProperty(1L);
    }

    //Prueba 41: Verificar que se obtiene una propiedad por su id, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, ModelMapper, PropertyService, PropertyDTO, Property, RepositoryProperty
    @Test
    void testGetById() {
        when(repositoryProperty.findById(1L)).thenReturn(Optional.of(property));
        when(modelMapper.map(property, PropertyDTO.class)).thenReturn(propertyDTO);

        PropertyDTO result = propertyService.get(1L);

        assertEquals(propertyDTO, result);
    }

    //Prueba 42: Verificar que se obtienen todas las propiedades, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, ModelMapper, PropertyService, PropertyDTO, Property, RepositoryProperty
    @Test
    void testGetAll() {
        when(repositoryProperty.findAll()).thenReturn(Arrays.asList(property));
        when(modelMapper.map(property, PropertyDTO.class)).thenReturn(propertyDTO);

        assertEquals(Arrays.asList(propertyDTO), propertyService.get());
    }

    //Prueba 43: Verificar que se guarda una propiedad, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, ModelMapper, PropertyService, PropertyDTO, Property, RepositoryProperty
    @Test
    void testSave() {
        when(modelMapper.map(propertyDTO, Property.class)).thenReturn(property);
        when(repositoryProperty.save(property)).thenReturn(property);

        PropertyDTO result = propertyService.save(propertyDTO);

        assertEquals(propertyDTO, result);
    }

    //Prueba 44: Verificar que se actualiza una propiedad, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, ModelMapper, PropertyService, PropertyDTO, Property, RepositoryProperty
    /*@Test
    void testUpdate() {
        when(repositoryProperty.findById(1L)).thenReturn(Optional.of(property));
        when(modelMapper.map(property, PropertyDTO.class)).thenReturn(propertyDTO);
        when(modelMapper.map(propertyDTO, Property.class)).thenReturn(property);
        when(repositoryProperty.save(property)).thenReturn(property);

        PropertyDTO result = propertyService.update(propertyDTO);

        assertEquals(propertyDTO, result);
    }*/

    //Prueba 45: Verificar que se elimina una propiedad, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, PropertyService, RepositoryProperty
    @Test
    void testDelete() {
        doNothing().when(repositoryProperty).deleteById(1L);

        propertyService.delete(1L);

        verify(repositoryProperty, times(1)).deleteById(1L);
    }
}