package com.chess_fsm.chess.game.gameLogic.Pieces;

import com.chess_fsm.chess.game.dto.legalMoveDTO;
import com.chess_fsm.chess.game.gameLogic.Board.Board;
import com.chess_fsm.chess.game.gameLogic.Board.Square;

public class Piece {
    public int x;
    public int y;
    public boolean isWhite;
    public PieceType pieceType;
    public legalMoveDTO legalMoves;

    public Piece(int x, int y, boolean isWhite, PieceType pieceType) {
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
        this.pieceType = pieceType;
        this.legalMoves = new legalMoveDTO();
    }

    @Override
    public String toString() {
        return "Piece [x=" + x + ", y=" + y + ", isWhite=" + isWhite + ", pieceType=" + pieceType + ", legalMoves="
                + legalMoves + "]";
    }

    public void getLegalMoves(Board board){
        legalMoveDTO legalMoves = new legalMoveDTO();
        Square[][] squares = board.getSquares();
        for (int row = 0; row < squares.length; row++) {
            for (int col = 0; col < squares[row].length; col++) {
                if (this.canMoveTo(row,col)){
                    legalMoves.addMove(row,col);
                }
            }
         }
    }

    private boolean canMoveTo(int row , int col) {
        return false;
    }
}
