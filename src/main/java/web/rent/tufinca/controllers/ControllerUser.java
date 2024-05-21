package web.rent.tufinca.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import web.rent.tufinca.dtos.UserDTO;
import web.rent.tufinca.entities.User;
import web.rent.tufinca.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import web.rent.tufinca.utils.HTTPResponse;

@RestController
@RequestMapping("/grupo23/users")
public class ControllerUser {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> get( Authentication authentication ) {
        return userService.get();
    }
    
    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO get(@PathVariable Long id, Authentication authentication) {
        System.out.println("authentication details\n\n");
        Long userId = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            userId = mapper.readValue(authentication.getPrincipal().toString(), UserDTO.class).getIdUser();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            userId = -1L;
        }
        // userId contiene el ID del usuario autenticado
        System.out.println(userId);
        return userService.get(id);
    }

    @CrossOrigin
    @GetMapping(value = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HTTPResponse> getByEmail(@PathVariable String email) {
        UserDTO user = userService.getByEmail(email);
        ResponseEntity<HTTPResponse> response;
        if (user == null) {
            response = HTTPResponse.build(
                    "Alguna de tu informacion es incorrecta",
                    null,
                    null,
                    HttpStatus.BAD_REQUEST
            );
        } else {
            response = HTTPResponse.build(
                    null,
                    "El usuario ha sido recuperado",
                    user,
                    HttpStatus.OK
            );
        }

        return response;
    }
    
    @CrossOrigin
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO save(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public UserDTO update(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        return userService.update(userDTO, id);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}