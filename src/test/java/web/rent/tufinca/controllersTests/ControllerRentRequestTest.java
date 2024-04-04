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

    @BeforeEach
    void setUp() {
        rentRequestDTO = new RentRequestDTO();
        rentRequestDTO.setIdRentRequest(1L);
        rentRequestDTO.setNumPeople(4);
        rentRequestDTO.setPrice(500);
    }

    @Test
    void testGetAllRentRequests() throws Exception {
        List<RentRequestDTO> rentRequests = Arrays.asList(rentRequestDTO);
        given(rentRequestService.get()).willReturn(rentRequests);

        mockMvc.perform(get("/grupo23/controllers/rentrequest"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].price").value(500));
    }

    @Test
    void testGetRentRequestById() throws Exception {
        given(rentRequestService.get(1L)).willReturn(rentRequestDTO);

        mockMvc.perform(get("/grupo23/controllers/rentrequest/{id}", 1L))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.price").value(500));
    }

    @Test
    void testSaveRentRequest() throws Exception {
        given(rentRequestService.save(any(RentRequestDTO.class))).willReturn(rentRequestDTO);

        mockMvc.perform(post("/grupo23/controllers/rentrequest")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"numPeople\":4,\"price\":500}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.price").value(500));
    }

    @Test
    void testUpdateRentRequest() throws Exception {
        given(rentRequestService.save(any(RentRequestDTO.class))).willReturn(rentRequestDTO);

        mockMvc.perform(put("/grupo23/controllers/rentrequest")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"numPeople\":5,\"price\":600}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.price").value(600));
    }

    @Test
    void testDeleteRentRequest() throws Exception {
        willDoNothing().given(rentRequestService).delete(1L);

        mockMvc.perform(delete("/grupo23/controllers/rentrequest/{id}", 1L))
               .andExpect(status().isOk());

        verify(rentRequestService).delete(1L);
    }
}

