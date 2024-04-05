package web.rent.tufinca.servicesTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import web.rent.tufinca.dtos.PhotoDTO;
import web.rent.tufinca.entities.Photo;
import web.rent.tufinca.repositories.RepositoryPhoto;
import web.rent.tufinca.services.PhotoService;

class PhotoServiceTest {

    @InjectMocks
    private PhotoService photoService;

    @Mock
    private RepositoryPhoto repositoryPhoto;

    @Mock
    private ModelMapper modelMapper;

    private PhotoDTO photoDTO;
    private Photo photo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        photoDTO = new PhotoDTO();
        photoDTO.setIdPhoto(1L);

        photo = new Photo();
        photo.setIdPhoto(1L);
    }

    //Prueba 31: Verificar que se obtiene una foto por su id, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, ModelMapper, PhotoService, PhotoDTO, Photo, RepositoryPhoto
    @Test
    void testGetById() {
        when(repositoryPhoto.findById(1L)).thenReturn(Optional.of(photo));
        when(modelMapper.map(photo, PhotoDTO.class)).thenReturn(photoDTO);

        PhotoDTO result = photoService.get(1L);

        assertEquals(photoDTO, result);
    }

    //Prueba 32: Verificar que se obtienen todas las fotos, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, ModelMapper, PhotoService, PhotoDTO, Photo, RepositoryPhoto
    @Test
    void testGetAll() {
        when(repositoryPhoto.findAll()).thenReturn(Arrays.asList(photo));
        when(modelMapper.map(photo, PhotoDTO.class)).thenReturn(photoDTO);

        assertEquals(Arrays.asList(photoDTO), photoService.get());
    }

    //Prueba 33: Verificar que se guarda una foto, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, ModelMapper, PhotoService, PhotoDTO, Photo, RepositoryPhoto
    @Test
    void testSave() {
        when(modelMapper.map(photoDTO, Photo.class)).thenReturn(photo);
        when(repositoryPhoto.save(photo)).thenReturn(photo);

        PhotoDTO result = photoService.save(photoDTO);

        assertEquals(photoDTO, result);
    }

    //Prueba 34: Verificar que se actualiza una foto, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, ModelMapper, PhotoService, PhotoDTO, Photo, RepositoryPhoto
    /*@Test
    void testUpdate() {
        when(repositoryPhoto.findById(1L)).thenReturn(Optional.of(photo));
        when(modelMapper.map(photo, PhotoDTO.class)).thenReturn(photoDTO);
        when(modelMapper.map(photoDTO, Photo.class)).thenReturn(photo);
        when(repositoryPhoto.save(photo)).thenReturn(photo);

        PhotoDTO result = photoService.update(photoDTO);

        assertEquals(photoDTO, result);
    }*/

    //Prueba 35: Verificar que se elimina una foto, creado por: Daniela Martinez, ejecutado por Daniela Martínez
    // Dependencies: JUnit 5, Mockito, PhotoService, RepositoryPhoto
    @Test
    void testDelete() {
        doNothing().when(repositoryPhoto).deleteById(1L);

        photoService.delete(1L);

        verify(repositoryPhoto, times(1)).deleteById(1L);
    }
}