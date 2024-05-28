package web.rent.tufinca.controllers;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
import web.rent.tufinca.utils.HTTPResponse;

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
    public List<RentRequestDTO> getWhereIsOwner(Authentication auth) {
        return rentRequestService.getRentRequestsByOwnerId(HTTPResponse.getUserIDFromAuth(auth));
    }

    @CrossOrigin
    @GetMapping(value = "/requested", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RentRequestDTO> getWhereIsRenter(Authentication auth) {
        return rentRequestService.getRentRequestsByRenterId(HTTPResponse.getUserIDFromAuth(auth));
    }

    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RentRequestDTO get(@PathVariable Long id, Authentication auth) {
        return rentRequestService.get(id);
    }
    
    @CrossOrigin
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public RentRequestDTO save(@RequestBody RentRequestDTO rentRequestDTO, Authentication auth) {
        return rentRequestService.save(rentRequestDTO);
    }

    @CrossOrigin
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RentRequestDTO update(@RequestBody RentRequestDTO rentRequestDTO, @PathVariable Long id, Authentication auth) throws IllegalArgumentException {
        return rentRequestService.update(rentRequestDTO, id);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id, Authentication auth) {
        rentRequestService.delete(id);
    }
}