package com.chess_fsm.chess.game;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chess_fsm.chess.game.dto.moveDTO;
import com.chess_fsm.chess.game.gameLogic.Board.Board;

@RestController
@RequestMapping(path="api/v1/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping()
    public ResponseEntity<Board> getBoard(){
        return new ResponseEntity<Board>(gameService.startNewGame(),HttpStatus.OK);
    }

    @PostMapping(path="/move")
    public ResponseEntity<Board> move(@RequestBody moveDTO move){
        return new ResponseEntity<Board>(gameService.makeMove(move),HttpStatus.OK);
    }
    
}
