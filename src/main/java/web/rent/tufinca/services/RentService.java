package web.rent.tufinca.services;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List; 

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.rent.tufinca.dtos.RentDTO;
import web.rent.tufinca.entities.Property;
import web.rent.tufinca.entities.Rent;
import web.rent.tufinca.entities.User;
import web.rent.tufinca.repositories.RepositoryProperty;
import web.rent.tufinca.repositories.RepositoryRent;
import web.rent.tufinca.repositories.RepositoryUser;

@Service
public class RentService {
    
    @Autowired
    private RepositoryRent repositoryRent;
    
    @Autowired
    private RepositoryUser userRepository;

    @Autowired
    private RepositoryProperty repositoryProperty;

    @Autowired
    private ModelMapper modelMapper;

    //GET
    public List<RentDTO> get(){
        List<Rent> rents = (List<Rent>) repositoryRent.findAll();
        return rents.stream()
        .map(rent -> modelMapper.map(rent, RentDTO.class))
        .collect(Collectors.toList());
    }

    //GET BY ID
    public RentDTO get(Long id){
        Optional<Rent> rentOptional = repositoryRent.findById(id);
        RentDTO rentDTO = null;
        if (rentOptional.isPresent()){
            rentDTO = modelMapper.map(rentOptional.get(), RentDTO.class);
        }
        return rentDTO;
    }

    //POST

    public RentDTO save(RentDTO rentDTO){
        Rent rent = modelMapper.map(rentDTO, Rent.class);
        Optional<User> ownerOptional = userRepository.findById(rentDTO.getOwnerId());
        Optional<User> renterOptional = userRepository.findById(rentDTO.getRenterId());
        Optional<Property> propertyOptional = repositoryProperty.findById(rentDTO.getPropertyId());

        if (ownerOptional.isPresent() && renterOptional.isPresent() && propertyOptional.isPresent()) {
            User owner = ownerOptional.get();
            User renter = renterOptional.get();
            Property property = propertyOptional.get();
            rent.setOwner(owner);
            rent.setRenter(renter);
            rent.setProperty(property);
            rent = repositoryRent.save(rent);
            return modelMapper.map(rent, RentDTO.class);
        }
        return null;
    }

    //PUT
    public RentDTO update(RentDTO rentDTO, Long id) {
        Optional<Rent> optionalRent = repositoryRent.findById(id);
    
        if (optionalRent.isPresent()) {
            Rent rent = optionalRent.get();
            
            rent.setDateStart(rentDTO.getDateStart());
            rent.setDateEnd(rentDTO.getDateEnd());
            rent.setNumPeople(rentDTO.getNumPeople());
            rent.setPrice(rentDTO.getPrice());
            rent.setPayment(rentDTO.getPayment());
            rent.setRatingOwner(rentDTO.getRatingOwner());
            rent.setRatingRenter(rentDTO.getRatingRenter());
            rent.setStatus(rentDTO.getStatus());
    
            Optional<User> ownerOptional = userRepository.findById(rentDTO.getOwnerId());
            Optional<User> renterOptional = userRepository.findById(rentDTO.getRenterId());
            Optional<Property> propertyOptional = repositoryProperty.findById(rentDTO.getPropertyId());
    
            if (ownerOptional.isPresent() && renterOptional.isPresent() && propertyOptional.isPresent()) {
                User owner = ownerOptional.get();
                User renter = renterOptional.get();
                Property property = propertyOptional.get();
                rent.setOwner(owner);
                rent.setRenter(renter);
                rent.setProperty(property);
            }
    
            rent = repositoryRent.save(rent);
            return modelMapper.map(rent, RentDTO.class);
        }
        
        return null;
    }

    //DELETE
    public void delete(Long id){
        repositoryRent.deleteById(id);
    }
}