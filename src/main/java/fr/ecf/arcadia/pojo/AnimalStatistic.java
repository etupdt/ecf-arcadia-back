package fr.ecf.arcadia.pojo;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalStatistic {

    private String firstname;

    private LocalDate date;
  
    private Long qty;
  
    public AnimalStatistic() {}
  
    public AnimalStatistic(String firstname) {
        this.firstname = firstname;
    }
  
    @Override
    public String toString() {

        return String.format(
            "Customer[id=%s, name='%s']",
            firstname
        );
    
    }

}
