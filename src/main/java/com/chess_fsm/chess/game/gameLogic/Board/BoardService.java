package com.chess_fsm.chess.game.gameLogic.Board;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.chess_fsm.chess.game.dto.Move;
import com.chess_fsm.chess.game.gameLogic.Pieces.Bishop;
import com.chess_fsm.chess.game.gameLogic.Pieces.Knight;
import com.chess_fsm.chess.game.gameLogic.Pieces.Piece;
import com.chess_fsm.chess.game.gameLogic.Pieces.PieceType;
import com.chess_fsm.chess.game.gameLogic.Pieces.Queen;
import com.chess_fsm.chess.game.gameLogic.Pieces.Rook;

import static com.chess_fsm.chess.game.gameState.Decoders.*;

@Service
public class BoardService {

    public void updateBoard(Board board) {
        board.getAllPieces();
        board.removeKing(!board.isWhiteToMove());
        this.updatePseudoMoves(board);
        board.returnKingToBoard(!board.isWhiteToMove());
        // board.getAttackedSquares();
        // for (Piece piece : board.isWhiteToMove() ? board.getBlackPieces() :
        // board.getWhitePieces()) {
        // piece.clearLegalMoves();
        // }
    }

    public void updatePseudoMoves(Board board) {
        for (Piece piece : board.getPiecesAll()) {
            piece.pseudoMoveGenerator(board);
        }

    }

    public void recalculateLegalMoves(Board board) {
        board.getLegalMoves().clear();
        for (Piece piece : board.getPiecesAll()) {
            piece.pseudoMoveGenerator(board);
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

    public Piece promote(Board board, String move, Move decodedMove) {
        char ch = move.charAt(4);
        int x = decodedMove.getMoveTo()[0];
        int y = decodedMove.getMoveTo()[1];
        boolean isWhite = y == 7 ? true : false;
        switch (Character.toUpperCase(ch)) {
            case 'N':
                return new Knight(x, y, isWhite);
            case 'B':
                return new Bishop(x, y, isWhite);
            case 'R':
                return new Rook(x, y, isWhite);
            case 'Q':
                return new Queen(x, y, isWhite);
        }
        return null;
    }

    public void makeMove(Board board, String move) {
        Move moves = moveDecoder(move);

        int xDif = moves.getMoveTo()[0] - moves.getPieceAt()[0];
        Square current = board.getSquare(moves.getPieceAt()[0], moves.getPieceAt()[1]);
        Square moveTo = board.getSquare(moves.getMoveTo()[0], moves.getMoveTo()[1]);
        if (current == moveTo){
            return;
        }
        current.getPiece().updateXY(moves.getMoveTo()[0], moves.getMoveTo()[1]);
        if (current.getPiece().pieceType == PieceType.KING || current.getPiece().pieceType == PieceType.ROOK) {
            current.getPiece().setHasMoved(true);
        }
        if (current.getPiece().pieceType == PieceType.KING && Math.abs(xDif) == 2) {
            this.castle(board, xDif, current.getPiece().isWhite);
        }
        boolean promoting = move.length() == 5;
        if (promoting) {
            moveTo.setPiece(promote(board, move, moves));

        } else {
            moveTo.setPiece(current.getPiece());
        }
        board.setSquare(moves.getMoveTo()[0], moves.getMoveTo()[1], moveTo);
        board.setSquare(moves.getPieceAt()[0],
                moves.getPieceAt()[1],
                new Square(moves.getPieceAt()[0], moves.getPieceAt()[1]));
        board.setWhiteToMove(!board.isWhiteToMove());
    }

}
