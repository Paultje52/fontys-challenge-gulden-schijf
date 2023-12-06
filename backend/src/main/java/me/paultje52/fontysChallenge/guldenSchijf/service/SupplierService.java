package me.paultje52.fontysChallenge.guldenSchijf.service;

import me.paultje52.fontysChallenge.guldenSchijf.model.SupplierModel;
import me.paultje52.fontysChallenge.guldenSchijf.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public SupplierModel getSupplierById(int id) {
        return this.supplierRepository.findById(id).orElse(null);
    }
}
