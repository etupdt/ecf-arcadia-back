package fr.ecf.arcadia.pojo;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnoreProperties(value = {"food"}, allowSetters = true)
    @OneToMany(mappedBy = "food")
    private Set<FoodAnimal> foodAnimals;

    public Food(String name) {

        this.name = name;

    }

    public Food() {

    }

}
