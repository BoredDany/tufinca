package web.rent.tufinca.controllersTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO();
        userDTO.setIdUser(1L);
        userDTO.setName("John Doe");
        userDTO.setEmail("johndoe@example.com");
        userDTO.setPhone(1234567890);
        userDTO.setPhoto("photo.jpg");
        //userDTO.setStatus("ACTIVE");
    }

    @Test
    void testGetAllUsers() throws Exception {
        List<UserDTO> users = Arrays.asList(userDTO);
        given(userService.get()).willReturn(users);

        mockMvc.perform(get("/grupo23/controllers/user/"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    void testGetUserById() throws Exception {
        given(userService.get(1L)).willReturn(userDTO);

        mockMvc.perform(get("/grupo23/controllers/user/{id}", 1L))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testSaveUser() throws Exception {
        given(userService.save(any(UserDTO.class))).willReturn(userDTO);

        mockMvc.perform(post("/grupo23/controllers/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John Doe\",\"email\":\"johndoe@example.com\",\"phone\":1234567890,\"money\":1000,\"photo\":\"photo.jpg\",\"status\":\"ACTIVE\"}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testUpdateUser() throws Exception {
        given(userService.save(any(UserDTO.class))).willReturn(userDTO);

        mockMvc.perform(put("/grupo23/controllers/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Jane Doe\",\"email\":\"janedoe@example.com\",\"phone\":1234567890,\"money\":1000,\"photo\":\"photo.jpg\",\"status\":\"ACTIVE\"}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Jane Doe"));
    }

    @Test
    void testDeleteUser() throws Exception {
        willDoNothing().given(userService).delete(1L);

        mockMvc.perform(delete("/grupo23/controllers/user/{id}", 1L))
               .andExpect(status().isOk());

        verify(userService).delete(1L);
    }
}
