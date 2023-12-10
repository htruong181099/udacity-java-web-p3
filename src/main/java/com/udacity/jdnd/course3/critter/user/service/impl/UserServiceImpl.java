package com.udacity.jdnd.course3.critter.user.service.impl;

import com.udacity.jdnd.course3.critter.user.UserModelMapper;
import com.udacity.jdnd.course3.critter.user.model.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.model.dtos.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.model.dtos.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.model.dtos.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.model.entity.Customer;
import com.udacity.jdnd.course3.critter.user.model.entity.Employee;
import com.udacity.jdnd.course3.critter.user.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.service.UserService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final UserModelMapper userModelMapper;

    public UserServiceImpl(CustomerRepository customerRepository, EmployeeRepository employeeRepository, UserModelMapper userModelMapper) {
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.userModelMapper = userModelMapper;
    }

    @Override
    public CustomerDTO insertCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setNotes(customerDTO.getNotes());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customerRepository.save(customer);
        customerDTO.setId(customer.getId());
        return customerDTO;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return userModelMapper.toCustomerDTOList(customerRepository.findAll());
    }

    @Override
    public CustomerDTO getOwnerByPet(Long petId) {Customer c = customerRepository.findByPetList_Id(petId);
        return userModelMapper.toCustomerDTO(customerRepository.findByPetList_Id(petId));
    }

    @Override
    public EmployeeDTO insertEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setDaysAvailable(employeeDTO.getDaysAvailable());
        employee.setSkills(employeeDTO.getSkills());
        employeeRepository.save(employee);
        employeeDTO.setId(employee.getId());
        return employeeDTO;
    }

    @Override
    public void setAvailability(Long employeeId, Set<DayOfWeek> daysAvailable) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new RuntimeException("Employee not found"));
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

    @Override
    public EmployeeDTO getEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        return userModelMapper.toEmployeeDTO(employee);
    }

    @Override
    public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO employeeRequestDTO) {
        List<Employee> employees = employeeRepository
                .findByDaysAvailableContains(employeeRequestDTO.getDate().getDayOfWeek()).stream()
                .filter(employee -> employee.getSkills().containsAll(employeeRequestDTO.getSkills()))
                .collect(Collectors.toList());
        return userModelMapper.toEmployeeDTOList(employees);
    }

    private List<String> asListOfStrings(Set<EmployeeSkill> skills) {
        if (skills == null || skills.isEmpty())
            return Collections.singletonList("");

        return skills.stream()
                .map(EmployeeSkill::name)
                .collect(Collectors.toList());
    }
}
