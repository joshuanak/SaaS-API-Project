package edu.greenriver.sdev.saasproject.controllers;

import edu.greenriver.sdev.saasproject.domain.EventRequest;
import edu.greenriver.sdev.saasproject.service.EventRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class EventRequestApi {
    private EventRequestService service;

    public EventRequestApi(EventRequestService service) {
        this.service = service;
    }

    @GetMapping("events/all")
    public ResponseEntity<List<EventRequest>> getAll() {
        return new ResponseEntity<>(service.all(), HttpStatus.OK);
    }

    @GetMapping("events/{date}")
    public ResponseEntity<List<EventRequest>> getByDate(@PathVariable String date) {
        return new ResponseEntity<>(service.byDate(date), HttpStatus.OK);
    }

    @PostMapping("events/")
    public ResponseEntity addMovie(@RequestBody EventRequest event) {
        return new ResponseEntity(service.addEvent(event), HttpStatus.CREATED);
    }

    @PutMapping("events/")
    public ResponseEntity addEvent(@RequestBody EventRequest newEvent) {
        return new ResponseEntity(service.addEvent(newEvent), HttpStatus.CREATED);
    }

    @PutMapping("events/{id}")
    public ResponseEntity<EventRequest> updateEvent(@PathVariable int id,
                                             @RequestBody EventRequest updatedEvent)
    {
        EventRequest event = service.byId(id);
        if (event == null)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else
        {
            return new ResponseEntity(service.updateEvent(updatedEvent, id),
                    HttpStatus.OK);
        }
    }

    @DeleteMapping("events/{id}")
    public ResponseEntity deleteEvent(@PathVariable int id)
    {
        EventRequest event = service.byId(id);
        if (event == null)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else
        {
            service.deleteEvent(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}




