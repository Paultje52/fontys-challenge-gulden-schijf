package me.paultje52.fontysChallenge.guldenSchijf.service;

import me.paultje52.fontysChallenge.guldenSchijf.model.BandModel;
import me.paultje52.fontysChallenge.guldenSchijf.repository.BandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BandService {
    @Autowired
    private BandRepository bandRepository;

    public BandModel getBandById(int id) {
        return this.bandRepository.findById(id).orElse(null);
    }
}
