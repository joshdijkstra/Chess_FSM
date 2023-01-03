package com.chess_fsm.chess.game;

import org.springframework.stereotype.Service;

@Service
public class GameService {
    public String getGameDetails(){
        return "New Game Started";
    }
}
