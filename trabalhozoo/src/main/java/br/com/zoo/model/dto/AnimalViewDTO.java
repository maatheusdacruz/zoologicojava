package br.com.zoo.model.dto;

import br.com.zoo.model.EHealth;

import java.time.LocalDate;

public class AnimalViewDTO {
    private int id;
    private String name;
    private String scientificName;
    private String specie;
    private LocalDate arrivalDate;
    private String place;
    private EHealth animalhealth;

    public AnimalViewDTO() {
    }

    public AnimalViewDTO(int id, String name, String scientificName, String specie, LocalDate arrivalDate, String place, EHealth animalhealth) {
        this.id = id;
        this.name = name;
        this.scientificName = scientificName;
        this.specie = specie;
        this.arrivalDate = arrivalDate;
        this.place = place;
        this.animalhealth = animalhealth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAnimalhealth() {
        return animalhealth.name();
    }

    public void setAnimalhealth(String health) {
        this.animalhealth = EHealth.valueOf(health);
    }
}
