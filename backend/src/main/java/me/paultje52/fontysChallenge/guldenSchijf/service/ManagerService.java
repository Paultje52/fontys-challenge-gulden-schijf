package me.paultje52.fontysChallenge.guldenSchijf.service;

import me.paultje52.fontysChallenge.guldenSchijf.model.ManagerModel;
import me.paultje52.fontysChallenge.guldenSchijf.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {
    @Autowired
    private ManagerRepository managerRepository;

    public ManagerModel getManagerById(int id) {
        return this.managerRepository.findById(id).orElse(null);
    }
}
