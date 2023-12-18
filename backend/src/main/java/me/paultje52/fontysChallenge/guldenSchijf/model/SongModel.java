package me.paultje52.fontysChallenge.guldenSchijf.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Entity
@Table(name = "song")
@Getter
@Setter
public class SongModel {
    @Id
    @Column(name = "id")
    private int songId;

    @Column(name = "title")
    private String songTitle;

    @Column(name = "year")
    private Year songYear;

    @Column(name = "duration")
    private int songDuration;

    @Column(name = "filePath")
    private String songFilePath;

    @Column(name = "bandId")
    private int songBandId;

    @Column(name = "bandName")
    private String songBandName;

    @Column(name = "managerId")
    private int managerId;

    @Column(name = "managerName")
    private String songManagerName;

    @Column(name = "supplierId")
    private int supplierId;

    @Column(name = "supplierName")
    private String songSupplierName;


}