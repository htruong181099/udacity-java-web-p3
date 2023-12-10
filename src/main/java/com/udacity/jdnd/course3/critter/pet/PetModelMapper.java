package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.pet.model.dtos.PetDTO;
import com.udacity.jdnd.course3.critter.pet.model.entity.Pet;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PetModelMapper {
    private final ModelMapper modelMapper;

    public PetModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Pet toPet(PetDTO petDTO){
        return modelMapper.map(petDTO, Pet.class);
    }

    public PetDTO toPetDTO(Pet pet){
        PetDTO petDTO = modelMapper.map(pet, PetDTO.class);
        petDTO.setOwnerId(pet.getOwner().getId());
        return petDTO;
    }

    public List<PetDTO> toPetDTOList(List<Pet> petList){
        return petList.stream().map(this::toPetDTO).collect(Collectors.toList());
    }
}
