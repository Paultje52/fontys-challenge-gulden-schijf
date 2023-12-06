package me.paultje52.fontysChallenge.guldenSchijf.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "manager")
@Getter
@Setter
public class ManagerModel {
    @Id
    @Column(name = "id")
    private int managerId;

    @Column(name = "hourlyRate")
    private int hourlyRate;

    @Column(name = "name")
    private String name;

    @Column(name = "addressStreet")
    private String adressStreet;

    @Column(name = "addressNumber")
    private int adressNumber;

    @Column(name = "addressZip")
    private String adressZip;

    @Column(name = "addressCity")
    private String adressCity;

    @Column(name = "addressCountry")
    private String adressCountry;
}
