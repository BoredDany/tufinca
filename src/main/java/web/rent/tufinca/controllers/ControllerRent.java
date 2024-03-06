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
public class ControllerRent {
    @Autowired
    private RepositoryRent repositoryRent; // Cambiado a RepositoryRent

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Rent> get() throws Exception {
        Iterable<Rent> rents = repositoryRent.findAll(); // Cambiado a Rent
        return StreamSupport.stream(rents.spliterator(), false).collect(Collectors.toList());
    }
    
    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Rent get(@PathVariable Long id) throws Exception {
        return repositoryRent.findById(id).get(); // Cambiado a Rent
    }
    
    @CrossOrigin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Rent save(@RequestBody Rent rent) throws Exception { // Cambiado a Rent
        return repositoryRent.save(rent); // Cambiado a Rent
    }

    @CrossOrigin
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Rent update(@RequestBody Rent rent) { // Cambiado a Rent
        return repositoryRent.save(rent); // Cambiado a Rent
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) throws Exception { // Cambiado el nombre del método a delete para reflejar mejor su función
        repositoryRent.deleteById(id);
    }
}