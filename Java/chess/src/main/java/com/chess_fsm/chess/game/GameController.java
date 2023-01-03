package com.chess_fsm.chess.game;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chess_fsm.chess.game.gameLogic.Board.Board;

@RestController
@RequestMapping(path="api/v1/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping()
    public String getGameDetails(){
        return gameService.startNewGame().toString();
    }
    
}
