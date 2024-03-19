package fr.ecf.arcadia.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Hours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String monday;

    private String tuesday;

    private String wednesday;

    private String thursday;

    private String friday;

    private String saturday;

    private String sunday;

    public Hours(String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday) {

        this.monday = monday;
        this.monday = tuesday;
        this.monday = wednesday;
        this.monday = thursday;
        this.monday = friday;
        this.monday = saturday;
        this.monday = sunday;

    }

    public Hours() {
    }

}
