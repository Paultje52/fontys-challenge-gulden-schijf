package me.paultje52.fontysChallenge.guldenSchijf.service;

import me.paultje52.fontysChallenge.guldenSchijf.model.SongModel;
import me.paultje52.fontysChallenge.guldenSchijf.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;
    public SongModel[] GetAllSongs(){
        Iterable<SongModel> songs = songRepository.findAll();
        ArrayList<SongModel> songsList = new ArrayList<>();
        songs.forEach(songsList::add);
        return songsList.toArray(new SongModel[0]);
    }
    public SongModel[] GetAllSongsFiltered(String title,String band,String manager,String supplier){
        Iterable<SongModel> songs = songRepository.getAllSongsByFilter(title,band,manager,supplier);
        ArrayList<SongModel> songsList = new ArrayList<>();
        songs.forEach(songsList::add);
        return songsList.toArray(new SongModel[0]);
    }

    public ResponseEntity<InputStreamResource> Mp3(int id) throws IOException {
        Resource resource = new ClassPathResource("song/"+ id +".mp3");
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("audio/mpeg"));

        InputStream inputStream = resource.getInputStream();
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

        return ResponseEntity.ok().headers(headers).body(inputStreamResource);
    }
}
