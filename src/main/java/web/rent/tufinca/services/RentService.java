package web.rent.tufinca.services;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List; 

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.rent.tufinca.dtos.RentDTO;
import web.rent.tufinca.entities.Rent;
import web.rent.tufinca.repositories.RepositoryRent;

@Service
public class RentService {
    
    @Autowired
    private RepositoryRent repositoryRent;

    @Autowired
    private ModelMapper modelMapper;


    public RentDTO get(Long id){
        Optional<Rent> rentOptional = repositoryRent.findById(id);
        RentDTO rentDTO = null;
        if (rentOptional.isPresent()){
            rentDTO = modelMapper.map(rentOptional.get(), RentDTO.class);
        }
        return rentDTO;
    }

    public List<RentDTO> get(){
        List<Rent> rents = (List<Rent>) repositoryRent.findAll();
        return rents.stream()
        .map(rent -> modelMapper.map(rent, RentDTO.class))
        .collect(Collectors.toList());
    }

    public RentDTO save(RentDTO rentDTO){
        Rent rent = modelMapper.map(rentDTO, Rent.class);
        rent = repositoryRent.save(rent);
        rentDTO.setIdRent(rent.getIdRent());
        return rentDTO;
    }

    public RentDTO update(RentDTO rentDTO){
        rentDTO = get(rentDTO.getIdRent());
        if (rentDTO == null){
            throw new IllegalArgumentException("Unidentified registry");
        }
        Rent rent = modelMapper.map(rentDTO, Rent.class);
        rent = repositoryRent.save(rent);
        rentDTO = modelMapper.map(rent, RentDTO.class);
        return rentDTO;
    }

    public void delete(Long id){
        repositoryRent.deleteById(id);
    }
}