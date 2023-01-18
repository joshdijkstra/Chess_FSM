package com.chess_fsm.chess.game.gameLogic.Board;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.chess_fsm.chess.game.dto.Move;
import com.chess_fsm.chess.game.gameLogic.Pieces.Piece;
import com.chess_fsm.chess.game.gameLogic.Pieces.PieceType;
import static com.chess_fsm.chess.game.gameState.Decoders.*;

@Service
public class BoardService {

    public void updateBoard(Board board) {
        board.getAllPieces();
        this.updateLegalMoves(board);
        board.getAttackedSquares();
    }

    public void updateLegalMoves(Board board) {
        board.setLegalMoves(new ArrayList<String>());
        for (Piece piece : board.getPiecesAll()) {
            piece.getLegalMoves(board);
        }
        board.getCheckedMoves(board.isWhiteToMove(), this);
        for (Piece piece : board.isWhiteToMove() ? board.getBlackPieces() : board.getWhitePieces()) {
            piece.clearLegalMoves();
        }
    }

    public void recalculateLegalMoves(Board board) {
        board.setLegalMoves(new ArrayList<String>());
        for (Piece piece : board.getPiecesAll()) {
            piece.getLegalMoves(board);
        }
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

    public void makeMove(Board board, String move) {
        Move moves = moveDecoder(move);
        int xDif = moves.getMoveTo()[0] - moves.getPieceAt()[0];
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
        board.setWhiteToMove(!board.isWhiteToMove());
    }

    public void undoMove(Board board, String move) {
        StringBuilder reversedMove = new StringBuilder();
        reversedMove.append(move.charAt(2));
        reversedMove.append(move.charAt(3));
        reversedMove.append(move.charAt(0));
        reversedMove.append(move.charAt(1));
        this.makeMove(board, reversedMove.toString());
    }
}
