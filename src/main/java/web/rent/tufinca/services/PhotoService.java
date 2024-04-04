package web.rent.tufinca.services;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List; 

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.rent.tufinca.dtos.PhotoDTO;
import web.rent.tufinca.entities.Photo;
import web.rent.tufinca.repositories.RepositoryPhoto;

@Service
public class PhotoService {
    @Autowired
    private RepositoryPhoto repositoryPhoto;

    @Autowired
    private ModelMapper modelMapper;


    public PhotoDTO get(Long id){
        Optional<Photo> photoOptional = repositoryPhoto.findById(id);
        PhotoDTO photoDTO = null;
        if (photoOptional.isPresent()){
            photoDTO = modelMapper.map(photoOptional.get(), PhotoDTO.class);
        }
        return photoDTO;
    }

    public List<PhotoDTO> get(){
        List<Photo> photos = (List<Photo>) repositoryPhoto.findAll();
        return photos.stream()
        .map(photo -> modelMapper.map(photo, PhotoDTO.class))
        .collect(Collectors.toList());
    }

    public PhotoDTO save(PhotoDTO photoDTO){
        Photo photo = modelMapper.map(photoDTO, Photo.class);
        photo = repositoryPhoto.save(photo);
        photoDTO.setIdPhoto(photo.getIdPhoto());
        return photoDTO;
    }

    public PhotoDTO update(PhotoDTO photoDTO){
        photoDTO = get(photoDTO.getIdPhoto());
        if (photoDTO == null){
            throw new IllegalArgumentException("Unidentified registry");
        }
        Photo photo = modelMapper.map(photoDTO, Photo.class);
        photo = repositoryPhoto.save(photo);
        photoDTO = modelMapper.map(photo, PhotoDTO.class);
        return photoDTO;
    }

    public void delete(Long id){
        repositoryPhoto.deleteById(id);
    }
}