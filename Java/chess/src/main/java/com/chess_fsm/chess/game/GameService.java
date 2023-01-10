package com.chess_fsm.chess.game;

import org.springframework.stereotype.Service;
import com.chess_fsm.chess.game.dto.moveDTO;
import com.chess_fsm.chess.game.gameLogic.Board.Board;
import com.chess_fsm.chess.game.gameLogic.Board.BoardService;
import com.chess_fsm.chess.game.gameState.GameState;
import com.chess_fsm.chess.game.gameState.GameStateService;
import com.chess_fsm.chess.game.logger.Logger;

@Service
public class GameService {

    public final String username = "josh";
    private final BoardService boardService;
    private final GameStateService gameStateService;
    private final Logger logger;

    public GameService(BoardService boardService, GameStateService gameStateService, Logger logger) {
        this.boardService = boardService;
        this.gameStateService = gameStateService;
        this.logger = logger;
    }

    public String getGameDetails() {
        return "New Game Started";
    }

    public Board startNewGame() {
        GameState game = gameStateService.getStateForPlayer(this.username);

        boardService.updateLegalMoves(game.getGameObjects().getBoard());
        return game.getGameObjects().getBoard();
    }

    public Board makeMove(moveDTO moves) {
        GameState game = gameStateService.getStateForPlayer(this.username);
        game.makeMove(moves);
        logger.logMove(moves, username);
        boardService.updateLegalMoves(game.getGameObjects().getBoard());
        return game.getGameObjects().getBoard();
    }
}
