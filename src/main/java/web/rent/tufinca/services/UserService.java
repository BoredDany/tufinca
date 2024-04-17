package web.rent.tufinca.services;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List; 

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import web.rent.tufinca.dtos.UserDTO;
import web.rent.tufinca.entities.Status;
import web.rent.tufinca.entities.User;
import web.rent.tufinca.repositories.RepositoryUser;


@Service
public class UserService {
    @Autowired
    private RepositoryUser userRepository;

    @Autowired
    private ModelMapper modelMapper;


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

    //POST
    public UserDTO save (UserDTO userDTO){
        User user = modelMapper.map(userDTO, User.class);
        user.setStatus(Status.ACTIVE); 
        user = userRepository.save(user);
        userDTO.setIdUser(user.getIdUser());
        return userDTO;
    }

    //PUT
    public UserDTO update(UserDTO userDTO, Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
    
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
    
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPhone(userDTO.getPhone());
            user.setPhoto(userDTO.getPhoto());
            user.setStatus(userDTO.getStatus());
    
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
