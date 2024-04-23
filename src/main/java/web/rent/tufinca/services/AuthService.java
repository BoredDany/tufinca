package web.rent.tufinca.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.rent.tufinca.dtos.UserDTO;
import web.rent.tufinca.entities.User;
import web.rent.tufinca.repositories.RepositoryUser;
import web.rent.tufinca.utils.schemas.LoginSchema;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private RepositoryUser repositoryUser;

    @Autowired
    private UserService userService;

    public int login(LoginSchema loginForm) {
        Optional<User> user = repositoryUser.findByEmail(loginForm.getEmail());
        if (user.isEmpty()) return -1;

        // Verificar la constraseña
        if (!BCrypt
                .verifyer()
                .verify(
                        loginForm.getPassword().toCharArray(),
                        user.get().getPassword().toCharArray()
                ).verified) return -1;

        return 0;
    }

    public UserDTO register(UserDTO userDTO) {
        Optional<User> possibleUser = repositoryUser.findByEmail(userDTO.getEmail());
        if (possibleUser.isPresent()) return null;
        return userService.save(userDTO);
    }
}
