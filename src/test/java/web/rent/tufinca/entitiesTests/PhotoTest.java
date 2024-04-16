package web.rent.tufinca.entitiesTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import web.rent.tufinca.entities.Photo;
import web.rent.tufinca.entities.Property;

class PhotoTest {

    private Photo photo;
    private Property property;

    @BeforeEach
    void setUp() {
        photo = new Photo();
        photo.setIdPhoto(1L);
        photo.setUrl("http://example.com/photo.jpg");
        photo.setDescription("A beautiful beach house");

        property = new Property();
        photo.setProperty(property);
    }

    @Test
    void testPhotoAttributes() {
        assertEquals(1L, photo.getIdPhoto());
        assertEquals("http://example.com/photo.jpg", photo.getUrl());
        assertEquals("A beautiful beach house", photo.getDescription());
        assertNotNull(photo.getProperty());
    }

}
