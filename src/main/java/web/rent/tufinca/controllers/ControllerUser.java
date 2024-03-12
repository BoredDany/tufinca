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
@RequestMapping("/grupo23/controllers/user")
public class ControllerUser {
    @Autowired
    private RepositoryUser repositoryUser;

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> get() throws Exception {
        Iterable<User> users = repositoryUser.findAll();
        return StreamSupport.stream(users.spliterator(), false).collect(Collectors.toList());
    }
    
    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable Long id) throws Exception {
        return repositoryUser.findById(id).get();
    }
    
    @CrossOrigin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User save(@RequestBody User user) throws Exception {
        return repositoryUser.save(user);
    }

    @CrossOrigin
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User update(@RequestBody User user) {
        return repositoryUser.save(user);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable Long id) throws Exception {
        repositoryUser.deleteById(id);
    }
}