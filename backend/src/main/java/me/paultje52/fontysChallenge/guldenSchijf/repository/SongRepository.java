package me.paultje52.fontysChallenge.guldenSchijf.repository;
import me.paultje52.fontysChallenge.guldenSchijf.model.SongModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SongRepository extends CrudRepository<SongModel, Integer> {

    @Query(value = "SELECT song.id, title, year, duration, filePath, bandId, managerId, supplierId FROM song INNER JOIN band ON song.bandId = band.id INNER JOIN manager ON song.managerId = manager.id INNER JOIN supplier ON song.supplierId = supplier.id WHERE (:title is null or song.title LIKE %:title%) AND (:band is null or band.name LIKE %:band%) AND (:manager is null or manager.name LIKE %:manager%) AND (:supplier is null or supplier.name LIKE %:supplier%)",nativeQuery = true)
    Iterable<SongModel> getAllSongsByFilter(@Param("title") String title, @Param("band") String band, @Param("manager") String manager, @Param("supplier") String supplier);}
