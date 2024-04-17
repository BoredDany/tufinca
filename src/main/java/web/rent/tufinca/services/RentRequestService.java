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

    //GET
    public List<RentRequestDTO> get(){
        List<RentRequest> rentRequests = (List<RentRequest>) repositoryRentRequest.findAll();
        return rentRequests.stream()
        .map(rentRequest -> modelMapper.map(rentRequest, RentRequestDTO.class))
        .collect(Collectors.toList());
    }

    //GET BY ID
    public RentRequestDTO get(Long id){
        Optional<RentRequest> rentRequestOptional = repositoryRentRequest.findById(id);
        RentRequestDTO rentRequestDTO = null;
        if (rentRequestOptional.isPresent()){
            rentRequestDTO = modelMapper.map(rentRequestOptional.get(), RentRequestDTO.class);
        }
        return rentRequestDTO;
    }

    //POST
    public RentRequestDTO save(RentRequestDTO rentRequestDTO){
        RentRequest rentRequest = modelMapper.map(rentRequestDTO, RentRequest.class);
        rentRequest = repositoryRentRequest.save(rentRequest);
        rentRequestDTO.setIdRentRequest(rentRequest.getIdRentRequest());
        return rentRequestDTO;
    }

    //PUT
    public RentRequestDTO update(RentRequestDTO rentRequestDTO, Long id) {
        Optional<RentRequest> optionalRentRequest = repositoryRentRequest.findById(id);
    
        if (optionalRentRequest.isPresent()) {
            RentRequest rentRequest = optionalRentRequest.get();
    
            rentRequest.setDateStart(rentRequestDTO.getDateStart());
            rentRequest.setDateEnd(rentRequestDTO.getDateEnd());
            rentRequest.setNumPeople(rentRequestDTO.getNumPeople());
            rentRequest.setPrice(rentRequestDTO.getPrice());
            rentRequest.setApproval(rentRequestDTO.getApproval());
            rentRequest.setStatus(rentRequestDTO.getStatus());

            rentRequest = repositoryRentRequest.save(rentRequest);
            rentRequestDTO = modelMapper.map(rentRequest, RentRequestDTO.class);
            return rentRequestDTO;
        }
        return null;
    }

    //DELETE
    public void delete(Long id){
        repositoryRentRequest.deleteById(id);
    }
}