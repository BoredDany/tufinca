package web.rent.tufinca.services;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List; 

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.rent.tufinca.dtos.PropertyDTO;
import web.rent.tufinca.entities.Property;
import web.rent.tufinca.repositories.RepositoryProperty;

@Service
public class PropertyService {
    @Autowired
    private RepositoryProperty repositoryProperty;

    @Autowired
    private ModelMapper modelMapper;


    public PropertyDTO get(Long id){
        Optional<Property> propertyOptional = repositoryProperty.findById(id);
        PropertyDTO propertyDTO = null;
        if (propertyOptional.isPresent()){
            propertyDTO = modelMapper.map(propertyOptional.get(), PropertyDTO.class);
        }
        return propertyDTO;
    }

    public List<PropertyDTO> get(){
        List<Property> properties = (List<Property>) repositoryProperty.findAll();
        return properties.stream()
        .map(property -> modelMapper.map(property, PropertyDTO.class))
        .collect(Collectors.toList());
    }

    public PropertyDTO save(PropertyDTO propertyDTO){
        Property property = modelMapper.map(propertyDTO, Property.class);
        property = repositoryProperty.save(property);
        propertyDTO.setIdProperty(property.getIdProperty());
        return propertyDTO;
    }

    public PropertyDTO update(PropertyDTO propertyDTO, Long id){
        Property property = repositoryProperty.findById(id).orElseThrow(() -> new IllegalArgumentException("Unidentified registry"));
    
        property = modelMapper.map(propertyDTO, Property.class);
        property.setIdProperty(id); // Ensure the id is not changed
    
        property = repositoryProperty.save(property);
    
        propertyDTO = modelMapper.map(property, PropertyDTO.class);
    
        return propertyDTO;
    }

    public void delete(Long id){
        repositoryProperty.deleteById(id);
    }
}