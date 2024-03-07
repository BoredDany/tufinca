package web.rent.tufinca.controllers;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.rent.tufinca.entities.Photo;
import web.rent.tufinca.entities.User;
import web.rent.tufinca.repositories.RepositoryPhoto;
import web.rent.tufinca.repositories.RepositoryUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/grupo23/controllers")
public class ControllerPhoto {
    @Autowired
    private RepositoryPhoto repositoryPhoto; // Cambiado a RepositoryPhoto

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Photo> get() throws Exception {
        Iterable<Photo> photos = repositoryPhoto.findAll(); // Cambiado a Photo
        return StreamSupport.stream(photos.spliterator(), false).collect(Collectors.toList());
    }
    
    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Photo get(@PathVariable Long id) throws Exception {
        return repositoryPhoto.findById(id).get(); // Cambiado a Photo
    }
    
    @CrossOrigin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Photo save(@RequestBody Photo photo) throws Exception { // Cambiado a Photo
        return repositoryPhoto.save(photo); // Cambiado a Photo
    }

    @CrossOrigin
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Photo update(@RequestBody Photo photo) { // Cambiado a Photo
        return repositoryPhoto.save(photo); // Cambiado a Photo
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) throws Exception { // Cambiado el nombre del método a delete para reflejar mejor su función
        repositoryPhoto.deleteById(id);
    }
}