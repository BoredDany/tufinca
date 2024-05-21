package web.rent.tufinca.controllers;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import web.rent.tufinca.CustomUserDetailsService;
import web.rent.tufinca.dtos.RentDTO;
import web.rent.tufinca.entities.Rent;
import web.rent.tufinca.entities.User;
import web.rent.tufinca.services.AuthService;
import web.rent.tufinca.services.RentService;
import web.rent.tufinca.services.TokenService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/grupo23/rents")
public class ControllerRent {
    @Autowired
    private RentService rentService;

    @Autowired
    private TokenService tokenService;

    /*
     * @CrossOrigin
     * 
     * @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
     * public List<RentDTO> get() {
     * return rentService.get();
     * }
     */

    @CrossOrigin
    @GetMapping("/")
    public List<RentDTO> getUserRentsWhereIsOwner() {
        return rentService.getRentsByOwnerId(tokenService.getId());
    }

    @CrossOrigin
    @GetMapping("/rented")
    public List<RentDTO> getUserRentsWhereIsRenter() {
        return rentService.getRentsByRenterId(tokenService.getId());
    }

    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RentDTO get(@PathVariable Long id) {
        return rentService.get(id);
    }

    @CrossOrigin
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public RentDTO save(@RequestBody RentDTO rentDTO) {
        return rentService.save(rentDTO);
    }

    @CrossOrigin
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RentDTO update(@RequestBody RentDTO rentDTO, @PathVariable Long id) throws IllegalArgumentException {
        return rentService.update(rentDTO, id);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) {
        rentService.delete(id);
    }
}