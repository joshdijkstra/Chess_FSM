package com.chess_fsm.chess.game;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chess_fsm.chess.game.dto.moveDTO;
import com.chess_fsm.chess.game.gameLogic.Board.Board;

@Controller
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // @MessageMapping("/startGame")
    // @SendTo("/topic/messages")
    // public ResponseEntity<Board> getBoard() {
    //     return new ResponseEntity<Board>(gameService.startNewGame(), HttpStatus.OK);
    // }

    // @MessageMapping("/makeMove")
    // @SendTo("/topic/messages")
    // public ResponseEntity<Board> move(@RequestBody String moveObj) {
    //     return new ResponseEntity<Board>(gameService.makeMove(moveObj), HttpStatus.OK);
    // }

}
