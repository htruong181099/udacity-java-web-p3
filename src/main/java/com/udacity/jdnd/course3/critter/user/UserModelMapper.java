package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.model.entity.Pet;
import com.udacity.jdnd.course3.critter.user.model.dtos.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.model.dtos.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.model.entity.Customer;
import com.udacity.jdnd.course3.critter.user.model.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserModelMapper {
    private final ModelMapper modelMapper;

    public UserModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CustomerDTO toCustomerDTO(Customer customer){
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        customerDTO.setPetIds(customer.getPetList().stream().map(Pet::getId).collect(Collectors.toList()));
        return customerDTO;
    }

    public Customer toCustomerEntity(CustomerDTO customerDTO){
        return modelMapper.map(customerDTO, Customer.class);
    }

    public List<CustomerDTO> toCustomerDTOList(List<Customer> customerList){
        return customerList.stream()
                .map(this::toCustomerDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO toEmployeeDTO(Employee employee){
//        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setDaysAvailable(employee.getDaysAvailable());
        employeeDTO.setSkills(employee.getSkills());
        return employeeDTO;
    }

    public List<EmployeeDTO> toEmployeeDTOList(List<Employee> employeeList){
        return employeeList.stream()
                .map(this::toEmployeeDTO)
                .collect(Collectors.toList());
    }
}
