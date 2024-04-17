package web.rent.tufinca.controllers;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.rent.tufinca.dtos.PhotoDTO;
import web.rent.tufinca.dtos.UserDTO;
import web.rent.tufinca.services.PhotoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/grupo23/photos/")
public class ControllerPhoto {
    @Autowired
    private PhotoService servicePhoto;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PhotoDTO> get() {
        return servicePhoto.get();
    }
    
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PhotoDTO get(@PathVariable Long id) {
        return servicePhoto.get(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PhotoDTO save(@RequestBody PhotoDTO photoDTO) { 
        return servicePhoto.save(photoDTO); 
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PhotoDTO update(@RequestBody PhotoDTO photoDTO, @PathVariable Long id) throws IllegalArgumentException{ 
        return servicePhoto.update(photoDTO, id);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) {
        servicePhoto.delete(id);
    }
}