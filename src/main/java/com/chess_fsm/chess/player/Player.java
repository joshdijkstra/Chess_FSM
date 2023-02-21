package com.chess_fsm.chess.player;

import java.time.LocalDate;

public class Player {
    private Long id;
    private String username;
    private Double elo;
    private LocalDate dateJoined;
    private LocalDate lastActive;
    
    public Player(String username, Double elo, LocalDate dateJoined, LocalDate lastActive) {
        this.username = username;
        this.elo = elo;
        this.dateJoined = dateJoined;
        this.lastActive = lastActive;
    }

    public Player(Long id, String username, Double elo, LocalDate dateJoined, LocalDate lastActive) {
        this.id = id;
        this.username = username;
        this.elo = elo;
        this.dateJoined = dateJoined;
        this.lastActive = lastActive;
    }

    public Player(String username) {
        this.username = username;
    }
    
}
