package me.paultje52.fontysChallenge.guldenSchijf.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "band")
@Getter
@Setter
public class BandModel {
    @Id
    @Column(name = "id")
    private int bandId;

    @Column(name = "name")
    private String name;

    @Column(name = "specialInstruments")
    private boolean hasSpecialInstruments;
}
