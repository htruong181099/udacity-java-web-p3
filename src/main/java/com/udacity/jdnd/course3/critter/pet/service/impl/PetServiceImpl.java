package com.udacity.jdnd.course3.critter.pet.service.impl;

import com.udacity.jdnd.course3.critter.pet.PetModelMapper;
import com.udacity.jdnd.course3.critter.pet.model.dtos.PetDTO;
import com.udacity.jdnd.course3.critter.pet.model.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.repository.PetRepository;
import com.udacity.jdnd.course3.critter.pet.service.PetService;
import com.udacity.jdnd.course3.critter.user.model.entity.Customer;
import com.udacity.jdnd.course3.critter.user.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;
    private final PetModelMapper modelMapper;

    public PetServiceImpl(PetRepository petRepository, CustomerRepository customerRepository, PetModelMapper modelMapper) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public PetDTO insertPet(PetDTO petDTO) {
        PetDTO newPet = new PetDTO(petDTO);
        Customer owner = customerRepository.findById(petDTO.getOwnerId())
                .orElseThrow(()-> new RuntimeException("Owner not found"));
        Pet pet = new Pet();
        pet.setName(petDTO.getName());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setNotes(petDTO.getNotes());
        pet.setType(petDTO.getType());
        pet.setOwner(owner);
        petRepository.save(pet);
        owner.getPetList().add(pet);
        newPet.setId(pet.getId());
        return newPet;
    }

    @Override
    public PetDTO getPet(Long petId) {
        return modelMapper.toPetDTO(petRepository.findById(petId).orElse(null));
    }

    @Override
    public List<PetDTO> getAllPets() {
        return  modelMapper.toPetDTOList(petRepository.findAll());
    }

    @Override
    public List<PetDTO> getPetsByOwner(Long ownerId) {
        return modelMapper.toPetDTOList(petRepository.findByOwner_Id(ownerId));
    }
}
