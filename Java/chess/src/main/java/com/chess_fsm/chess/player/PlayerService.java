package com.chess_fsm.chess.player;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    private Player createPlayer(String username){
        return new Player(1L,
        username,
        1500.0,
        LocalDate.now(),
        LocalDate.now());
    }
}
