package web.rent.tufinca.mappers;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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

@Configuration
public class ModelMapperConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        
        modelMapper.typeMap(User.class, UserDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getProperties() != null ? src.getProperties().stream().map(Property::getIdProperty).collect(Collectors.toList()) : null, UserDTO::setPropertyIds);
            mapper.map(src -> src.getReservations() != null ? src.getReservations().stream().map(Rent::getIdRent).collect(Collectors.toList()) : null, UserDTO::setReservationIds);
            mapper.map(src -> src.getRents() != null ? src.getRents().stream().map(Rent::getIdRent).collect(Collectors.toList()) : null, UserDTO::setRentIds);
            mapper.map(src -> src.getRentRequests() != null ? src.getRentRequests().stream().map(RentRequest::getIdRentRequest).collect(Collectors.toList()) : null, UserDTO::setRentRequestIds);
            mapper.map(src -> src.getReservationRequests() != null ? src.getReservationRequests().stream().map(RentRequest::getIdRentRequest).collect(Collectors.toList()) : null, UserDTO::setReservationRequestIds);
        });
        
        // Mapping for Property to PropertyDTO
        modelMapper.typeMap(Property.class, PropertyDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getUser() == null ? null : src.getUser().getIdUser(), PropertyDTO::setOwnerId);
            mapper.map(src -> src.getRents() == null ? null : src.getRents().stream().map(Rent::getIdRent).collect(Collectors.toList()), PropertyDTO::setRentIds);
            mapper.map(src -> src.getRentRequests() == null ? null : src.getRentRequests().stream().map(RentRequest::getIdRentRequest).collect(Collectors.toList()), PropertyDTO::setRentRequestIds);
            mapper.map(src -> src.getPhotos() == null ? null : src.getPhotos().stream().map(Photo::getIdPhoto).collect(Collectors.toList()), PropertyDTO::setPhotoIds);
        });


        // Mapping for Rent to RentDTO
        modelMapper.typeMap(Rent.class, RentDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getOwner() == null ? null : src.getOwner().getIdUser(), RentDTO::setOwnerId);
            mapper.map(src -> src.getRenter() == null ? null : src.getRenter().getIdUser(), RentDTO::setRenterId);
            mapper.map(src -> src.getProperty() == null ? null : src.getProperty().getIdProperty(), RentDTO::setPropertyId);
        });

        // Configuración para mapear el ID del propietario, inquilino y propiedad en RentRequest a RentRequestDTO
        modelMapper.typeMap(RentRequest.class, RentRequestDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getOwner() == null ? null : src.getOwner().getIdUser(), RentRequestDTO::setOwnerId);
            mapper.map(src -> src.getRenter() == null ? null : src.getRenter().getIdUser(), RentRequestDTO::setRenterId);
            mapper.map(src -> src.getProperty() == null ? null : src.getProperty().getIdProperty(), RentRequestDTO::setPropertyId);
        });
        
        // Configuración para mapear el ID de la propiedad en Photo a PhotoDTO
        modelMapper.typeMap(Photo.class, PhotoDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getProperty() == null ? null : src.getProperty().getIdProperty(), PhotoDTO::setPropertyId);
        });


        //DTO  A ENTIDAD


        // Mapping for UserDTO to User
        modelMapper.typeMap(UserDTO.class, User.class).addMappings(mapper -> {
            mapper.skip(User::setProperties);
            mapper.skip(User::setReservations);
            mapper.skip(User::setRents);
            mapper.skip(User::setRentRequests);
            mapper.skip(User::setReservationRequests);
        });

        // Mapping for PropertyDTO to Property
        modelMapper.typeMap(PropertyDTO.class, Property.class).addMappings(mapper -> {
            mapper.skip(Property::setUser);
            mapper.skip(Property::setRents);
            mapper.skip(Property::setRentRequests);
            mapper.skip(Property::setPhotos);
        });

        // Mapping for RentDTO to Rent
        modelMapper.typeMap(RentDTO.class, Rent.class).addMappings(mapper -> {
            mapper.skip(Rent::setOwner);
            mapper.skip(Rent::setRenter);
            mapper.skip(Rent::setProperty);
        });

        // Mapping for RentRequestDTO to RentRequest
        modelMapper.typeMap(RentRequestDTO.class, RentRequest.class).addMappings(mapper -> {
            mapper.skip(RentRequest::setOwner);
            mapper.skip(RentRequest::setRenter);
            mapper.skip(RentRequest::setProperty);
        });

        // Mapping for PhotoDTO to Photo
        modelMapper.typeMap(PhotoDTO.class, Photo.class).addMappings(mapper -> {
            mapper.skip(Photo::setProperty);
        });
        
        return modelMapper;
    }
}