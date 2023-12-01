package me.paultje52.fontysChallenge.guldenSchijf.repository;

import jdk.jfr.Name;
import me.paultje52.fontysChallenge.guldenSchijf.model.SongModel;
import me.paultje52.fontysChallenge.guldenSchijf.model.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SongRepository extends CrudRepository<SongModel, Integer> {

    @Query(value = "SELECT song.id,title,year,duration, filePath, bandId, managerId, supplierId FROM song inner join band on song.bandId = band.id inner join manager on song.managerId = manager.id inner join supplier on song.supplierId = supplier.id where title = %?1% AND band.name = %?2%", nativeQuery = true)
    Iterable<SongModel> getAllSongsByFilterd(@Param("title") String title, String band,String manager,String supplier);
}
