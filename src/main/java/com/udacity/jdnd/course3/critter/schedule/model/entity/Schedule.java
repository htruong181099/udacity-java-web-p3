package com.udacity.jdnd.course3.critter.schedule.model.entity;

import com.udacity.jdnd.course3.critter.pet.model.entity.Pet;
import com.udacity.jdnd.course3.critter.user.model.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.model.entity.Employee;
import com.udacity.jdnd.course3.critter.utility.EmployeeSkillsConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "schedule_date", nullable = false)
    private LocalDate date;

    @Column(name = "activities")
    @Convert(converter = EmployeeSkillsConverter.class)
    private Set<EmployeeSkill> activities;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinTable(
            name = "schedule_pets",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "pet_id")
    )
    private List<Pet> petList;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinTable(
            name = "schedule_employees",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> employeeList;

    public Schedule() {
    }

    public Schedule(Long id, LocalDate date, Set<EmployeeSkill> activities, List<Pet> petList, List<Employee> employeeList) {
        this.id = id;
        this.date = date;
        this.activities = activities;
        this.petList = petList;
        this.employeeList = employeeList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }

    public List<Pet> getPetList() {
        return petList;
    }

    public void setPetList(List<Pet> petList) {
        this.petList = petList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
