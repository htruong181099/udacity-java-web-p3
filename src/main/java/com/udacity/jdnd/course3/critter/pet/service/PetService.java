package com.udacity.jdnd.course3.critter.pet.service;

import com.udacity.jdnd.course3.critter.pet.model.dtos.PetDTO;

import java.util.List;

public interface PetService {
    PetDTO insertPet(PetDTO petDTO);
    PetDTO getPet(Long petId);
    List<PetDTO> getAllPets();
    List<PetDTO> getPetsByOwner(Long ownerId);
}
