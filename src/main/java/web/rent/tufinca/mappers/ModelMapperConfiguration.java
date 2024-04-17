package web.rent.tufinca.mappers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import web.rent.tufinca.dtos.PhotoDTO;
import web.rent.tufinca.dtos.PropertyDTO;
import web.rent.tufinca.dtos.RentDTO;
import web.rent.tufinca.dtos.RentRequestDTO;
import web.rent.tufinca.entities.Photo;
import web.rent.tufinca.entities.Property;
import web.rent.tufinca.entities.Rent;
import web.rent.tufinca.entities.RentRequest;

@Configuration
public class ModelMapperConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        
        // Configuración para mapear el ID del usuario en lugar del usuario completo
        modelMapper.typeMap(Property.class, PropertyDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getUser().getIdUser(), PropertyDTO::setOwnerId);
        });
        
        // Configuración para mapear el ID del propietario, inquilino y propiedad en lugar de las entidades completas
        modelMapper.typeMap(Rent.class, RentDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getOwner().getIdUser(), RentDTO::setOwnerId);
            mapper.map(src -> src.getRenter().getIdUser(), RentDTO::setRenterId);
            mapper.map(src -> src.getProperty().getIdProperty(), RentDTO::setPropertyId);
        });
        
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
