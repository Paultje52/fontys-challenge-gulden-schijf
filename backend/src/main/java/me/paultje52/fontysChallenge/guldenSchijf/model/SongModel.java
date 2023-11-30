package me.paultje52.fontysChallenge.guldenSchijf.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private int songBandID;
    @Column(name = "managerId")
    private int songManagerId;
    @Column(name = "supplierId")
    private int songSupplierId;

}