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
    private Long id = 66L;

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

    //Prueba 6: Verificar que se obtienen todos los detalles de las propiedades, creado por: Santiago Castro, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Spring Boot Test, Mockito, JsonPath, PropertyDetailServiceService, PropertyDetailDTO, MockMvc
    @Test
    void testGetAllPropertyDetails() throws Exception {
        List<PropertyDetailDTO> propertyDetails = Arrays.asList(propertyDetailDTO);
        given(propertyDetailService.get()).willReturn(propertyDetails);

        mockMvc.perform(get("/grupo23/controllers/propertydetails/"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].description").value("Spacious beach house with sea view"));
    }

    //Prueba 7: Verificar que se obtiene un detalle de propiedad por su id, creado por: Santiago Castro, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Spring Boot Test, Mockito, JsonPath, PropertyDetailServiceService, PropertyDetailDTO, MockMvc
    @Test
    void testGetPropertyDetailById() throws Exception {
        given(propertyDetailService.get(1L)).willReturn(propertyDetailDTO);

        mockMvc.perform(get("/grupo23/controllers/propertydetails/{id}", 1L))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.description").value("Spacious beach house with sea view"));
    }

    //Prueba 8: Verificar que se guarda un detalle de propiedad, creado por: Santiago Castro, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Spring Boot Test, Mockito, JsonPath, PropertyDetailServiceService, PropertyDetailDTO, MockMvc
    @Test
    void testSavePropertyDetail() throws Exception {
        given(propertyDetailService.save(any(PropertyDetailDTO.class))).willReturn(propertyDetailDTO);

        mockMvc.perform(post("/grupo23/controllers/propertydetails/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"numToilets\":2,\"numRooms\":3,\"numKitchens\":1,\"numLevel\":2,\"description\":\"Spacious beach house with sea view\"}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.description").value("Spacious beach house with sea view"));
    }

    //Prueba 9: Verificar que se actualiza un detalle de propiedad, creado por: Santiago Castro, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Spring Boot Test, Mockito, JsonPath, ObjectMapper, PropertyDetailServiceService, PropertyDetailDTO, MockMvc
    /*@Test
    void testUpdatePropertyDetail() throws Exception {
        PropertyDetailDTO propertyDetailUpdatedDTO = new PropertyDetailDTO();
        propertyDetailUpdatedDTO.setIdPropertyDetail(1L);
        propertyDetailUpdatedDTO.setNumToilets(2);
        propertyDetailUpdatedDTO.setNumRooms(3);
        propertyDetailUpdatedDTO.setNumKitchens(1);
        propertyDetailUpdatedDTO.setNumLevel(2);
        propertyDetailUpdatedDTO.setDescription("New description test");
        
        given(propertyDetailService.update(any(PropertyDetailDTO.class), id)).willReturn(propertyDetailUpdatedDTO);

        mockMvc.perform(put("/grupo23/controllers/propertydetails/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(propertyDetailDTO)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.description").value("New description test"));
    }*/

    //Prueba 10: Verificar que se elimina un detalle de propiedad, creado por: Santiago Castro, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Spring Boot Test, Mockito, PropertyDetailServiceService, MockMvc
    @Test
    void testDeletePropertyDetail() throws Exception {
        willDoNothing().given(propertyDetailService).delete(1L);

        mockMvc.perform(delete("/grupo23/controllers/propertydetails/{id}", 1L))
               .andExpect(status().isOk());

        verify(propertyDetailService).delete(1L);
    }
}
