package fr.ecf.arcadia.pojo;

import java.time.LocalDate;

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

    @ManyToOne
    @JoinColumn(name = "id_food")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "id_animal")
    private Animal animal;

    public FoodAnimal(int gramage, LocalDate dateFood) {

        this.gramage = gramage;
        this.dateFood = dateFood;

    }

    public FoodAnimal() {

    }

}
