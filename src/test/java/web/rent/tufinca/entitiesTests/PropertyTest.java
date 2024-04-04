package web.rent.tufinca.entitiesTests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import web.rent.tufinca.entities.Property;

class PropertyTest {

    private Property property;

    @BeforeEach
    void setUp() {
        property = new Property();
        property.setIdProperty(1L);
        property.setName("Beach House");
        property.setCountry("Country");
        property.setCity("City");
        property.setLatitude("00.0000");
        property.setLongitude("00.0000");
        property.setPrice(1000);
        property.setArea(120);
        
        // Revisar si hay relaciones para inicializarlas (No se si hay)
    }

    @Test
    void testPropertyAttributes() {
        assertEquals(1L, property.getIdProperty());
        assertEquals("Beach House", property.getName());
        assertEquals("Country", property.getCountry());
        assertEquals("City", property.getCity());
        assertEquals("00.0000", property.getLatitude());
        assertEquals("00.0000", property.getLongitude());
        assertEquals(Integer.valueOf(1000), property.getPrice());
        assertEquals(Integer.valueOf(120), property.getArea());
        
    }

}
