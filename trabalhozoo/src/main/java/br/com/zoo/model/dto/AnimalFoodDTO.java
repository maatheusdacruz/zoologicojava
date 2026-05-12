package br.com.zoo.model.dto;

import br.com.zoo.model.Animal;

public class AnimalFoodDTO {
    private Animal animal;
    private boolean wasFed;

    public AnimalFoodDTO(Animal animal, boolean wasFed) {
        this.animal = animal;
        this.wasFed = wasFed;
    }
    public AnimalFoodDTO() {
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public boolean isWasFed() {
        return wasFed;
    }

    public void setWasFed(boolean wasFed) {
        this.wasFed = wasFed;
    }
}
