package web.rent.tufinca.controllers;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.rent.tufinca.dtos.PropertyDetailDTO;
import web.rent.tufinca.services.PropertyDetailService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/grupo23/controllers/propertydetails/")
public class ControllerPropertyDetails {

    @Autowired
    private PropertyDetailService propertyDetailService; 

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PropertyDetailDTO> get() throws Exception {
        return propertyDetailService.get();
    }
    
    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PropertyDetailDTO get(@PathVariable Long id) throws Exception {
        return propertyDetailService.get(id);
    }
    
    @CrossOrigin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PropertyDetailDTO save(@RequestBody PropertyDetailDTO propertyDetailDTO)  {
        return propertyDetailService.save(propertyDetailDTO); 
    }

    @CrossOrigin
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PropertyDetailDTO update(@RequestBody PropertyDetailDTO propertyDetailDTO) throws IllegalArgumentException { 
        return propertyDetailService.update(propertyDetailDTO); 
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) throws Exception { 
        propertyDetailService.delete(id);
    }
}