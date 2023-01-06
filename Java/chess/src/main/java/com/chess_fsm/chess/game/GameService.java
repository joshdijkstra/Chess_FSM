package com.chess_fsm.chess.game;

import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.chess_fsm.chess.game.dto.moveDTO;
import com.chess_fsm.chess.game.gameLogic.Board.Board;
import com.chess_fsm.chess.game.gameLogic.Board.BoardService;
import com.chess_fsm.chess.game.gameState.GameState;
import com.chess_fsm.chess.game.gameState.GameStateService;

@Service
public class GameService {

    public final String username = "josh";
    private final BoardService boardService;
    private final GameStateService gameStateService;

    public GameService(BoardService boardService,GameStateService gameStateService){
        this.boardService = boardService;
        this.gameStateService = gameStateService;
    }

    public String getGameDetails(){
        return "New Game Started";
    }

    public Board startNewGame(){
        GameState game = gameStateService.getStateForPlayer(this.username);
        return game.getGameObjects().getBoard();
    }

    public Board makeMove(moveDTO moves) {
        GameState game = gameStateService.getStateForPlayer(this.username);
        game.makeMove(moves);
        System.out.println("User " + username + " moved" + moves.toString());
        return game.getGameObjects().getBoard();
    }
}
