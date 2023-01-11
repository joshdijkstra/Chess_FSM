package com.chess_fsm.chess.game.gameState;

import java.util.concurrent.CopyOnWriteArrayList;

import com.chess_fsm.chess.game.GameObjects;
import com.chess_fsm.chess.game.dto.moveDTO;
import com.chess_fsm.chess.game.gameLogic.Board.Board;
import com.chess_fsm.chess.game.gameLogic.Board.Square;
import com.chess_fsm.chess.game.gameLogic.Pieces.PieceType;
import com.chess_fsm.chess.game.transitions.TransitionPhase;
import com.chess_fsm.chess.player.Player;
import com.chess_fsm.chess.player.PlayerService;

import lombok.Data;

@Data
public class GameState {
    private final String username;
    private final TransitionPhase phase;
    private final PlayerService playerService;
    private GameObjects gameObjects;
    private CopyOnWriteArrayList<Player> players;

    public GameState(CopyOnWriteArrayList<Player> players, String username, TransitionPhase phase,
            PlayerService playerService, GameObjects gameObjects) {
        this.players = players;
        this.username = username;
        this.phase = phase;
        this.playerService = playerService;
        this.gameObjects = gameObjects;
    }

    public void makeMove(moveDTO moves) {
        Board board = this.getGameObjects().getBoard();
        Square current = board.getSquare(moves.getPieceAt()[0], moves.getPieceAt()[1]);
        Square moveTo = board.getSquare(moves.getMoveTo()[0], moves.getMoveTo()[1]);
        current.getPiece().updateXY(moves.getMoveTo()[0], moves.getMoveTo()[1]);
        if (current.getPiece().pieceType == PieceType.KING || current.getPiece().pieceType == PieceType.ROOK) {
            current.getPiece().setHasMoved(true);
        }
        moveTo.setPiece(current.getPiece());
        board.setSquare(moves.getMoveTo()[0], moves.getMoveTo()[1], moveTo);
        board.setSquare(moves.getPieceAt()[0],
                moves.getPieceAt()[1],
                new Square(moves.getPieceAt()[0], moves.getPieceAt()[1]));
    }

}
