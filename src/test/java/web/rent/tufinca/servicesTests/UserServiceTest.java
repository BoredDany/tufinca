package web.rent.tufinca.servicesTests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import web.rent.tufinca.dtos.UserDTO;
import web.rent.tufinca.entities.User;
import web.rent.tufinca.repositories.RepositoryUser;
import web.rent.tufinca.services.UserService;

class UserServiceTest {

    private UserService userService;
    private RepositoryUser repositoryUser;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        repositoryUser = mock(RepositoryUser.class);
        modelMapper = new ModelMapper();
    }

    @Test
    void testGetById() {
        User user = new User();
        user.setIdUser(1L);
        when(repositoryUser.findById(1L)).thenReturn(Optional.of(user));

        UserDTO result = userService.get(1L);

        assertNotNull(result);
        assertEquals(user.getIdUser(), result.getIdUser());
    }

    @Test
    void testGetAll() {
        User user1 = new User();
        user1.setIdUser(1L);
        List<User> userList = Arrays.asList(user1);
        when(repositoryUser.findAll()).thenReturn(userList);

        List<UserDTO> resultList = userService.get();

        assertFalse(resultList.isEmpty());
        assertEquals(user1.getIdUser(), resultList.get(0).getIdUser());
    }

    @Test
    void testSave() {
        User user = new User();
        user.setIdUser(1L);
        UserDTO userDTO = new UserDTO();
        userDTO.setIdUser(1L);

        when(repositoryUser.save(any(User.class))).thenReturn(user);
        UserDTO savedDTO = userService.save(userDTO);

        assertNotNull(savedDTO);
        assertEquals(user.getIdUser(), savedDTO.getIdUser());
    }

    @Test
    void testUpdate() {
        User user = new User();
        user.setIdUser(1L);
        UserDTO userDTO = new UserDTO();
        userDTO.setIdUser(1L);

        when(repositoryUser.findById(1L)).thenReturn(Optional.of(user));
        when(repositoryUser.save(any(User.class))).thenReturn(user);

        UserDTO updatedDTO = userService.update(userDTO);

        assertNotNull(updatedDTO);
        assertEquals(user.getIdUser(), updatedDTO.getIdUser());
    }

    @Test
    void testDelete() {
        doNothing().when(repositoryUser).deleteById(1L);

        userService.delete(1L);

        verify(repositoryUser).deleteById(1L);
    }
}
