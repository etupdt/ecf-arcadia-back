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
public class VeterinaryReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String detail;

    private int gramage;

    @ManyToOne
    @JoinColumn(name = "animalId")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "foodId")
    private Food food;

    public VeterinaryReport(LocalDate date, String detail) {

        this.date = date;
        this.detail = detail;

    }

    public VeterinaryReport() {
    }

}
