package com.chess_fsm.chess.game.gameLogic.Pieces;

import java.util.ArrayList;
import java.util.List;

import com.chess_fsm.chess.game.gameLogic.Board.Board;

import lombok.Data;

@Data
public class Piece {
    public int x;
    public int y;
    public boolean isWhite;
    public PieceType pieceType;
    public List<int[]> legalMoves;

    public Piece(int x, int y, boolean isWhite, PieceType pieceType) {
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
        this.pieceType = pieceType;
        this.legalMoves = new ArrayList<int[]>();
    }

    @Override
    public String toString() {
        return "Piece [x=" + x + ", y=" + y + ", isWhite=" + isWhite + ", pieceType=" + pieceType + ", legalMoves="
                + legalMoves + "]";
    }

    public void addLegalMove(int row, int col) {
        int[] list = { row, col };
        this.legalMoves.add(list);
    }

    public void getLegalMoves(Board board) {
        System.out.println("Overwrite");
    }

    public void clearLegalMoves() {
        this.setLegalMoves(new ArrayList<int[]>());
    }

}
