package com.crud.api.models;

import jakarta.persistence.*;

@Entity
@Table(name="animal")
public class AnimalsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_animal;

    @Column
    private String name_animal;

    @Column
    private String family_animal;

    @Column
    private Long number_animal;

    @Column
    private String picture_animal;

    public Long getId_animal() {
        return id_animal;
    }

    public void setId_animal(Long id_animal) {
        this.id_animal = id_animal;
    }

    public String getName_animal() {
        return name_animal;
    }

    public void setName_animal(String name_animal) {
        this.name_animal = name_animal;
    }

    public String getFamily_animal() {
        return family_animal;
    }

    public void setFamily_animal(String family_animal) {
        this.family_animal = family_animal;
    }

    public Long getNumber_animal() {
        return number_animal;
    }

    public void setNumber_animal(Long number_animal) {
        this.number_animal = number_animal;
    }

    public String getPicture_animal() {
        return picture_animal;
    }

    public void setPicture_animal(String picture_animal) {
        this.picture_animal = picture_animal;
    }
}
