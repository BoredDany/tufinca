package web.rent.tufinca.controllersTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import web.rent.tufinca.controllers.ControllerUser;
import web.rent.tufinca.dtos.UserDTO;
import web.rent.tufinca.services.UserService;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(ControllerUser.class)
class ControllerUserTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserDTO userDTO;
    private Long id = 66L;

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO();
        userDTO.setIdUser(1L);
        userDTO.setName("John Doe");
        userDTO.setEmail("johndoe@example.com");
        userDTO.setPhone(1234567890);
        userDTO.setPhoto("photo.jpg");
    }

    //Prueba 26: Verificar que se obtienen todos los usuarios, creado por: Santiago Castro, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Spring Boot Test, Mockito, JsonPath, UserService, UserDTO, MockMvc
    @Test
    void testGetAllUsers() throws Exception {
        List<UserDTO> users = Arrays.asList(userDTO);
        given(userService.get()).willReturn(users);

        mockMvc.perform(get("/grupo23/controllers/user/"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    //Prueba 27: Verificar que se obtiene un usuario por su id, creado por: Santiago Castro, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Spring Boot Test, Mockito, JsonPath, UserService, UserDTO, MockMvc
    @Test
    void testGetUserById() throws Exception {
        given(userService.get(1L)).willReturn(userDTO);

        mockMvc.perform(get("/grupo23/controllers/user/{id}", 1L))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("John Doe"));
    }

    //Prueba 28: Verificar que se guarda un usuario, creado por: Santiago Castro, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Spring Boot Test, Mockito, JsonPath, UserService, UserDTO, MockMvc
    @Test
    void testSaveUser() throws Exception {
        given(userService.save(any(UserDTO.class))).willReturn(userDTO);

        mockMvc.perform(post("/grupo23/controllers/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John Doe\",\"email\":\"johndoe@example.com\",\"phone\":1234567890,\"money\":1000,\"photo\":\"photo.jpg\",\"status\":\"ACTIVE\"}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("John Doe"));
    }

    //Prueba 29: Verificar que se actualiza un usuario, creado por: Santiago Castro, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Spring Boot Test, Mockito, JsonPath, ObjectMapper, UserService, UserDTO, MockMvc
    /*@Test
    void testUpdateUser() throws Exception {
        UserDTO userUpdatedDTO = new UserDTO();
        userUpdatedDTO.setIdUser(1L);
        userUpdatedDTO.setName("New Name");
        userUpdatedDTO.setEmail("johndoe@example.com");
        userUpdatedDTO.setPhone(1234567890);
        userUpdatedDTO.setPhoto("photo.jpg");
        
        given(userService.update(any(UserDTO.class), id)).willReturn(userUpdatedDTO);

        mockMvc.perform(put("/grupo23/controllers/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDTO)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("New Name"));
    }*/

    //Prueba 30: Verificar que se elimina un usuario, creado por: Santiago Castro, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Spring Boot Test, Mockito, UserService, MockMvc
    @Test
    void testDeleteUser() throws Exception {
        willDoNothing().given(userService).delete(1L);

        mockMvc.perform(delete("/grupo23/controllers/user/{id}", 1L))
               .andExpect(status().isOk());

        verify(userService).delete(1L);
    }
}
