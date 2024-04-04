package web.rent.tufinca.servicesTests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import web.rent.tufinca.dtos.PhotoDTO;
import web.rent.tufinca.entities.Photo;
import web.rent.tufinca.repositories.RepositoryPhoto;
import web.rent.tufinca.services.PhotoService;

class PhotoServiceTest {

    private PhotoService photoService;
    private RepositoryPhoto repositoryPhoto;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        repositoryPhoto = mock(RepositoryPhoto.class);
        modelMapper = mock(ModelMapper.class);  // Mock ModelMapper también
        photoService = new PhotoService(repositoryPhoto, modelMapper); // Suponiendo que existe un constructor adecuado en PhotoService
    }

    @Test
    void testGetById() {
        Photo photo = new Photo();
        photo.setIdPhoto(1L);
        when(repositoryPhoto.findById(1L)).thenReturn(Optional.of(photo));
        when(modelMapper.map(any(), eq(PhotoDTO.class))).thenReturn(new PhotoDTO()); // Mock the mapping

        PhotoDTO result = photoService.get(1L);

        assertNotNull(result);
        assertEquals(photo.getIdPhoto(), result.getIdPhoto());
    }

    @Test
    void testGetAll() {
        Photo photo = new Photo();
        photo.setIdPhoto(1L);
        when(repositoryPhoto.findAll()).thenReturn(Arrays.asList(photo));
        when(modelMapper.map(any(), eq(PhotoDTO.class))).thenReturn(new PhotoDTO()); // Mock the mapping

        List<PhotoDTO> result = photoService.get();

        assertFalse(result.isEmpty());
        assertEquals(photo.getIdPhoto(), result.get(0).getIdPhoto());
    }

    @Test
    void testSave() {
        Photo photo = new Photo();
        photo.setIdPhoto(1L);
        when(repositoryPhoto.save(any(Photo.class))).thenReturn(photo);
        when(modelMapper.map(any(), eq(Photo.class))).thenReturn(photo); // Mock the mapping to Photo
        when(modelMapper.map(any(), eq(PhotoDTO.class))).thenReturn(new PhotoDTO()); // Mock the mapping to PhotoDTO

        PhotoDTO photoDTO = new PhotoDTO();
        photoDTO = photoService.save(photoDTO);

        assertNotNull(photoDTO);
        assertEquals(photo.getIdPhoto(), photoDTO.getIdPhoto());
    }

    @Test
    void testUpdate() {
        Photo photo = new Photo();
        photo.setIdPhoto(1L);
        when(repositoryPhoto.findById(1L)).thenReturn(Optional.of(photo));
        when(repositoryPhoto.save(any(Photo.class))).thenReturn(photo);
        when(modelMapper.map(any(), eq(Photo.class))).thenReturn(photo); // Mock the mapping to Photo
        when(modelMapper.map(any(), eq(PhotoDTO.class))).thenReturn(new PhotoDTO()); // Mock the mapping to PhotoDTO

        PhotoDTO photoDTO = new PhotoDTO();
        photoDTO.setIdPhoto(1L);
        photoDTO = photoService.update(photoDTO);

        assertNotNull(photoDTO);
        assertEquals(photo.getIdPhoto(), photoDTO.getIdPhoto());
    }

    @Test
    void testDelete() {
        doNothing().when(repositoryPhoto).deleteById(1L);

        photoService.delete(1L);

        verify(repositoryPhoto).deleteById(1L);
    }
}
