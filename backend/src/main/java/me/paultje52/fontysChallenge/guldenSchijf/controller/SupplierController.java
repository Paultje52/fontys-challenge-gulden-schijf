package me.paultje52.fontysChallenge.guldenSchijf.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.paultje52.fontysChallenge.guldenSchijf.model.ManagerModel;
import me.paultje52.fontysChallenge.guldenSchijf.model.SupplierModel;
import me.paultje52.fontysChallenge.guldenSchijf.service.ManagerService;
import me.paultje52.fontysChallenge.guldenSchijf.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/supplier")
@Tag(name = "Supplier", description = "Endpoints for suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping("/{id}")
    public SupplierModel getBandById(@PathVariable int id) {
        SupplierModel model = this.supplierService.getSupplierById(id);
        if (model == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found");

        return model;
    }
}
