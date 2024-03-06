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
public class ControllerRentRequest {
    @Autowired
    private RepositoryRentRequest repositoryRentRequest;

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RentRequest> get() throws Exception {
        Iterable<RentRequest> rentRequests = repositoryRentRequest.findAll();
        return StreamSupport.stream(rentRequests.spliterator(), false).collect(Collectors.toList());
    }
    
    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RentRequest get(@PathVariable Long id) throws Exception {
        return repositoryRentRequest.findById(id).get();
    }
    
    @CrossOrigin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RentRequest save(@RequestBody RentRequest rentRequest) throws Exception {
        return repositoryRentRequest.save(rentRequest); 
    }

    @CrossOrigin
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RentRequest update(@RequestBody RentRequest rentRequest) {
        return repositoryRentRequest.save(rentRequest); 
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) throws Exception { 
        repositoryRentRequest.deleteById(id);
    }
}