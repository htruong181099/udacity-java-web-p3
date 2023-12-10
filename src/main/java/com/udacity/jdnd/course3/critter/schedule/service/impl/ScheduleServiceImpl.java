package com.udacity.jdnd.course3.critter.schedule.service.impl;

import com.udacity.jdnd.course3.critter.pet.repository.PetRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleModelMapper;
import com.udacity.jdnd.course3.critter.schedule.model.dtos.ScheduleDTO;
import com.udacity.jdnd.course3.critter.schedule.model.entity.Schedule;
import com.udacity.jdnd.course3.critter.schedule.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.service.ScheduleService;
import com.udacity.jdnd.course3.critter.user.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;
    private final PetRepository petRepository;
    private final ScheduleModelMapper modelMapper;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, EmployeeRepository employeeRepository, PetRepository petRepository, ScheduleModelMapper modelMapper) {
        this.scheduleRepository = scheduleRepository;
        this.employeeRepository = employeeRepository;
        this.petRepository = petRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public ScheduleDTO insertSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setActivities(scheduleDTO.getActivities());
        schedule.setEmployeeList(employeeRepository.findByIdIn(scheduleDTO.getEmployeeIds()));
        schedule.setPetList(petRepository.findByIdIn(scheduleDTO.getPetIds()));
        scheduleRepository.save(schedule);
        scheduleDTO.setId(schedule.getId());
        return scheduleDTO;
    }

    @Override
    public List<ScheduleDTO> getPetSchedule(Long petId) {
        return modelMapper.toListScheduleDTO(scheduleRepository.findByPetListId(petId));
    }

    @Override
    public List<ScheduleDTO> getEmployeeSchedule(Long employeeId) {
        return modelMapper.toListScheduleDTO(scheduleRepository.findByEmployeeList_Id(employeeId));
    }

    @Override
    public List<ScheduleDTO> getCustomerSchedule(Long customerId) {
        return modelMapper.toListScheduleDTO(scheduleRepository.findByPetList_OwnerId(customerId));
    }

    @Override
    public List<ScheduleDTO> getAllSchedules() {
        return modelMapper.toListScheduleDTO(scheduleRepository.findAll());
    }
}
