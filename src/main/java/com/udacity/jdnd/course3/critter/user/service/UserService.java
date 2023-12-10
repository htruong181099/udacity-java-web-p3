package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.user.model.dtos.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.model.dtos.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.model.dtos.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.model.entity.Customer;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

public interface UserService {
    CustomerDTO insertCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getOwnerByPet(Long petId);

    EmployeeDTO insertEmployee(EmployeeDTO employeeDTO);
    void setAvailability(Long employeeId, Set<DayOfWeek> daysAvailable);
    EmployeeDTO getEmployee(Long id);
    List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO employeeRequestDTO);
}
