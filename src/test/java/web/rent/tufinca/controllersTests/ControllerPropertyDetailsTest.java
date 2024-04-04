package web.rent.tufinca.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import web.rent.tufinca.controllers.ControllerPropertyDetails;
import web.rent.tufinca.dtos.PropertyDetailDTO;
import web.rent.tufinca.services.PropertyDetailService;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(ControllerPropertyDetails.class)
public class ControllerPropertyDetailsTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PropertyDetailService propertyDetailService;

    private PropertyDetailDTO propertyDetailDTO;

    @BeforeEach
    void setUp() {
        propertyDetailDTO = new PropertyDetailDTO();
        propertyDetailDTO.setIdPropertyDetail(1L);
        propertyDetailDTO.setNumToilets(2);
        propertyDetailDTO.setNumRooms(3);
        propertyDetailDTO.setNumKitchens(1);
        propertyDetailDTO.setNumLevel(2);
        propertyDetailDTO.setDescription("Spacious beach house with sea view");
    }

    @Test
    void testGetAllPropertyDetails() throws Exception {
        List<PropertyDetailDTO> propertyDetails = Arrays.asList(propertyDetailDTO);
        given(propertyDetailService.get()).willReturn(propertyDetails);

        mockMvc.perform(get("/grupo23/controllers/propertydetails"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].description").value("Spacious beach house with sea view"));
    }

    @Test
    void testGetPropertyDetailById() throws Exception {
        given(propertyDetailService.get(1L)).willReturn(propertyDetailDTO);

        mockMvc.perform(get("/grupo23/controllers/propertydetails/{id}", 1L))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.description").value("Spacious beach house with sea view"));
    }

    @Test
    void testSavePropertyDetail() throws Exception {
        given(propertyDetailService.save(any(PropertyDetailDTO.class))).willReturn(propertyDetailDTO);

        mockMvc.perform(post("/grupo23/controllers/propertydetails")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"numToilets\":2,\"numRooms\":3,\"numKitchens\":1,\"numLevel\":2,\"description\":\"Spacious beach house with sea view\"}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.description").value("Spacious beach house with sea view"));
    }

    @Test
    void testUpdatePropertyDetail() throws Exception {
        given(propertyDetailService.update(any(PropertyDetailDTO.class))).willReturn(propertyDetailDTO);

        mockMvc.perform(put("/grupo23/controllers/propertydetails")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"numToilets\":3,\"numRooms\":4,\"numKitchens\":2,\"numLevel\":3,\"description\":\"Updated description\"}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.description").value("Updated description"));
    }

    @Test
    void testDeletePropertyDetail() throws Exception {
        willDoNothing().given(propertyDetailService).delete(1L);

        mockMvc.perform(delete("/grupo23/controllers/propertydetails/{id}", 1L))
               .andExpect(status().isOk());

        verify(propertyDetailService).delete(1L);
    }
}
