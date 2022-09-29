package org.example.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.CascadeType.PERSIST;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
public class Person { // POJO

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name = null;
    private int age = 0;

    @ManyToOne(cascade = PERSIST)
    private Team myTeam;

    public void setMyTeam(Team aTeam) {
        this.myTeam = aTeam;
        aTeam.addMember(this);
    }
}
