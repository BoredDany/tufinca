package web.rent.tufinca.entitiesTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import web.rent.tufinca.entities.Status;
import web.rent.tufinca.entities.User;

import java.util.ArrayList;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setIdUser(1L);
        user.setName("John Doe");
        user.setEmail("johndoe@example.com");
        user.setPassword("secret");
        user.setPhone(1234567890);
        user.setMoney(1000);
        user.setPhoto("photo.jpg");
        user.setStatus(Status.ACTIVE);
        user.setProperties(new ArrayList<>());
        user.setReservations(new ArrayList<>());
        user.setRents(new ArrayList<>());
        user.setRentRequests(new ArrayList<>());
        user.setReservationRequests(new ArrayList<>());
    }

    @Test
    void testUserAttributes() {
        assertEquals(1L, user.getIdUser());
        assertEquals("John Doe", user.getName());
        assertEquals("johndoe@example.com", user.getEmail());
        assertEquals("secret", user.getPassword());
        assertEquals(Integer.valueOf(1234567890), user.getPhone());
        assertEquals(Integer.valueOf(1000), user.getMoney());
        assertEquals("photo.jpg", user.getPhoto());
        assertEquals(Status.ACTIVE, user.getStatus());
        assertNotNull(user.getProperties());
        assertNotNull(user.getReservations());
        assertNotNull(user.getRents());
        assertNotNull(user.getRentRequests());
        assertNotNull(user.getReservationRequests());
    }

}

