package br.com.zoo.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Food {
    @Id
    @GeneratedValue
    private int id;

    private LocalDateTime dateTime;

    @Column(length = 50,nullable = false)
    private String typeFood;


    @ManyToOne
    private Animal animal;

    @ManyToOne
    private Worker worker;

    public Food(int id, LocalDateTime dateTime, String typeFood, Animal animal, Worker worker) {
        this.id = id;
        this.dateTime = dateTime;
        this.typeFood = typeFood;
        this.animal = animal;
        this.worker = worker;
    }

    public Food() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getTypeFood() {
        return typeFood;
    }

    public void setTypeFood(String typeFood) {
        this.typeFood = typeFood;
    }
}

