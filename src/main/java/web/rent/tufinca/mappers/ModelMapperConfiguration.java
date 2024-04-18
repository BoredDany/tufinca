package web.rent.tufinca.mappers;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import web.rent.tufinca.dtos.PhotoDTO;
import web.rent.tufinca.dtos.PropertyDTO;
import web.rent.tufinca.dtos.RentDTO;
import web.rent.tufinca.dtos.RentRequestDTO;
import web.rent.tufinca.dtos.UserDTO;
import web.rent.tufinca.entities.Photo;
import web.rent.tufinca.entities.Property;
import web.rent.tufinca.entities.Rent;
import web.rent.tufinca.entities.RentRequest;
import web.rent.tufinca.entities.User;
import web.rent.tufinca.repositories.RepositoryPhoto;
import web.rent.tufinca.repositories.RepositoryProperty;
import web.rent.tufinca.repositories.RepositoryRent;
import web.rent.tufinca.repositories.RepositoryRentRequest;
import web.rent.tufinca.repositories.RepositoryUser;

import java.util.List;

@Configuration
public class ModelMapperConfiguration {

    @Autowired
    private RepositoryProperty propertyRepository;

    @Autowired
    private RepositoryRent rentRepository;

    @Autowired
    private RepositoryRentRequest rentRequestRepository;

    @Autowired
    private RepositoryUser userRepository;

    @Autowired
    private RepositoryPhoto photoRepository;
    
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        

        //Mapping for UserDTO to User
        modelMapper.typeMap(UserDTO.class, User.class).addMappings(mapper -> {
            mapper.using(ctx -> {
                List<Long> source = (List<Long>) ctx.getSource();
                return source.stream()
                    .map(id -> propertyRepository.findById(id).orElse(null))
                    .collect(Collectors.toList());
            }).map(UserDTO::getPropertyIds, User::setProperties);
            mapper.using(ctx -> {
                List<Long> source = (List<Long>) ctx.getSource();
                return source.stream()
                    .map(id -> rentRepository.findById(id).orElse(null))
                    .collect(Collectors.toList());
            }).map(UserDTO::getRentIds, User::setRents);
            mapper.using(ctx -> {
                List<Long> source = (List<Long>) ctx.getSource();
                return source.stream()
                    .map(id -> rentRequestRepository.findById(id).orElse(null))
                    .collect(Collectors.toList());
            }).map(UserDTO::getRentRequestIds, User::setRentRequests);
        });

        //Mapping for User to UserDTO
        modelMapper.typeMap(User.class, UserDTO.class).addMappings(mapper -> {
            mapper.using(ctx -> ((List<Property>) ctx.getSource()).stream()
                .map(Property::getIdProperty)
                .collect(Collectors.toList()))
                .map(User::getProperties, UserDTO::setPropertyIds);
            
            mapper.using(ctx -> ((List<Rent>) ctx.getSource()).stream()
                .map(Rent::getIdRent)
                .collect(Collectors.toList()))
                .map(User::getRents, UserDTO::setRentIds);

            mapper.using(ctx -> ((List<Rent>) ctx.getSource()).stream()
                .map(Rent::getIdRent)
                .collect(Collectors.toList()))
                .map(User::getReservations, UserDTO::setReservationIds);
            
            mapper.using(ctx -> ((List<RentRequest>) ctx.getSource()).stream()
                .map(RentRequest::getIdRentRequest)
                .collect(Collectors.toList()))
                .map(User::getRentRequests, UserDTO::setRentRequestIds);

            mapper.using(ctx -> ((List<RentRequest>) ctx.getSource()).stream()
                .map(RentRequest::getIdRentRequest)
                .collect(Collectors.toList()))
                .map(User::getReservationRequests, UserDTO::setReservationRequestIds);
        });

        //Mapping for PropertyDTO to Property
        modelMapper.typeMap(PropertyDTO.class, Property.class).addMappings(mapper -> {
            mapper.using(ctx -> userRepository.findById((Long) ctx.getSource()).orElse(null))
                .map(PropertyDTO::getOwnerId, Property::setUser);
            mapper.using(ctx -> {
                List<Long> source = (List<Long>) ctx.getSource();
                return source.stream()
                    .map(id -> rentRepository.findById(id).orElse(null))
                    .collect(Collectors.toList());
            }).map(PropertyDTO::getRentIds, Property::setRents);
            mapper.using(ctx -> {
                List<Long> source = (List<Long>) ctx.getSource();
                return source.stream()
                    .map(id -> rentRequestRepository.findById(id).orElse(null))
                    .collect(Collectors.toList());
            }).map(PropertyDTO::getRentRequestIds, Property::setRentRequests);
            mapper.using(ctx -> {
                List<Long> source = (List<Long>) ctx.getSource();
                return source.stream()
                    .map(id -> photoRepository.findById(id).orElse(null))
                    .collect(Collectors.toList());
            }).map(PropertyDTO::getPhotoIds, Property::setPhotos);
        });

        //Mapping for Property to PropertyDTO
        modelMapper.typeMap(Property.class, PropertyDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getUser().getIdUser(), PropertyDTO::setOwnerId);
            mapper.using(ctx -> ((List<Rent>) ctx.getSource()).stream()
                .map(Rent::getIdRent)
                .collect(Collectors.toList()))
                .map(Property::getRents, PropertyDTO::setRentIds);
            mapper.using(ctx -> ((List<RentRequest>) ctx.getSource()).stream()
                .map(RentRequest::getIdRentRequest)
                .collect(Collectors.toList()))
                .map(Property::getRentRequests, PropertyDTO::setRentRequestIds);
            mapper.using(ctx -> ((List<Photo>) ctx.getSource()).stream()
                .map(Photo::getIdPhoto)
                .collect(Collectors.toList()))
                .map(Property::getPhotos, PropertyDTO::setPhotoIds);
        });

        //Mapping for RentRequestDTO to RentRequest
        modelMapper.typeMap(RentRequestDTO.class, RentRequest.class).addMappings(mapper -> {
            mapper.using(ctx -> userRepository.findById((Long) ctx.getSource()).orElse(null))
                .map(RentRequestDTO::getOwnerId, RentRequest::setOwner);
            mapper.using(ctx -> userRepository.findById((Long) ctx.getSource()).orElse(null))
                .map(RentRequestDTO::getRenterId, RentRequest::setRenter);
            mapper.using(ctx -> propertyRepository.findById((Long) ctx.getSource()).orElse(null))
                .map(RentRequestDTO::getPropertyId, RentRequest::setProperty);
        });

        //Mapping for RentRequest to RentRequestDTO
        modelMapper.typeMap(RentRequest.class, RentRequestDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getOwner().getIdUser(), RentRequestDTO::setOwnerId);
            mapper.map(src -> src.getRenter().getIdUser(), RentRequestDTO::setRenterId);
            mapper.map(src -> src.getProperty().getIdProperty(), RentRequestDTO::setPropertyId);
        });

        //Mapping for RentDTO to Rent
        modelMapper.typeMap(RentDTO.class, Rent.class).addMappings(mapper -> {
            mapper.using(ctx -> userRepository.findById((Long) ctx.getSource()).orElse(null))
                .map(RentDTO::getOwnerId, Rent::setOwner);
            mapper.using(ctx -> userRepository.findById((Long) ctx.getSource()).orElse(null))
                .map(RentDTO::getRenterId, Rent::setRenter);
            mapper.using(ctx -> propertyRepository.findById((Long) ctx.getSource()).orElse(null))
                .map(RentDTO::getPropertyId, Rent::setProperty);
        });

        //Mapping for Rent to RentDTO
        modelMapper.typeMap(Rent.class, RentDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getOwner().getIdUser(), RentDTO::setOwnerId);
            mapper.map(src -> src.getRenter().getIdUser(), RentDTO::setRenterId);
            mapper.map(src -> src.getProperty().getIdProperty(), RentDTO::setPropertyId);
        });

        //Mapping for PhotoDTO to Photo
        modelMapper.typeMap(PhotoDTO.class, Photo.class).addMappings(mapper -> {
            mapper.using(ctx -> propertyRepository.findById((Long) ctx.getSource()).orElse(null))
                .map(PhotoDTO::getPropertyId, Photo::setProperty);
        });

        //Mapping for Photo to PhotoDTO
        modelMapper.typeMap(Photo.class, PhotoDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getProperty().getIdProperty(), PhotoDTO::setPropertyId);
        });

        return modelMapper;
    }
}