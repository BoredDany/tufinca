package web.rent.tufinca.controllers;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.rent.tufinca.dtos.PropertyDTO;
import web.rent.tufinca.services.PropertyService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/grupo23/properties")
public class ControllerProperty {
    @Autowired
    private PropertyService propertyService; 

    @CrossOrigin
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PropertyDTO> get(Authentication auth) {
        return propertyService.get();
    }
    
    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PropertyDTO get(@PathVariable Long id, Authentication auth) {
        return propertyService.get(id);
    }
    
    @CrossOrigin
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public PropertyDTO save(@RequestBody PropertyDTO propertyDTO, Authentication auth) {
        return propertyService.save(propertyDTO);
    }

    @CrossOrigin
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PropertyDTO update(@RequestBody PropertyDTO propertyDTO, @PathVariable Long id, Authentication auth) throws IllegalArgumentException{
        return propertyService.update(propertyDTO, id);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id, Authentication auth)  {
        propertyService.delete(id);
    }
}