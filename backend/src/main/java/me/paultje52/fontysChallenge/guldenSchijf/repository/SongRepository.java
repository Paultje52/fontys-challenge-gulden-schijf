package me.paultje52.fontysChallenge.guldenSchijf.repository;

import me.paultje52.fontysChallenge.guldenSchijf.model.SongModel;
import me.paultje52.fontysChallenge.guldenSchijf.model.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SongRepository extends CrudRepository<SongModel, Integer> {

    @Query(value = "SELECT * FROM song INNER JOIN band on song.bandId = band.id inner join manager on song.managerId = manager.id inner join supplier on song.supplierId = supplier.id", nativeQuery = true)
    Iterable<SongModel> getAllSongs();
}
