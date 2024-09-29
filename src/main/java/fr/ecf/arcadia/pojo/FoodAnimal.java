package fr.ecf.arcadia.pojo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FoodAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int gramage;

    private LocalDate dateFood;

    @JsonIgnoreProperties(value = {"foodAnimals"}, allowSetters = true)
    @ManyToOne
    @JoinColumn(name = "foodId")
    private Food food;

    @JsonIgnoreProperties(value = {"foodAnimals"}, allowSetters = true)
    @ManyToOne
    @JoinColumn(name = "animalId")
    private Animal animal;

    public FoodAnimal(int gramage, LocalDate dateFood, Food food, Animal animal) {

        this.gramage = gramage;
        this.dateFood = dateFood;
        this.food = food;
        this.animal = animal;

    }

    public FoodAnimal() {

    }

}
