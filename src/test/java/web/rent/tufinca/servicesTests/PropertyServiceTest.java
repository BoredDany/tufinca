package web.rent.tufinca.servicesTests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import web.rent.tufinca.dtos.PropertyDTO;
import web.rent.tufinca.entities.Property;
import web.rent.tufinca.repositories.RepositoryProperty;
import web.rent.tufinca.services.PropertyService;

class PropertyServiceTest {

    private PropertyService propertyService;
    private RepositoryProperty repositoryProperty;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        repositoryProperty = mock(RepositoryProperty.class);
        modelMapper = new ModelMapper();
    }

    @Test
    void testGetById() {
        Property property = new Property();
        property.setIdProperty(1L);
        when(repositoryProperty.findById(1L)).thenReturn(Optional.of(property));

        PropertyDTO result = propertyService.get(1L);

        assertNotNull(result);
        assertEquals(property.getIdProperty(), result.getIdProperty());
    }

    @Test
    void testGetAll() {
        Property property1 = new Property();
        property1.setIdProperty(1L);
        List<Property> propertyList = Arrays.asList(property1);
        when(repositoryProperty.findAll()).thenReturn(propertyList);

        List<PropertyDTO> resultList = propertyService.get();

        assertFalse(resultList.isEmpty());
        assertEquals(property1.getIdProperty(), resultList.get(0).getIdProperty());
    }

    @Test
    void testSave() {
        Property property = new Property();
        property.setIdProperty(1L);
        PropertyDTO propertyDTO = new PropertyDTO();

        when(repositoryProperty.save(any(Property.class))).thenReturn(property);
        PropertyDTO result = propertyService.save(propertyDTO);

        assertNotNull(result);
        assertEquals(property.getIdProperty(), result.getIdProperty());
    }

    @Test
    void testUpdate() {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setIdProperty(1L);
        Property property = new Property();
        property.setIdProperty(1L);

        when(repositoryProperty.findById(1L)).thenReturn(Optional.of(property));
        when(repositoryProperty.save(any(Property.class))).thenReturn(property);

        PropertyDTO updatedResult = propertyService.update(propertyDTO);

        assertNotNull(updatedResult);
        assertEquals(property.getIdProperty(), updatedResult.getIdProperty());
    }

    @Test
    void testDelete() {
        doNothing().when(repositoryProperty).deleteById(1L);
        propertyService.delete(1L);
        verify(repositoryProperty).deleteById(1L);
    }
}
