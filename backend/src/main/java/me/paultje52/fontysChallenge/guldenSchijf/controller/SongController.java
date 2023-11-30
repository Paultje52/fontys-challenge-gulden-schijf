package me.paultje52.fontysChallenge.guldenSchijf.controller;

import me.paultje52.fontysChallenge.guldenSchijf.model.SongModel;
import me.paultje52.fontysChallenge.guldenSchijf.service.SongService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/song")
public class SongController {
    @Autowired
    private SongService songService;
    @RequestMapping("")
    public SongModel[] getAllSongs(){
        return songService.GetAllSongs();
    }

    @RequestMapping("/id/{id}")
    public ResponseEntity<InputStreamResource> songById(@PathVariable int id) throws IOException {
        return songService.Mp3(id);
    }

}
