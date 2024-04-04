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

    @Test
    void testGetAllPhotos() throws Exception {
        List<PhotoDTO> photos = Arrays.asList(photoDTO);
        given(servicePhoto.get()).willReturn(photos);

        mockMvc.perform(get("/grupo23/controllers/photo"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].url").value("http://example.com/photo.jpg"));
    }

    @Test
    void testGetPhotoById() throws Exception {
        given(servicePhoto.get(1L)).willReturn(photoDTO);

        mockMvc.perform(get("/grupo23/controllers/photo/{id}", 1L))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.url").value("http://example.com/photo.jpg"));
    }

    @Test
    void testSavePhoto() throws Exception {
        given(servicePhoto.save(any(PhotoDTO.class))).willReturn(photoDTO);

        mockMvc.perform(post("/grupo23/controllers/photo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"url\":\"http://example.com/photo.jpg\",\"description\":\"A beautiful beach house\"}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.url").value("http://example.com/photo.jpg"));
    }

    @Test
    void testUpdatePhoto() throws Exception {
        given(servicePhoto.update(any(PhotoDTO.class))).willReturn(photoDTO);

        mockMvc.perform(put("/grupo23/controllers/photo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"url\":\"http://example.com/updatedphoto.jpg\",\"description\":\"An updated description\"}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.url").value("http://example.com/updatedphoto.jpg"));
    }

    @Test
    void testDeletePhoto() throws Exception {
        willDoNothing().given(servicePhoto).delete(1L);

        mockMvc.perform(delete("/grupo23/controllers/photo/{id}", 1L))
               .andExpect(status().isOk());

        verify(servicePhoto).delete(1L);
    }
}