package com.chess_fsm.chess.game.gameState;

import java.util.concurrent.CopyOnWriteArrayList;

import com.chess_fsm.chess.game.GameObjects;
import com.chess_fsm.chess.game.dto.moveDTO;
import com.chess_fsm.chess.game.gameLogic.Board.Board;
import com.chess_fsm.chess.game.gameLogic.Board.Square;
import com.chess_fsm.chess.game.gameLogic.Pieces.Piece;
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

    public void castle(Board board, int xDif, boolean isWhite) {
        Square rook = board.getSquare(xDif >= 0 ? 7 : 0, isWhite ? 0 : 7);
        rook.getPiece().updateXY(xDif >= 0 ? 5 : 3, isWhite ? 0 : 7);
        Square moveTo = board.getSquare(xDif >= 0 ? 5 : 3, isWhite ? 0 : 7);
        moveTo.setPiece(rook.getPiece());
        board.setSquare(xDif >= 0 ? 5 : 3, isWhite ? 0 : 7, moveTo);
        board.setSquare(xDif >= 0 ? 7 : 0, isWhite ? 0 : 7,
                new Square(xDif >= 0 ? 7 : 0, isWhite ? 0 : 7));
    }

    public void makeMove(moveDTO moves) {
        int xDif = moves.getMoveTo()[0] - moves.getPieceAt()[0];
        Board board = this.getGameObjects().getBoard();
        Square current = board.getSquare(moves.getPieceAt()[0], moves.getPieceAt()[1]);
        Square moveTo = board.getSquare(moves.getMoveTo()[0], moves.getMoveTo()[1]);
        current.getPiece().updateXY(moves.getMoveTo()[0], moves.getMoveTo()[1]);
        if (current.getPiece().pieceType == PieceType.KING || current.getPiece().pieceType == PieceType.ROOK) {
            current.getPiece().setHasMoved(true);
        }
        if (current.getPiece().pieceType == PieceType.KING && Math.abs(xDif) == 2) {
            this.castle(board, xDif, current.getPiece().isWhite);
        }
        moveTo.setPiece(current.getPiece());
        board.setSquare(moves.getMoveTo()[0], moves.getMoveTo()[1], moveTo);
        board.setSquare(moves.getPieceAt()[0],
                moves.getPieceAt()[1],
                new Square(moves.getPieceAt()[0], moves.getPieceAt()[1]));
    }

}
