package web.rent.tufinca.controllers;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.rent.tufinca.entities.User;
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
public class ControllerPropertyDetails {
    @Autowired
    private RepositoryPropertyDetails repositoryPropertyDetails; // Cambiado a RepositoryPropertyDetails

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PropertyDetails> get() throws Exception {
        Iterable<PropertyDetails> propertyDetails = repositoryPropertyDetails.findAll(); // Cambiado a PropertyDetails
        return StreamSupport.stream(propertyDetails.spliterator(), false).collect(Collectors.toList());
    }
    
    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PropertyDetails get(@PathVariable Long id) throws Exception {
        return repositoryPropertyDetails.findById(id).get(); // Cambiado a PropertyDetails
    }
    
    @CrossOrigin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PropertyDetails save(@RequestBody PropertyDetails propertyDetails) throws Exception { // Cambiado a PropertyDetails
        return repositoryPropertyDetails.save(propertyDetails); // Cambiado a PropertyDetails
    }

    @CrossOrigin
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PropertyDetails update(@RequestBody PropertyDetails propertyDetails) { // Cambiado a PropertyDetails
        return repositoryPropertyDetails.save(propertyDetails); // Cambiado a PropertyDetails
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) throws Exception { // Cambiado el nombre del método a delete para reflejar mejor su función
        repositoryPropertyDetails.deleteById(id);
    }
}