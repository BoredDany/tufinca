package web.rent.tufinca.services;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.core.Authentication;

import web.rent.tufinca.dtos.UserDTO;
import web.rent.tufinca.entities.Status;
import web.rent.tufinca.entities.User;
import web.rent.tufinca.repositories.RepositoryProperty;
import web.rent.tufinca.repositories.RepositoryRent;
import web.rent.tufinca.repositories.RepositoryRentRequest;
import web.rent.tufinca.repositories.RepositoryUser;


@Service
public class UserService {
    
    @Autowired
    private RepositoryUser userRepository;

    @Autowired
    private RepositoryProperty propertyRepository;

    @Autowired
    private RepositoryRent rentRepository;

    @Autowired
    private RepositoryRentRequest rentRequestRepository;

    @Autowired
    private ModelMapper modelMapper;


    //AUTH
    public UserDTO autorizacion( Authentication authentication ) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("-----------------------");
        System.out.println(  authentication.getName() );
        UserDTO usuario = objectMapper.readValue(authentication.getName(), UserDTO.class);
        System.out.println("-----------------------"); 
        return usuario;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //GET
    public List<UserDTO> get ( ){
        List<User> users = (List<User>) userRepository.findAll(); // Remove unnecessary cast
        return users.stream()
        .map(user -> modelMapper
        .map(user, UserDTO.class))
        .collect(Collectors.toList());
    }

    //GET BY ID
    public UserDTO get (Long id){
        Optional<User> userOptional = userRepository.findById(id);
        UserDTO userDTO = null;

        // Cuando se declare Optional<> entonces hay que validarlo de esta forma
        if (userOptional.isPresent()){
            userDTO = modelMapper.map(userOptional.get(), UserDTO.class);
        }
        return userDTO;
    }

    public UserDTO getByEmail (String email){
        Optional<User> userOptional = userRepository.findByEmail(email);
        UserDTO userDTO = null;
        if (userOptional.isPresent()){
            userDTO = modelMapper.map(userOptional.get(), UserDTO.class);
        }
        return userDTO;
    }

    //POST
    public UserDTO save(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        // Encriptar la constraseña
        String hash = BCrypt
                .withDefaults()
                .hashToString(
                        12,
                        userDTO.getPassword().toCharArray()
                );
        user.setPassword(hash);
        user.setMoney(userDTO.getMoney());
        user.setStatus(Status.ACTIVE);
        user = userRepository.save(user);
        userDTO.setIdUser(user.getIdUser());
        return modelMapper.map(user, UserDTO.class);
    }

    //PUT
    public UserDTO update(UserDTO userDTO, Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
    
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
    
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPhone(userDTO.getPhone());
            user = userRepository.save(user);
            userDTO = modelMapper.map(user, UserDTO.class);
            return userDTO;
        }
        return null;
    }

    //DELETE
    public void delete (Long id){
        userRepository.deleteById(id);
    }


}
