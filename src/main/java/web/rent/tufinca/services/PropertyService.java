package web.rent.tufinca.services;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List; 

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.rent.tufinca.dtos.PropertyDTO;
import web.rent.tufinca.entities.Property;
import web.rent.tufinca.entities.User;
import web.rent.tufinca.repositories.RepositoryProperty;
import web.rent.tufinca.repositories.RepositoryUser;

@Service
public class PropertyService {
    @Autowired
    private RepositoryProperty repositoryProperty;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RepositoryUser userRepository;

    //GET
    public List<PropertyDTO> get(){
        List<Property> properties = (List<Property>) repositoryProperty.findAll();
        return properties.stream()
        .map(property -> modelMapper.map(property, PropertyDTO.class))
        .collect(Collectors.toList());
    }

    //GET BY ID
    public PropertyDTO get(Long id){
        Optional<Property> propertyOptional = repositoryProperty.findById(id);
        PropertyDTO propertyDTO = null;
        if (propertyOptional.isPresent()){
            propertyDTO = modelMapper.map(propertyOptional.get(), PropertyDTO.class);
        }
        return propertyDTO;
    }

    //POST
    public PropertyDTO save(PropertyDTO propertyDTO){
        Property property = modelMapper.map(propertyDTO, Property.class);
        Optional<User> userOptional = userRepository.findById(propertyDTO.getOwnerId());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            property.setUser(user);
            property = repositoryProperty.save(property);
            return propertyDTO;
        }
        
        return null;
    }

    //PUT
    public PropertyDTO update(PropertyDTO propertyDTO, Long id) {
        Optional<Property> optionalProeprty = repositoryProperty.findById(id);
    
        if (optionalProeprty.isPresent()) {
            Property property = optionalProeprty.get();
            property.setName(propertyDTO.getName());
            property.setCountry(propertyDTO.getCountry());
            property.setCity(propertyDTO.getCity());
            property.setLatitude(propertyDTO.getLatitude());
            property.setLongitude(propertyDTO.getLongitude());
            property.setPrice(propertyDTO.getPrice());
            property.setArea(propertyDTO.getArea());
            property.setDescription(propertyDTO.getDescription());
            property.setRooms(propertyDTO.getRooms());
            property.setBathrooms(propertyDTO.getBathrooms());
            property.setParking(propertyDTO.getParking());
            property.setKitchens(propertyDTO.getKitchens());
            property.setFloors(propertyDTO.getFloors());
            property.setStatus(propertyDTO.getStatus());

            property = repositoryProperty.save(property);
            propertyDTO = modelMapper.map(property, PropertyDTO.class);
            return propertyDTO;
        }
        return null;
    }

    //DELETE
    public void delete(Long id){
        repositoryProperty.deleteById(id);
    }
}