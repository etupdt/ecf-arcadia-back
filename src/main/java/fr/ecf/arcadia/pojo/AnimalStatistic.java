package fr.ecf.arcadia.pojo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class AnimalStatistic {

    @Id
    private String firstname;

    private LocalDate date;
  
    private Long qty;
  
    public AnimalStatistic() {}
  
    public AnimalStatistic(String firstname, LocalDate date, Long qty) {

        this.firstname = firstname;
        this.date = date;
        this.qty = qty;

    }

    @Override
    public String toString() {

        return String.format(
            "Stat[name='%s']",
            firstname
        );
    
    }

}
