package com.chess_fsm.chess.player;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final Map<String , Player> playerUserMap = new ConcurrentHashMap<>();

    private Player createPlayer(String username){
        return new Player(1L,
        username,
        1500.0,
        LocalDate.now(),
        LocalDate.now());
    }

    public Player get(String username) { return playerUserMap.get(username);}
}
