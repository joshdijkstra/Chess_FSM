package com.chess_fsm.chess.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.chess_fsm.chess.game.gameLogic.Board.Board;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate template;
    private final GameService gameService;

    @Autowired
    WebSocketController(SimpMessagingTemplate template, GameService gameService){
        this.template = template;
        this.gameService = gameService;
    }

    @MessageMapping("/send/message")
    public void sendMessage(String message){
        System.out.println(message);
        this.template.convertAndSend("/message",  gameService.startNewGame());
    //     return gameService.startNewGame();

    }

    // @MessageMapping("/startGame")
    // @SendTo("/topic/messages")
    // public Board getBoard() {
    //     return gameService.startNewGame();
    // }

    // @MessageMapping("/makeMove")
    // @SendTo("/topic/messages")
    // public ResponseEntity<Board> move(@RequestBody moveDTO moveObj) {
    //     return new ResponseEntity<Board>(gameService.makeMove(moveObj), HttpStatus.OK);
    // }
}