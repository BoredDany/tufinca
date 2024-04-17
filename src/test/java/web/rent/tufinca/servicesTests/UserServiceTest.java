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

import web.rent.tufinca.dtos.UserDTO;
import web.rent.tufinca.entities.Status;
import web.rent.tufinca.entities.User;
import web.rent.tufinca.repositories.RepositoryUser;
import web.rent.tufinca.services.UserService;

class UserServiceTest {

   /* @InjectMocks
    private UserService userService;

    @Mock
    private RepositoryUser userRepository;

    @Mock
    private ModelMapper modelMapper;

    private UserDTO userDTO;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        userDTO = new UserDTO();
        userDTO.setIdUser(1L);

        user = new User();
        user.setIdUser(1L);
        user.setStatus(Status.ACTIVE);
    }

    //Prueba 56: Verificar que se obtiene un usuario por su id, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, ModelMapper, UserService, UserDTO, User, RepositoryUser
    @Test
    void testGetById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        UserDTO result = userService.get(1L);

        assertEquals(userDTO, result);
    }

    //Prueba 57: Verificar que se obtienen todos los usuarios, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, ModelMapper, UserService, UserDTO, User, RepositoryUser
    @Test
    void testGetAll() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        assertEquals(Arrays.asList(userDTO), userService.get());
    }

    //Prueba 58: Verificar que se guarda un usuario, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, ModelMapper, UserService, UserDTO, User, RepositoryUser
    @Test
    void testSave() {
        when(modelMapper.map(userDTO, User.class)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        UserDTO result = userService.save(userDTO);

        assertEquals(userDTO, result);
    }

    //Prueba 59: Verificar que se actualiza un usuario, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, ModelMapper, UserService, UserDTO, User, RepositoryUser
    @Test
    void testUpdate() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);
        when(modelMapper.map(userDTO, User.class)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        UserDTO result = userService.update(userDTO);

        assertEquals(userDTO, result);
    }

    //Prueba 60: Verificar que se elimina un usuario, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, UserService, RepositoryUser
    @Test
    void testDelete() {
        doNothing().when(userRepository).deleteById(1L);

        userService.delete(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }*/
}