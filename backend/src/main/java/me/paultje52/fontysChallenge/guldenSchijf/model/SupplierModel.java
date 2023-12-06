package me.paultje52.fontysChallenge.guldenSchijf.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "supplier")
@Getter
@Setter
public class SupplierModel {
    @Id
    @Column(name = "id")
    private int supplierId;

    @Column(name = "averageDeliveryTime")
    private int averageDeliveryTime;

    @Column(name = "name")
    private String name;
}
