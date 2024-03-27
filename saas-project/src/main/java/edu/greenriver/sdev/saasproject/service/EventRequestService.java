package edu.greenriver.sdev.saasproject.service;

import edu.greenriver.sdev.saasproject.db.EventRequestRepository;
import edu.greenriver.sdev.saasproject.domain.EventRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EventRequestService {
    private EventRequestRepository repository;

    public EventRequestService(EventRequestRepository repository) {
        this.repository = repository;
    }

    public List<EventRequest> all()
    {
        List<EventRequest> events = repository.findAll();
        return Collections.unmodifiableList(events);
    }

    public List<EventRequest> byDate(String date)
    {
        List<EventRequest> results = new ArrayList<>();
        List<EventRequest> events = repository.findAll();

        for (int i = 0; i < events.size(); i++)
        {
            EventRequest next = events.get(i);
            if (next.getDate().equals(date))
            {
                results.add(next);
            }
        }

        return results;
    }

    public EventRequest addEvent(EventRequest EventRequest) {
        return repository.save(EventRequest);
    }

    public EventRequest updateEvent(EventRequest updatedEvent, int id) {
        EventRequest currentEvent = repository.findById(id).orElseThrow();

        currentEvent.setDate(updatedEvent.getDate());
        currentEvent.setLocation(updatedEvent.getLocation());
        currentEvent.setGenres(updatedEvent.getGenres());
        currentEvent.setSpecialRequests(updatedEvent.getSpecialRequests());
        currentEvent.setEquipment(updatedEvent.getEquipment());

        return repository.save(currentEvent);
    }

    public void deleteEvent(int id)
    {
        repository.deleteById(id);
    }

    public EventRequest byId(int id)
    {
        return repository.findById(id).orElse(null);
    }
}




