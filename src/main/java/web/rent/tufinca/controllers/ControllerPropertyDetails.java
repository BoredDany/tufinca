package web.rent.tufinca.controllers;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.rent.tufinca.entities.PropertyDetail;
import web.rent.tufinca.repositories.RepositoryPropertyDetail;
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
@RequestMapping("/grupo23/controllers/propertydetails")
public class ControllerPropertyDetails {
    @Autowired
    private RepositoryPropertyDetail repositoryPropertyDetail; // Cambiado a RepositoryPropertyDetails

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PropertyDetail> get() throws Exception {
        Iterable<PropertyDetail> propertyDetails = repositoryPropertyDetail.findAll(); // Cambiado a PropertyDetails
        return StreamSupport.stream(propertyDetails.spliterator(), false).collect(Collectors.toList());
    }
    
    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PropertyDetail get(@PathVariable Long id) throws Exception {
        return repositoryPropertyDetail.findById(id).get(); // Cambiado a PropertyDetails
    }
    
    @CrossOrigin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PropertyDetail save(@RequestBody PropertyDetail propertyDetails) throws Exception { // Cambiado a PropertyDetails
        return repositoryPropertyDetail.save(propertyDetails); // Cambiado a PropertyDetails
    }

    @CrossOrigin
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PropertyDetail update(@RequestBody PropertyDetail propertyDetails) { // Cambiado a PropertyDetails
        return repositoryPropertyDetail.save(propertyDetails); // Cambiado a PropertyDetails
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) throws Exception { // Cambiado el nombre del método a delete para reflejar mejor su función
        repositoryPropertyDetail.deleteById(id);
    }
}