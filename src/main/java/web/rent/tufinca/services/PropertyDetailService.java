package web.rent.tufinca.services;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List; 

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.rent.tufinca.dtos.PropertyDetailDTO;
import web.rent.tufinca.entities.PropertyDetail;
import web.rent.tufinca.repositories.RepositoryPropertyDetail;

@Service
public class PropertyDetailService {
    @Autowired
    private RepositoryPropertyDetail repositoryPropertyDetail;

    @Autowired
    private ModelMapper modelMapper;


    public PropertyDetailDTO get(Long id){
        Optional<PropertyDetail> propertyDetailOptional = repositoryPropertyDetail.findById(id);
        PropertyDetailDTO propertyDetailDTO = null;
        if (propertyDetailOptional.isPresent()){
            propertyDetailDTO = modelMapper.map(propertyDetailOptional.get(), PropertyDetailDTO.class);
        }
        return propertyDetailDTO;
    }

    public List<PropertyDetailDTO> get(){
        List<PropertyDetail> propertyDetails = (List<PropertyDetail>) repositoryPropertyDetail.findAll();
        return propertyDetails.stream()
        .map(propertyDetail -> modelMapper.map(propertyDetail, PropertyDetailDTO.class))
        .collect(Collectors.toList());
    }

    public PropertyDetailDTO save(PropertyDetailDTO propertyDetailDTO){
        PropertyDetail propertyDetail = modelMapper.map(propertyDetailDTO, PropertyDetail.class);
        propertyDetail = repositoryPropertyDetail.save(propertyDetail);
        propertyDetailDTO.setIdPropertyDetail(propertyDetail.getIdPropertyDetail());
        return propertyDetailDTO;
    }

    public PropertyDetailDTO update(PropertyDetailDTO propertyDetailDTO){
        propertyDetailDTO = get(propertyDetailDTO.getIdPropertyDetail());
        if (propertyDetailDTO == null){
            throw new IllegalArgumentException("Unidentified registry");
        }
        PropertyDetail propertyDetail = modelMapper.map(propertyDetailDTO, PropertyDetail.class);
        propertyDetail = repositoryPropertyDetail.save(propertyDetail);
        propertyDetailDTO = modelMapper.map(propertyDetail, PropertyDetailDTO.class);
        return propertyDetailDTO;
    }

    public void delete(Long id){
        repositoryPropertyDetail.deleteById(id);
    }
}