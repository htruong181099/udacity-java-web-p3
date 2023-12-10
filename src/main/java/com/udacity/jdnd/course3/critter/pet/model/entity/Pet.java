package com.udacity.jdnd.course3.critter.pet.model.entity;

import com.udacity.jdnd.course3.critter.pet.model.PetType;
import com.udacity.jdnd.course3.critter.user.model.entity.Customer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pets")
public class Pet {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PetType type;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name= "birth_date")
    private LocalDate birthDate;
    @Column(name="notes")
    private String notes;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Customer owner;

    public Pet() {
    }

    public Pet(Long id, PetType type, String name, LocalDate birthDate, String notes, Customer owner) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.birthDate = birthDate;
        this.notes = notes;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }
}
