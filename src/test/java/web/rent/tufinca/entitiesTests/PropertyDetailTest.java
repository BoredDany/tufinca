package web.rent.tufinca.entitiesTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import web.rent.tufinca.entities.Property;
import web.rent.tufinca.entities.PropertyDetail;

import java.util.ArrayList;

class PropertyDetailTest {

    private PropertyDetail propertyDetail;

    @BeforeEach
    void setUp() {
        propertyDetail = new PropertyDetail();
        propertyDetail.setIdPropertyDetail(1L);
        propertyDetail.setNumToilets(2);
        propertyDetail.setNumRooms(3);
        propertyDetail.setNumKitchens(1);
        propertyDetail.setNumLevel(2);
        propertyDetail.setDescription("Spacious beach house with sea view");

        Property property = new Property();
        propertyDetail.setProperty(property);

        propertyDetail.setPhotos(new ArrayList<>()); 
        
        // Revisar si la lista de fotos se inicializa aca (No estoy seguro)
    }

    @Test
    void testPropertyDetailAttributes() {
        assertEquals(1L, propertyDetail.getIdPropertyDetail());
        assertEquals(2, propertyDetail.getNumToilets());
        assertEquals(3, propertyDetail.getNumRooms());
        assertEquals(1, propertyDetail.getNumKitchens());
        assertEquals(2, propertyDetail.getNumLevel());
        assertEquals("Spacious beach house with sea view", propertyDetail.getDescription());
        assertNotNull(propertyDetail.getProperty());
        assertNotNull(propertyDetail.getPhotos());
    }

}
