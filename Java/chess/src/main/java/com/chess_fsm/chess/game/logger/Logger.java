package com.chess_fsm.chess.game.logger;


import org.springframework.stereotype.Service;

import com.chess_fsm.chess.game.dto.moveDTO;

@Service
public class Logger {
    public void logMove(moveDTO moves, String username){
        System.out.println(username + "made move" + moves.toString());
    }
}
