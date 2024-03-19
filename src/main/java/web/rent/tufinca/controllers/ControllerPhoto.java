package web.rent.tufinca.controllers;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.rent.tufinca.dtos.PhotoDTO;
import web.rent.tufinca.entities.Photo;
import web.rent.tufinca.repositories.RepositoryPhoto;
import web.rent.tufinca.services.PhotoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/grupo23/controllers/photo")
public class ControllerPhoto {
    @Autowired
    private PhotoService servicePhoto;

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PhotoDTO> get() {
        return servicePhoto.get();
    }
    
    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PhotoDTO get(@PathVariable Long id) throws Exception {
        return servicePhoto.get(id);
    }
    
    @CrossOrigin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PhotoDTO save(@RequestBody PhotoDTO photoDTO) throws Exception { // Cambiado a Photo
        return servicePhoto.save(photoDTO); // Cambiado a Photo
    }

    @CrossOrigin
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PhotoDTO update(@RequestBody PhotoDTO photoDTO) throws IllegalArgumentException{ // Cambiado a Photo
        return servicePhoto.update(photoDTO);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) { // Cambiado el nombre del método a delete para reflejar mejor su función
        servicePhoto.delete(id);
    }
}