package web.rent.tufinca.entitiesTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import web.rent.tufinca.entities.Property;
import web.rent.tufinca.entities.RentRequest;
import web.rent.tufinca.entities.User;

class RentRequestTest {

    private RentRequest rentRequest;
    private User owner;
    private User renter;
    private Property property;

    @BeforeEach
    void setUp() {
        owner = new User();
        owner.setName("Owner Name");

        renter = new User();
        renter.setName("Renter Name");

        property = new Property();
        property.setName("Property Name");

        rentRequest = new RentRequest();
        rentRequest.setIdRentRequest(1L);
        rentRequest.setDateStart("2023-01-01");
        rentRequest.setDateEnd("2023-01-07");
        rentRequest.setNumPeople(4);
        rentRequest.setPrice(500);
        rentRequest.setApproval(1);
        rentRequest.setOwner(owner);
        rentRequest.setRenter(renter);
        rentRequest.setProperty(property);
    }

    @Test
    void testRentRequestAttributes() {
        assertEquals(1L, rentRequest.getIdRentRequest());
        assertEquals("2023-01-01", rentRequest.getDateStart());
        assertEquals("2023-01-07", rentRequest.getDateEnd());
        assertEquals(4, rentRequest.getNumPeople());
        assertEquals(500, rentRequest.getPrice());
        assertEquals(1, rentRequest.getApproval());
        assertEquals(owner, rentRequest.getOwner());
        assertEquals(renter, rentRequest.getRenter());
        assertEquals(property, rentRequest.getProperty());
    }

}
