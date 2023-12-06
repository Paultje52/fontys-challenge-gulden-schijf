package me.paultje52.fontysChallenge.guldenSchijf.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.paultje52.fontysChallenge.guldenSchijf.model.BandModel;
import me.paultje52.fontysChallenge.guldenSchijf.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/band")
@Tag(name = "Band", description = "Endpoints for bands")
public class BandController {
    @Autowired
    private BandService bandService;

    @GetMapping("/{id}")
    public BandModel getBandById(@PathVariable int id) {
        BandModel model = this.bandService.getBandById(id);
        if (model == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Band not found");

        return model;
    }
}
