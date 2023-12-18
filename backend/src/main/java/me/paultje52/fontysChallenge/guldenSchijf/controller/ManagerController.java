package me.paultje52.fontysChallenge.guldenSchijf.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.paultje52.fontysChallenge.guldenSchijf.model.ManagerModel;
import me.paultje52.fontysChallenge.guldenSchijf.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/manager")
@Tag(name = "Manager", description = "Endpoints for managers")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @GetMapping("/{id}")
    public ManagerModel getManagerById(@PathVariable int id) {
        ManagerModel model = this.managerService.getManagerById(id);
        if (model == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Manager not found");
        return model;
    }
}
