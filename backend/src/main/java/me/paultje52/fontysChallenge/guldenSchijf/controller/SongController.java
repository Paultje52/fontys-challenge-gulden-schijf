package me.paultje52.fontysChallenge.guldenSchijf.controller;

import me.paultje52.fontysChallenge.guldenSchijf.model.SongModel;
import me.paultje52.fontysChallenge.guldenSchijf.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/song")
public class SongController {
    @Autowired
    private SongService songService;

    @GetMapping("")
    public SongModel[] getAllSongs() {
        return songService.GetAllSongs();
    }

    @RequestMapping("/search")
    public SongModel[] searchAllSongs(@RequestParam(required = false) String title,@RequestParam(required = false) String band,@RequestParam(required = false) String manager,@RequestParam(required = false) String supplier){

        return songService.GetAllSongsFiltered(title,band,manager,supplier);

    }

    @RequestMapping("/{id}.mp3")
    public ResponseEntity<InputStreamResource> songById(@PathVariable int id) throws IOException {
        return songService.Mp3(id);
    }

}
