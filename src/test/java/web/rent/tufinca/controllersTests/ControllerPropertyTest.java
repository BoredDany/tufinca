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

import web.rent.tufinca.controllers.ControllerProperty;
import web.rent.tufinca.dtos.PropertyDTO;
import web.rent.tufinca.services.PropertyService;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(ControllerProperty.class)
class ControllerPropertyTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PropertyService propertyService;

    private PropertyDTO propertyDTO;

    @BeforeEach
    void setUp() {
        propertyDTO = new PropertyDTO();
        propertyDTO.setIdProperty(1L);
        propertyDTO.setName("Beach House");
        propertyDTO.setCountry("Country");
        propertyDTO.setCity("City");
        propertyDTO.setLatitude("00.0000");
        propertyDTO.setLongitude("00.0000");
        propertyDTO.setPrice(1000);
        propertyDTO.setArea(120);
    }

    @Test
    void testGetAllProperties() throws Exception {
        List<PropertyDTO> properties = Arrays.asList(propertyDTO);
        given(propertyService.get()).willReturn(properties);

        mockMvc.perform(get("/grupo23/controllers/property"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].name").value("Beach House"));
    }

    @Test
    void testGetPropertyById() throws Exception {
        given(propertyService.get(1L)).willReturn(propertyDTO);

        mockMvc.perform(get("/grupo23/controllers/property/{id}", 1L))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Beach House"));
    }

    @Test
    void testSaveProperty() throws Exception {
        given(propertyService.save(any(PropertyDTO.class))).willReturn(propertyDTO);

        mockMvc.perform(post("/grupo23/controllers/property")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Beach House\",\"country\":\"Country\",\"city\":\"City\",\"latitude\":\"00.0000\",\"longitude\":\"00.0000\",\"price\":1000,\"area\":120}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Beach House"));
    }

    @Test
    void testUpdateProperty() throws Exception {
        given(propertyService.save(any(PropertyDTO.class))).willReturn(propertyDTO);

        mockMvc.perform(put("/grupo23/controllers/property")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Mountain House\",\"country\":\"Country\",\"city\":\"City\",\"latitude\":\"00.0000\",\"longitude\":\"00.0000\",\"price\":1500,\"area\":150}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Mountain House"));
    }

    @Test
    void testDeleteProperty() throws Exception {
        willDoNothing().given(propertyService).delete(1L);

        mockMvc.perform(delete("/grupo23/controllers/property/{id}", 1L))
               .andExpect(status().isOk());

        verify(propertyService).delete(1L);
    }
}