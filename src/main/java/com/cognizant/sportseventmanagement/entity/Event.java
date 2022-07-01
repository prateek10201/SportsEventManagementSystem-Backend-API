package com.cognizant.sportseventmanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class Event implements Serializable {

   @Id
   @Column(nullable = false, updatable = false)
   private Long eventId;
   //@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
   private Date eventDate;
   private String eventName;
   private int noofSlots;
   private String sportsName;

    public Event() {
    }

    public Event(Long eventId, Date eventDate, String eventName, int noofSlots, String sportsName) {
        this.eventId = eventId;
        this.eventDate = eventDate;
        this.eventName = eventName;
        this.noofSlots = noofSlots;
        this.sportsName = sportsName;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }


    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getNoofSlots() {
        return noofSlots;
    }

    public void setNoofSlots(int noofSlots) {
        this.noofSlots = noofSlots;
    }

    public String getSportsName() {
        return sportsName;
    }

    public void setSportsName(String sportsName) {
        this.sportsName = sportsName;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", eventDate=" + eventDate +
                ", eventName='" + eventName + '\'' +
                ", noofSlots=" + noofSlots +
                ", sportsName='" + sportsName + '\'' +
                '}';
    }
}
