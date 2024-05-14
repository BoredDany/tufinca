package web.rent.tufinca.controllers;


import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.rent.tufinca.dtos.TokenDTO;
import web.rent.tufinca.dtos.UserDTO;
import web.rent.tufinca.services.AuthService;
import web.rent.tufinca.utils.HTTPResponse;
import web.rent.tufinca.utils.schemas.LoginSchema;

import java.awt.*;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/grupo23/auth")
public class ControllerAuth {

    @Autowired
    private AuthService authService;

    @CrossOrigin
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<HTTPResponse> login(@RequestHeader Map<String, String> headers) {

        String basicAuthHeader = headers.get("authorization");

        String res = authService.generateToken(basicAuthHeader);
        ResponseEntity<HTTPResponse> response;
        if (!Objects.equals(res, "NOT_FOUND")) {
            // Aqui se debe envíar el JWT o ponerlo en la cookie
            response = HTTPResponse.build(
                    null,
                    "Auth success",
                    new TokenDTO(res),
                    HttpStatus.OK
            );
        } else {
            response = HTTPResponse.build(
                    "Alguna de tu informacion es incorrecta",
                    null,
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return response;
    }

    @CrossOrigin
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<HTTPResponse> register(@RequestBody UserDTO user) {
       UserDTO newUser = authService.register(user);
       ResponseEntity<HTTPResponse> response;
        if (newUser == null) {
            // Uno no puede decir que ya existe la cuenta, puede darse ataques
            response = HTTPResponse.build(
                    "Ups, algo salió mal",
                    null,
                    newUser,
                    HttpStatus.BAD_REQUEST
            );
        } else {
            response = HTTPResponse.build(
                    null,
                    "Usuario creado con exito",
                    newUser,
                    HttpStatus.CREATED
            );
        }
        return response;
    }
}
