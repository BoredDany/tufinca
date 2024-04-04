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

import web.rent.tufinca.controllers.ControllerPhoto;
import web.rent.tufinca.dtos.PhotoDTO;
import web.rent.tufinca.services.PhotoService;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(ControllerPhoto.class)
class ControllerPhotoTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PhotoService servicePhoto;

    private PhotoDTO photoDTO;

    @BeforeEach
    void setUp() {
        photoDTO = new PhotoDTO();
        photoDTO.setIdPhoto(1L);
        photoDTO.setUrl("http://example.com/photo.jpg");
        photoDTO.setDescription("A beautiful beach house");
    }

    //Prueba 1: Verificar que se obtienen todas las fotos, creado por: Santiago Castro, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Spring Boot Test, Mockito, JsonPath, PhotoService, PhotoDTO, MockMvc
    @Test
    void testGetAllPhotos() throws Exception {
        List<PhotoDTO> photos = Arrays.asList(photoDTO);
        given(servicePhoto.get()).willReturn(photos);

        mockMvc.perform(get("/grupo23/controllers/photo"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].url").value("http://example.com/photo.jpg"));
    }

    //Prueba 2: Verificar que se obtiene una foto por su id, creado por: Santiago Castro, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Spring Boot Test, Mockito, JsonPath, PhotoService, PhotoDTO, MockMvc
    @Test
    void testGetPhotoById() throws Exception {
        given(servicePhoto.get(1L)).willReturn(photoDTO);

        mockMvc.perform(get("/grupo23/controllers/photo/{id}", 1L))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.url").value("http://example.com/photo.jpg"));
    }

    
    //Prueba 3: Verificar que se guarda una foto, creado por: Santiago Castro, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Spring Boot Test, Mockito, JsonPath, PhotoService, PhotoDTO, MockMvc
    @Test
    void testSavePhoto() throws Exception {
        given(servicePhoto.save(any(PhotoDTO.class))).willReturn(photoDTO);

        mockMvc.perform(post("/grupo23/controllers/photo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"url\":\"http://example.com/photo.jpg\",\"description\":\"A beautiful beach house\"}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.url").value("http://example.com/photo.jpg"));
    }

    //Prueba 4: Verificar que se actualiza una foto, creado por: Santiago Castro, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Spring Boot Test, Mockito, JsonPath, ObjectMapper, PhotoService, PhotoDTO, MockMvc
    @Test
    void testUpdatePhoto() throws Exception {
    PhotoDTO updatedPhotoDTO = new PhotoDTO();
    updatedPhotoDTO.setIdPhoto(1L);
    updatedPhotoDTO.setUrl("http://example.com/updated_photo.jpg");

    given(servicePhoto.update(any(PhotoDTO.class))).willReturn(updatedPhotoDTO);

    mockMvc.perform(put("/grupo23/controllers/photo")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(photoDTO)))
       .andExpect(status().isOk())
       .andExpect(jsonPath("$.url").value("http://example.com/updated_photo.jpg"));
    }

    //Prueba 5: Verificar que se elimina una foto, creado por: Santiago Castro, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Spring Boot Test, Mockito, PhotoService, MockMvc
    @Test
    void testDeletePhoto() throws Exception {
        willDoNothing().given(servicePhoto).delete(1L);

        mockMvc.perform(delete("/grupo23/controllers/photo/{id}", 1L))
               .andExpect(status().isOk());

        verify(servicePhoto).delete(1L);
    }
}