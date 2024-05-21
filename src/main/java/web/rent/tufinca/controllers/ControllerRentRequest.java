package web.rent.tufinca.controllers;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.rent.tufinca.dtos.RentRequestDTO;
import web.rent.tufinca.services.RentRequestService;
import web.rent.tufinca.services.TokenService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/grupo23/rentrequests")
public class ControllerRentRequest {

    @Autowired
    private RentRequestService rentRequestService;

    @Autowired
    private TokenService tokenService;

    /*@CrossOrigin
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RentRequestDTO> get() {
        return rentRequestService.get();
    }*/
    
    @CrossOrigin
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RentRequestDTO> getWhereIsOwner() {
        return rentRequestService.getRentRequestsByOwnerId(tokenService.getId());
    }

    @CrossOrigin
    @GetMapping(value = "/requested", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RentRequestDTO> getWhereIsRenter() {
        return rentRequestService.getRentRequestsByRenterId(tokenService.getId());
    }

    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RentRequestDTO get(@PathVariable Long id) {
        return rentRequestService.get(id);
    }
    
    @CrossOrigin
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public RentRequestDTO save(@RequestBody RentRequestDTO rentRequestDTO) {
        return rentRequestService.save(rentRequestDTO);
    }

    @CrossOrigin
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RentRequestDTO update(@RequestBody RentRequestDTO rentRequestDTO, @PathVariable Long id) throws IllegalArgumentException {
        return rentRequestService.update(rentRequestDTO, id);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) {
        rentRequestService.delete(id);
    }
}