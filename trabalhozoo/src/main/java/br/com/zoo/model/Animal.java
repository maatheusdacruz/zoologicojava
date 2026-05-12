package br.com.zoo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50,nullable = false)
    private String name;
    @Column(length = 50,nullable = false)
    private String scientificName;
    @Column(length = 50,nullable = false)
    private String specie;

    private LocalDate arrivalDate;
    @Column(length = 20,nullable = false)
    private String place;
    @Enumerated(EnumType.STRING)
    private EHealth animalhealth;

    public Animal(Integer id, String name, String scientificName, String specie, LocalDate arrivalDate, String place, EHealth animalhealth) {
        this.id = id;
        this.name = name;
        this.scientificName = scientificName;
        this.specie = specie;
        this.arrivalDate = arrivalDate;
        this.place = place;
        this.animalhealth = animalhealth;
    }

    public Animal() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getAnimalhealth() {
        return animalhealth.name();
    }

    public void setAnimalhealth(String health) {
        this.animalhealth = EHealth.valueOf(health);
    }
}
