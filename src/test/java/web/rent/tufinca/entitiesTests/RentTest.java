package web.rent.tufinca.entitiesTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import web.rent.tufinca.entities.Property;
import web.rent.tufinca.entities.Rent;
import web.rent.tufinca.entities.User;

class RentTest {

    private Rent rent;

    @BeforeEach
    void setUp() {
        rent = new Rent();
        rent.setIdRent(1L);
        rent.setNumPeople(4);
        rent.setPrice(200);
        rent.setDateStart("2023-01-01");
        rent.setDateEnd("2023-01-07");
        rent.setPayment(1);
        rent.setRatingOwner(5);
        rent.setRatingRenter(4);

        User owner = new User();
        owner.setName("Dueño Andres");

        User renter = new User();
        renter.setName("Visitante Matias");

        Property property = new Property();
        property.setName("Mansion Web");

        rent.setOwner(owner);
        rent.setRenter(renter);
        rent.setProperty(property);
    }

    @Test
    void testRentAttributes() {
        assertEquals(1L, rent.getIdRent());
        assertEquals(4, rent.getNumPeople());
        assertEquals(200, rent.getPrice());
        assertEquals("2023-01-01", rent.getDateStart());
        assertEquals("2023-01-07", rent.getDateEnd());
        assertEquals(1, rent.getPayment());
        assertEquals(5, rent.getRatingOwner());
        assertEquals(4, rent.getRatingRenter());
        assertEquals("Dueño Andres", rent.getOwner().getName());
        assertEquals("Visitante Matias", rent.getRenter().getName());
        assertEquals("Mansion Web", rent.getProperty().getName());
    }

}
