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

import web.rent.tufinca.controllers.ControllerRentRequest;
import web.rent.tufinca.dtos.RentRequestDTO;
import web.rent.tufinca.services.RentRequestService;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(ControllerRentRequest.class)
class ControllerRentRequestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentRequestService rentRequestService;

    private RentRequestDTO rentRequestDTO;
    private Long id = 66L;

    @BeforeEach
    void setUp() {
        rentRequestDTO = new RentRequestDTO();
        rentRequestDTO.setIdRentRequest(1L);
        rentRequestDTO.setNumPeople(4);
        rentRequestDTO.setPrice(500);
    }

    //Prueba 16: Verificar que se obtienen todas las solicitudes de alquiler, creado por: Santiago Castro, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Spring Boot Test, Mockito, JsonPath, RenRequestService, RentRequestDTO, MockMvc
    @Test
    void testGetAllRentRequests() throws Exception {
        List<RentRequestDTO> rentRequests = Arrays.asList(rentRequestDTO);
        given(rentRequestService.get()).willReturn(rentRequests);

        mockMvc.perform(get("/grupo23/controllers/rentrequest"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].price").value(500));
    }

    //Prueba 17: Verificar que se obtiene una solicitud de alquiler por su id, creado por: Santiago Castro, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Spring Boot Test, Mockito, JsonPath, RenRequestService, RentRequestDTO, MockMvc
    @Test
    void testGetRentRequestById() throws Exception {
        given(rentRequestService.get(1L)).willReturn(rentRequestDTO);

        mockMvc.perform(get("/grupo23/controllers/rentrequest/{id}", 1L))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.price").value(500));
    }

    //Prueba 18: Verificar que se guarda una solicitud de alquiler, creado por: Santiago Castro, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Spring Boot Test, Mockito, JsonPath, RenRequestService, RentRequestDTO, MockMvc
    @Test
    void testSaveRentRequest() throws Exception {
        given(rentRequestService.save(any(RentRequestDTO.class))).willReturn(rentRequestDTO);

        mockMvc.perform(post("/grupo23/controllers/rentrequest")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"numPeople\":4,\"price\":500}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.price").value(500));
    }

    //Prueba 19: Verificar que se actualiza una solicitud de alquiler, creado por: Santiago Castro, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Spring Boot Test, Mockito, JsonPath, ObjectMapper, RenRequestService, RentRequestDTO, MockMvc
    @Test
    void testUpdateRentRequest() throws Exception {
        RentRequestDTO rentRequestUpdatedDTO = new RentRequestDTO();
        rentRequestUpdatedDTO.setIdRentRequest(1L);
        rentRequestUpdatedDTO.setNumPeople(400);
        rentRequestUpdatedDTO.setPrice(500);
        
        given(rentRequestService.update(any(RentRequestDTO.class), id)).willReturn(rentRequestUpdatedDTO);

        mockMvc.perform(put("/grupo23/controllers/rentrequest/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(rentRequestDTO)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.numPeople").value(400));
    }

    //Prueba 20: Verificar que se elimina una solicitud de alquiler, creado por: Santiago Castro, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Spring Boot Test, Mockito, RentRequestService, MockMvc
    @Test
    void testDeleteRentRequest() throws Exception {
        willDoNothing().given(rentRequestService).delete(1L);

        mockMvc.perform(delete("/grupo23/controllers/rentrequest/{id}", 1L))
               .andExpect(status().isOk());

        verify(rentRequestService).delete(1L);
    }
}

