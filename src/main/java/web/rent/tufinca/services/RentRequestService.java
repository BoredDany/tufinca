package web.rent.tufinca.services;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List; 

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.rent.tufinca.dtos.RentRequestDTO;
import web.rent.tufinca.entities.RentRequest;
import web.rent.tufinca.repositories.RepositoryRentRequest;

@Service
public class RentRequestService {
    @Autowired
    private RepositoryRentRequest repositoryRentRequest;

    @Autowired
    private ModelMapper modelMapper;

    public RentRequestService(RepositoryRentRequest repositoryRentRequest2, ModelMapper modelMapper2) {
        //TODO Auto-generated constructor stub
    }

    public RentRequestDTO get(Long id){
        Optional<RentRequest> rentRequestOptional = repositoryRentRequest.findById(id);
        RentRequestDTO rentRequestDTO = null;
        if (rentRequestOptional.isPresent()){
            rentRequestDTO = modelMapper.map(rentRequestOptional.get(), RentRequestDTO.class);
        }
        return rentRequestDTO;
    }

    public List<RentRequestDTO> get(){
        List<RentRequest> rentRequests = (List<RentRequest>) repositoryRentRequest.findAll();
        return rentRequests.stream()
        .map(rentRequest -> modelMapper.map(rentRequest, RentRequestDTO.class))
        .collect(Collectors.toList());
    }

    public RentRequestDTO save(RentRequestDTO rentRequestDTO){
        RentRequest rentRequest = modelMapper.map(rentRequestDTO, RentRequest.class);
        rentRequest = repositoryRentRequest.save(rentRequest);
        rentRequestDTO.setIdRentRequest(rentRequest.getIdRentRequest());
        return rentRequestDTO;
    }

    public RentRequestDTO update(RentRequestDTO rentRequestDTO){
        rentRequestDTO = get(rentRequestDTO.getIdRentRequest());
        if (rentRequestDTO == null){
            throw new IllegalArgumentException("Unidentified registry");
        }
        RentRequest rentRequest = modelMapper.map(rentRequestDTO, RentRequest.class);
        rentRequest = repositoryRentRequest.save(rentRequest);
        rentRequestDTO = modelMapper.map(rentRequest, RentRequestDTO.class);
        return rentRequestDTO;
    }

    public void delete(Long id){
        repositoryRentRequest.deleteById(id);
    }
}