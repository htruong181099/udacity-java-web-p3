package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.model.entity.Pet;
import com.udacity.jdnd.course3.critter.schedule.model.dtos.ScheduleDTO;
import com.udacity.jdnd.course3.critter.schedule.model.entity.Schedule;
import com.udacity.jdnd.course3.critter.user.model.dtos.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.model.dtos.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.model.entity.Customer;
import com.udacity.jdnd.course3.critter.user.model.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleModelMapper {
    private final ModelMapper modelMapper;

    public ScheduleModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ScheduleDTO toScheduleDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = modelMapper.map(schedule, ScheduleDTO.class);
        scheduleDTO.setPetIds(schedule.getPetList().stream().map(Pet::getId).collect(Collectors.toList()));
        scheduleDTO.setEmployeeIds(schedule.getEmployeeList().stream().map(Employee::getId).collect(Collectors.toList()));
        return scheduleDTO;
    }

    public Schedule toSchedule(ScheduleDTO scheduleDTO){
        return modelMapper.map(scheduleDTO, Schedule.class);
    }

    public List<ScheduleDTO>toListScheduleDTO (List<Schedule> scheduleList){
        return scheduleList.stream()
                .map(this::toScheduleDTO)
                .collect(Collectors.toList());
    }

}
