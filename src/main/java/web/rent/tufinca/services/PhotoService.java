package web.rent.tufinca.services;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List; 

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.rent.tufinca.dtos.PhotoDTO;
import web.rent.tufinca.dtos.PropertyDTO;
import web.rent.tufinca.entities.Photo;
import web.rent.tufinca.entities.Property;
import web.rent.tufinca.entities.User;
import web.rent.tufinca.repositories.RepositoryPhoto;
import web.rent.tufinca.repositories.RepositoryProperty;

@Service
public class PhotoService {
    @Autowired
    private RepositoryPhoto repositoryPhoto;

    @Autowired 
    private RepositoryProperty propertyRepository;

    @Autowired
    private ModelMapper modelMapper;

    //GET
    public List<PhotoDTO> get(){
        List<Photo> photos = (List<Photo>) repositoryPhoto.findAll();
        return photos.stream()
        .map(photo -> modelMapper.map(photo, PhotoDTO.class))
        .collect(Collectors.toList());
    }

    //GET BY ID
    public PhotoDTO get(Long id){
        Optional<Photo> photoOptional = repositoryPhoto.findById(id);
        PhotoDTO photoDTO = null;
        if (photoOptional.isPresent()){
            photoDTO = modelMapper.map(photoOptional.get(), PhotoDTO.class);
        }
        return photoDTO;
    }

    //POST

    public PhotoDTO save(PhotoDTO photoDTO){
        Photo photo = modelMapper.map(photoDTO, Photo.class);
        Optional<Property> propertyOptional = propertyRepository.findById(photoDTO.getPropertyId());
    
        photo.setProperty(propertyOptional.get());
        photo = repositoryPhoto.save(photo);
        photoDTO.setIdPhoto(photo.getIdPhoto());
        return photoDTO;
    }

    //PUT
    public PhotoDTO update(PhotoDTO photoDTO, Long id) {
        Optional<Photo> optionalPhoto = repositoryPhoto.findById(id);
    
        if (optionalPhoto.isPresent()) {
            Photo photo = optionalPhoto.get();

            photo.setUrl(photoDTO.getUrl());
            photo.setDescription(photoDTO.getDescription());
            photo.setStatus(photoDTO.getStatus());
            
            photo = repositoryPhoto.save(photo);
            photoDTO = modelMapper.map(photo, PhotoDTO.class);
            return photoDTO;
        }
        return null;
    }

    //DELETE
    public void delete(Long id){
        repositoryPhoto.deleteById(id);
    }
}