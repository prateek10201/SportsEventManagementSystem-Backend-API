package com.cognizant.sportseventmanagement.service;

import com.cognizant.sportseventmanagement.entity.Event;
import com.cognizant.sportseventmanagement.entity.Sports;
import com.cognizant.sportseventmanagement.exception.EventGenerationException;
import com.cognizant.sportseventmanagement.exception.EventNotFoundException;
import com.cognizant.sportseventmanagement.exception.SportInsertionException;
import com.cognizant.sportseventmanagement.exception.SportNotFoundException;
import com.cognizant.sportseventmanagement.repository.EventRepository;
import com.cognizant.sportseventmanagement.repository.SportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SportsEventService {

    @Autowired
    private SportsRepository sportsRepository;

    @Autowired
    private EventRepository eventRepository;

    public Sports addSport(Long sportsId, int noOfPlayers, String sportsName, String sportsType) throws SportInsertionException
    {
        if(sportsId == null || noOfPlayers <= 0  || sportsName == null || sportsType == null)
            throw new SportInsertionException("Fields cannot be empty!");

        sportsRepository.findSportBySportsId(sportsId)
                .ifPresent(u -> {
                    throw new SportInsertionException("Sport already Exist!");
                });

        sportsRepository.findSportBySportsName(sportsName)
                .ifPresent(u -> {
                    throw new SportInsertionException("Sport Id already Exist!");
                });

        Sports sports = new Sports(sportsId, noOfPlayers, sportsName, sportsType);
        return sportsRepository.save(sports);
    }

    public List<Sports> findAllSports()
    {
        return sportsRepository.findAll();
    }

    public Sports findSportBySportsName(String sportsName)
    {
        return sportsRepository.findSportBySportsName(sportsName).orElseThrow(() -> new SportNotFoundException("Sport named "+sportsName+" not found!"));
    }

    public Event addEvent(Long eventId, Date eventDate, String eventName, int noofSlots, String sportsName) throws EventGenerationException
    {
        if(eventId == null || eventDate == null || eventName == null || noofSlots < 0 || sportsName == null)
            throw new EventGenerationException("Fields cannot be empty!");

        eventRepository.findEventByEventId(eventId)
                .ifPresent(p -> {
                    throw new EventGenerationException("Event with Id "+eventId+" already Exist!");
                });

        eventRepository.findEventByEventName(eventName)
                .ifPresent(p -> {
                    throw new EventGenerationException("Event already Exist!");
                });

        sportsRepository.findSportBySportsName(sportsName)
                .orElseThrow(() -> new SportNotFoundException("Event cant be generated since Sport "+sportsName+" doesnt exist in DB!"));

            Event event = new Event(eventId, eventDate, eventName, noofSlots, sportsName);
            return eventRepository.save(event);
    }

    public List<Event> findAllEvents()
    {
        return eventRepository.findAll();
    }

    public Event findByEventName(String eventName)
    {
        return eventRepository.findEventByEventName(eventName).orElseThrow(() -> new EventNotFoundException("Event named "+eventName+" does not exist!"));
    }

    public void deleteEvent(Long eventId)
    {
        Optional<Event> event = eventRepository.findEventByEventId(eventId);
        event.ifPresent(value -> eventRepository.delete(value));
        event.orElseThrow(() -> new EventNotFoundException("Event with Id "+eventId+" is not found!"));
    }

    

}
