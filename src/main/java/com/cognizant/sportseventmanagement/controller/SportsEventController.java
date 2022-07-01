package com.cognizant.sportseventmanagement.controller;

import com.cognizant.sportseventmanagement.entity.Event;
import com.cognizant.sportseventmanagement.entity.Sports;
import com.cognizant.sportseventmanagement.service.SportsEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/sportsEvent")
public class SportsEventController {

    @Autowired
    private SportsEventService sportsEventService;

    @GetMapping("/viewAllSports")
    public ResponseEntity<List<Sports>> viewAllSports()
    {
        List<Sports> sports = sportsEventService.findAllSports();
        return new ResponseEntity<>(sports, HttpStatus.OK);
    }

    @GetMapping("/viewSportBySportName/{sportsName}")
    public ResponseEntity<Sports> viewSportBySportName(@PathVariable("sportsName") String sportsName)
    {
        Sports sports = sportsEventService.findSportBySportsName(sportsName);
        return new ResponseEntity<>(sports, HttpStatus.OK);
    }

    @GetMapping("/viewAllEvents")
    public ResponseEntity<List<Event>> viewAllEvents()
    {
        List<Event> events = sportsEventService.findAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/viewEventByEventName/{eventName}")
    public ResponseEntity<Event> viewEventByEventName(@PathVariable("eventName") String eventName)
    {
        Event event = sportsEventService.findByEventName(eventName);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PostMapping("/addSport")
    public ResponseEntity<Map<String, String>> addSport(@RequestBody Map<String, Object> sportMap)
    {
        String sportsId = (String) sportMap.get("sportsId");
        String noOfPlayers = (String) sportMap.get("noOfPlayers");
        String sportsName = (String) sportMap.get("sportsName");
        String sportsType = (String) sportMap.get("sportsType");

        Sports sports = sportsEventService.addSport(Long.parseLong(sportsId), Integer.parseInt(noOfPlayers), sportsName, sportsType);
        return new ResponseEntity<>(sportAddedSuccessfully(sports), HttpStatus.OK);
    }

    @PostMapping("/addEvent")
    public ResponseEntity<Map<String,String>> addEvent(@RequestBody Map<String ,Object> eventMap)
    {
        String eventId = (String) eventMap.get("eventId");
        String eventDate = (String) eventMap.get("eventDate");
        String eventName = (String) eventMap.get("eventName");
        String noofSlots = (String) eventMap.get("noofSlots");
        String sportsName = (String) eventMap.get("sportsName");

        Event event = sportsEventService.addEvent(Long.parseLong(eventId),Date.valueOf(eventDate), eventName, Integer.parseInt(noofSlots), sportsName);
        return new ResponseEntity<>(eventAddedSuccessfully(event), HttpStatus.OK);
    }

    @DeleteMapping("/cancelEvent/{eventId}")
    public ResponseEntity<Map<String, String>> cancelEvent(@PathVariable("eventId") String eventId)
    {
        sportsEventService.deleteEvent(Long.parseLong(eventId));
        return new ResponseEntity<>(eventDeletedSuccessfully(eventId),HttpStatus.OK);
    }

    private Map<String, String> eventDeletedSuccessfully(String eventId)
    {
        Map<String, String> map = new HashMap<>();

        map.put("message", "Event with Id "+eventId+" is deleted Successsfully!");
        return map;
    }

    private Map<String, String> eventAddedSuccessfully(Event event)
    {
        Map<String, String> map = new HashMap<>();

        map.put("message","Event Added Successfully");
        map.put("eventId",event.getEventId().toString());
        map.put("eventName",event.getEventName());
        map.put("sportName",event.getSportsName());

        return map;
    }

    private Map<String, String> sportAddedSuccessfully(Sports sports)
    {
        Map<String, String> map = new HashMap<>();

        map.put("message","Sport Added Successfully!");
        map.put("sportsId",sports.getSportsId().toString());
        map.put("sportsName",sports.getSportsName());

        return map;
    }

}
