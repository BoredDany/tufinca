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
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        
        // Mapping for User to UserDTO
        modelMapper.typeMap(User.class, UserDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getProperties().stream().map(Property::getIdProperty).collect(Collectors.toList()), UserDTO::setPropertyIds);
            mapper.map(src -> src.getReservations().stream().map(Rent::getIdRent).collect(Collectors.toList()), UserDTO::setReservationIds);
            mapper.map(src -> src.getRents().stream().map(Rent::getIdRent).collect(Collectors.toList()), UserDTO::setRentIds);
            mapper.map(src -> src.getRentRequests().stream().map(RentRequest::getIdRentRequest).collect(Collectors.toList()), UserDTO::setRentRequestIds);
            mapper.map(src -> src.getReservationRequests().stream().map(RentRequest::getIdRentRequest).collect(Collectors.toList()), UserDTO::setReservationRequestIds);
        });
        
        // Mapping for Property to PropertyDTO
        modelMapper.typeMap(Property.class, PropertyDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getUser().getIdUser(), PropertyDTO::setOwnerId);
            mapper.map(src -> src.getRents().stream().map(Rent::getIdRent).collect(Collectors.toList()), PropertyDTO::setRentIds);
            mapper.map(src -> src.getRentRequests().stream().map(RentRequest::getIdRentRequest).collect(Collectors.toList()), PropertyDTO::setRentRequestIds);
            mapper.map(src -> src.getPhotos().stream().map(Photo::getIdPhoto).collect(Collectors.toList()), PropertyDTO::setPhotoIds);
        });


        // Mapping for Rent to RentDTO
        modelMapper.typeMap(Rent.class, RentDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getOwner(), RentDTO::setOwnerId);
            mapper.map(src -> src.getRenter(), RentDTO::setRenterId);
            mapper.map(src -> src.getProperty(), RentDTO::setPropertyId);
        });

        //
        
        // Configuración para mapear el ID del propietario, inquilino y propiedad en RentRequest a RentRequestDTO
        modelMapper.typeMap(RentRequest.class, RentRequestDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getOwner().getIdUser(), RentRequestDTO::setOwnerId);
            mapper.map(src -> src.getRenter().getIdUser(), RentRequestDTO::setRenterId);
            mapper.map(src -> src.getProperty().getIdProperty(), RentRequestDTO::setPropertyId);
        });
        
        // Configuración para mapear el ID de la propiedad en Photo a PhotoDTO
        modelMapper.typeMap(Photo.class, PhotoDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getProperty().getIdProperty(), PhotoDTO::setPropertyId);
        });
        
        return modelMapper;
    }
}
