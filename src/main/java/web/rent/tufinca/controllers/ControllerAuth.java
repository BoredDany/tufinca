package web.rent.tufinca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.Cookie;
import web.rent.tufinca.dtos.TokenDTO;
import web.rent.tufinca.dtos.UserDTO;
import web.rent.tufinca.services.AuthService;
import web.rent.tufinca.services.TokenService;
import web.rent.tufinca.services.UserService;
import web.rent.tufinca.utils.HTTPResponse;
import web.rent.tufinca.utils.schemas.LoginSchema;
import org.springframework.security.core.Authentication;

import java.awt.*;

@RestController
@RequestMapping("/grupo23/auth")
public class ControllerAuth {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService usuarioService;

    @Autowired
    private TokenService jwtTokenService;

    @CrossOrigin
    @PostMapping(  value = "/autenticar", produces = MediaType.APPLICATION_JSON_VALUE)
    public TokenDTO autenticar( @RequestBody UserDTO usuarioDTO ){
        return new TokenDTO(jwtTokenService.generarToken(usuarioDTO), usuarioDTO);
    }

    @CrossOrigin
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<HTTPResponse> login(@RequestBody LoginSchema loginForm) {
        int res = authService.login(loginForm);
        ResponseEntity<HTTPResponse> response;
        String errorMessage = null;

        if (res == 0) {
            // Aqui se debe envíar el JWT o ponerlo en la cookie

            Authentication authentication = new UsernamePasswordAuthenticationToken(loginForm.getEmail(),
                    loginForm.getPassword());
            UserDTO usuario = null;

            try {
                usuario = usuarioService.autorizacion(authentication);
            } catch (Exception e) {
                errorMessage = "Error al autorizar al usuario";
            }

            if (usuario != null) {
                String jwt = jwtTokenService.generarToken(usuario);
                response = HTTPResponse.build(
                        null,
                        "Auth success",
                        jwt,
                        HttpStatus.OK);

                Cookie cookie = new Cookie("jwt", jwt); // Aquí se crea la cookie
                response.getHeaders().add("Set-Cookie", cookie.toString()); // Aquí se envía la cookie

            } else {
                response = HTTPResponse.build(
                        errorMessage,
                        null,
                        null,
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            response = HTTPResponse.build(
                    "Alguna de tu informacion es incorrecta",
                    null,
                    null,
                    HttpStatus.BAD_REQUEST);
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
                    HttpStatus.BAD_REQUEST);
        } else {
            response = HTTPResponse.build(
                    null,
                    "Usuario creado con exito",
                    newUser,
                    HttpStatus.CREATED);
        }
        return response;
    }
}
