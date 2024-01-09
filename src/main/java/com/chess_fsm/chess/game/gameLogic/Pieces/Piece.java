package com.chess_fsm.chess.game.gameLogic.Pieces;

import java.util.HashSet;

import com.chess_fsm.chess.game.dto.Move;
import com.chess_fsm.chess.game.gameLogic.Board.Board;
import static com.chess_fsm.chess.game.gameState.Decoders.*;

import lombok.Data;

@Data
public class Piece {
    public int x;
    public int y;
    public boolean isWhite;
    public PieceType pieceType;
    public HashSet<int[]> legalMoves;
    public HashSet<int[]> pseudoMoves;

    public HashSet<int[]> defends;
    public boolean isPinned;

    public Piece(int x, int y, boolean isWhite, PieceType pieceType) {
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
        this.pieceType = pieceType;
        this.pseudoMoves = new HashSet<int[]>();
        this.defends = new HashSet<int[]>();        
        this.legalMoves = new HashSet<int[]>();
    }

    @Override
    public String toString() {
        return "Piece [x=" + x + ", y=" + y + ", isWhite=" + isWhite + ", pieceType=" + pieceType + ", legalMoves="
                + legalMoves + "]";
    }

    public void addPseudoMove(int row, int col, Board board) {
        int[] list = { row, col };
        this.pseudoMoves.add(list);
        board.addLegalMove(moveEncoder(new Move(new int[] { this.x, this.y }, list)));
        return;
    }

    public void addDefends(int row, int col) {
        int[] list = { row, col };
        this.defends.add(list);
    }

    public void pseudoMoveGenerator(Board board) {
        System.out.println("Overwrite");
    }

    public void clearMoves() {
        this.getPseudoMoves().clear();
        this.getLegalMoves().clear();   
        this.getDefends().clear();
    }

    public void updateXY(int i, int j) {
        this.setX(i);
        this.setY(j);
    }

    public boolean isInCheck(Board board) {
        System.out.println("Overwrite");
        return false;

    }

    public void setHasMoved(boolean b) {
        System.out.println("Overwrite");
    }

    public boolean getHasMoved() {
        return false;
    }

    public void setInCheck(boolean b) {
        System.out.println("Overwrite");
    }

}
