package com.udacity.jdnd.course3.critter.schedule.service;

import com.udacity.jdnd.course3.critter.schedule.model.dtos.ScheduleDTO;

import java.util.List;

public interface ScheduleService {
    ScheduleDTO insertSchedule(ScheduleDTO scheduleDTO);
    List<ScheduleDTO> getAllSchedules();
    List<ScheduleDTO> getPetSchedule(Long petId);
    List<ScheduleDTO> getEmployeeSchedule(Long employeeId);
    List<ScheduleDTO> getCustomerSchedule(Long customerId);

}
