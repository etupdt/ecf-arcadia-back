package fr.ecf.arcadia.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class View {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pseudo;

    private String comment;

    @Column(columnDefinition = "boolean default false")
    private Boolean isVisible;

    public View(String pseudo, String comment, Boolean isVisible) {

        this.pseudo = pseudo;
        this.comment = comment;
        this.isVisible = isVisible;

    }

    public View() {
    }

}
