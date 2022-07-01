package com.cognizant.sportseventmanagement.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Sports implements Serializable {

    @Id
    @Column(nullable = false, updatable = false)
    private Long sportsId;

    private int noOfPlayers;
    @Column(nullable = false, updatable = false)
    private String sportsName;
    private String sportsType;

    public Sports() {
    }

    public Sports(Long sportsId, int noOfPlayers, String sportsName, String sportsType) {
        this.sportsId = sportsId;
        this.noOfPlayers = noOfPlayers;
        this.sportsName = sportsName;
        this.sportsType = sportsType;
    }

    public Long getSportsId() {
        return sportsId;
    }

    public void setSportsId(Long sportsId) {
        this.sportsId = sportsId;
    }

    public int getNoOfPlayers() {
        return noOfPlayers;
    }

    public void setNoOfPlayers(int noOfPlayers) {
        this.noOfPlayers = noOfPlayers;
    }

    public String getSportsName() {
        return sportsName;
    }

    public void setSportsName(String sportsName) {
        this.sportsName = sportsName;
    }

    public String getSportsType() {
        return sportsType;
    }

    public void setSportsType(String sportsType) {
        this.sportsType = sportsType;
    }

    @Override
    public String toString() {
        return "Sports{" +
                "sportsId=" + sportsId +
                ", noOfPlayers=" + noOfPlayers +
                ", sportsName='" + sportsName + '\'' +
                ", sportsType='" + sportsType + '\'' +
                '}';
    }
}
