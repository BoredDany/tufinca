package web.rent.tufinca.controllersTests;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;
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

import web.rent.tufinca.controllers.ControllerRent;
import web.rent.tufinca.dtos.RentDTO;
import web.rent.tufinca.services.RentService;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(ControllerRent.class)
class ControllerRentTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentService rentService;

    private RentDTO rentDTO;

    @BeforeEach
    void setUp() {
        rentDTO = new RentDTO();
        rentDTO.setIdRent(1L);
        rentDTO.setNumPeople(4);
        rentDTO.setPrice(200);
    }

    //Prueba 21: Verificar que se obtienen todas las rentas, creado por: Santiago Castro, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Spring Boot Test, Mockito, JsonPath, RentService, RentDTO, MockMvc
    @Test
    void probarObtenerTodasLasRentas() throws Exception {
        List<RentDTO> rents = Arrays.asList(rentDTO);
        given(rentService.get()).willReturn(rents);

        mockMvc.perform(get("/grupo23/controllers/rent"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].idRent").value(1L))
               .andExpect(jsonPath("$[0].numPeople").value(4))
               .andExpect(jsonPath("$[0].price").value(200));
    }

    //Prueba 22: Verificar que se obtiene una renta por su id, creado por: Santiago Castro, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Spring Boot Test, Mockito, JsonPath, RentService, RentDTO, MockMvc
    @Test
    void probarObtenerRentaPorId() throws Exception {
        given(rentService.get(1L)).willReturn(rentDTO);

        mockMvc.perform(get("/grupo23/controllers/rent/{id}", 1L))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.idRent").value(1L))
               .andExpect(jsonPath("$.numPeople").value(4))
               .andExpect(jsonPath("$.price").value(200));
    }

    //Prueba 23: Verificar que se guarda una renta, creado por: Santiago Castro, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Spring Boot Test, Mockito, JsonPath, RentService, RentDTO, MockMvc
    @Test
    void probarGuardarRenta() throws Exception {
        given(rentService.save(any(RentDTO.class))).willReturn(rentDTO);

        mockMvc.perform(post("/grupo23/controllers/rent")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"numPeople\":4,\"price\":200}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.numPeople").value(4))
               .andExpect(jsonPath("$.price").value(200)); 
    }

    //Prueba 24: Verificar que se actualiza una renta, creado por: Santiago Castro, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Spring Boot Test, Mockito, JsonPath, ObjectMapper, RentService, RentDTO, MockMvc
    @Test
    void probarActualizarRenta() throws Exception {
        RentDTO rentUpdatedDTO = new RentDTO();
        rentUpdatedDTO.setIdRent(1L);
        rentUpdatedDTO.setNumPeople(5);
        rentUpdatedDTO.setPrice(500);

        given(rentService.update(any(RentDTO.class))).willReturn(rentUpdatedDTO);

        mockMvc.perform(put("/grupo23/controllers/rent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(rentDTO)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.numPeople").value(5))
               .andExpect(jsonPath("$.price").value(500)); 
    }

    //Prueba 25: Verificar que se elimina una renta, creado por: Santiago Castro, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Spring Boot Test, Mockito, RentService, MockMvc
    @Test
    void probarEliminarRenta() throws Exception {
        willDoNothing().given(rentService).delete(1L);

        mockMvc.perform(delete("/grupo23/controllers/rent/{id}", 1L))
               .andExpect(status().isOk());

        verify(rentService).delete(1L);
    }
}