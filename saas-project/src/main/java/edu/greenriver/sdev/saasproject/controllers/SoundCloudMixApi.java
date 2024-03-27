package edu.greenriver.sdev.saasproject.controllers;

import edu.greenriver.sdev.saasproject.domain.EventRequest;
import edu.greenriver.sdev.saasproject.domain.SoundCloudMix;
import edu.greenriver.sdev.saasproject.service.SoundCloudMixService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class SoundCloudMixApi {
    private SoundCloudMixService service;

    public SoundCloudMixApi(SoundCloudMixService service) {
        this.service = service;
    }

    @GetMapping("mixes/all")
    public ResponseEntity<List<SoundCloudMix>> getAll() {
        return new ResponseEntity<>(service.all(), HttpStatus.OK);
    }

    @GetMapping("mixes/{id}")
    public ResponseEntity<SoundCloudMix> byId(@PathVariable int id)
    {
        SoundCloudMix mix = service.byId(id);
        if (mix == null)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else
        {
            return new ResponseEntity(mix, HttpStatus.OK);
        }
    }

    @PostMapping("mixes/")
    public ResponseEntity addMovie(@RequestBody SoundCloudMix mix) {
        return new ResponseEntity(service.addMix(mix), HttpStatus.CREATED);
    }

    @PutMapping("mixes/{id}")
    public ResponseEntity<SoundCloudMix> updateEvent(@PathVariable int id,
                                                    @RequestBody SoundCloudMix updatedMix)
    {
        SoundCloudMix mix = service.byId(id);
        if (mix == null)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else
        {
            return new ResponseEntity(service.updateEvent(mix, id),
                    HttpStatus.OK);
        }
    }

    @DeleteMapping("mixes/{id}")
    public ResponseEntity deleteMix(@PathVariable int id)
    {
        SoundCloudMix mix = service.byId(id);
        if (mix == null)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else
        {
            service.deleteMix(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}
