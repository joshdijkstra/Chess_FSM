package com.chess_fsm.chess.game.gameLogic.Board;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.chess_fsm.chess.game.gameLogic.Pieces.Piece;
import com.chess_fsm.chess.game.gameLogic.Pieces.PieceType;

@Service
public class BoardService {

    public void updateBoard(Board board) {
        board.getAllPieces();
        this.updateLegalMoves(board);
        board.getAttackedSquares();
        board.setWhiteToMove(!board.isWhiteToMove());
    }

    public void updateLegalMoves(Board board) {
        board.setLegalMoves(new ArrayList<String>());
        for (Piece piece : board.isWhiteToMove() ? board.getBlackPieces() : board.getWhitePieces()) {
            piece.clearLegalMoves();
            piece.clearDefenders();
        }
        for (Piece piece : board.isWhiteToMove() ? board.getWhitePieces() : board.getBlackPieces()) {
            piece.clearLegalMoves();
            piece.clearDefenders();
            piece.getLegalMoves(board);
            if (piece.pieceType == PieceType.KING) {
                piece.isInCheck(board);
            }
        }
    }
}
