package com.udacity.jdnd.course3.critter.user.model.entity;

import com.udacity.jdnd.course3.critter.pet.model.entity.Pet;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "notes")
    private String notes;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = CascadeType.ALL)
    List<Pet> petList ;

    public Customer() {
        this.petList =  new ArrayList<>();
    }

    public Customer(Long id, String name, String notes, String phoneNumber, List<Pet> petList) {
        this.id = id;
        this.name = name;
        this.notes = notes;
        this.phoneNumber = phoneNumber;
        this.petList = petList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Pet> getPetList() {
        return petList;
    }

    public void setPetList(List<Pet> petList) {
        this.petList = petList;
    }
}
