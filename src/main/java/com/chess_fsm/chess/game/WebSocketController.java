package com.chess_fsm.chess.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.chess_fsm.chess.game.dto.moveDTO;
import com.chess_fsm.chess.game.gameLogic.Board.Board;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate template;
    private final GameService gameService;    
    private final GameRepository gameRepository;


    @Autowired
    WebSocketController(SimpMessagingTemplate template, GameService gameService, GameRepository gameRepository) {
        this.template = template;
        this.gameService = gameService;
        this.gameRepository = gameRepository;
    }

    @MessageMapping("/send/message")
    public void startGame(SimpMessageHeaderAccessor headers) {
        String simpSessionId = headers.getSessionId();        
        this.template.convertAndSend("/message", gameService.startNewGame(simpSessionId));
    }

    @MessageMapping("/ws-makemove")
    public void makeMove(String moveObj, SimpMessageHeaderAccessor headers) {
        String simpSessionId = headers.getSessionId();
        template.convertAndSend("/message", gameService.makeMove(simpSessionId,moveObj));

    }
}