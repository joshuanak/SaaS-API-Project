package edu.greenriver.sdev.saasproject.service;

import edu.greenriver.sdev.saasproject.db.EventRequestRepository;
import edu.greenriver.sdev.saasproject.db.SoundCloudMixRepository;
import edu.greenriver.sdev.saasproject.domain.EventRequest;
import edu.greenriver.sdev.saasproject.domain.SoundCloudMix;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SoundCloudMixService {
    private SoundCloudMixRepository repository;

    public SoundCloudMixService(SoundCloudMixRepository repository) {
        this.repository = repository;
    }

    public List<SoundCloudMix> all()
    {
        List<SoundCloudMix> mixes = repository.findAll();
        return Collections.unmodifiableList(mixes);
    }

    public SoundCloudMix byId(int id)
    {
        return repository.findById(id).orElse(null);
    }

    public SoundCloudMix addMix(SoundCloudMix mix) {
        return repository.save(mix);
    }

    public SoundCloudMix updateEvent(SoundCloudMix updatedMix, int id) {
        SoundCloudMix currentMix = repository.findById(id).orElseThrow();

        currentMix.setUrl(updatedMix.getUrl());
        currentMix.setIsb2b(updatedMix.isIsb2b());
        currentMix.setName(updatedMix.getName());
        currentMix.setGenre(updatedMix.getGenre());

        return repository.save(currentMix);
    }

    public void deleteMix(int id)
    {
        repository.deleteById(id);
    }
}




