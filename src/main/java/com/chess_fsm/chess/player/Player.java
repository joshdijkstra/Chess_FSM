package com.chess_fsm.chess.player;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Player {

    private String id;
    private String username;
    private String colour;
    private Double rating;
    private LocalDate joinDate;
    private LocalDate lastLoginDate;

    public Player(String id, String username, String colour, Double rating, LocalDate joinDate, LocalDate lastLoginDate) {
        this.id = id;
        this.username = username;
        this.colour = colour;
        this.rating = rating;
        this.joinDate = joinDate;
        this.lastLoginDate = lastLoginDate;
    }

    public Player(String id, String username){
        this.id = id;
        this.username = username;
        this.colour = "unassigned";
        this.rating = 1000.0;
        this.joinDate = LocalDate.now();
        this.lastLoginDate = LocalDate.now();
    }
}
