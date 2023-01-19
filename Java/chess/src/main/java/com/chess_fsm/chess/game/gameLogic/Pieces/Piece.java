package com.chess_fsm.chess.game.gameLogic.Pieces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public List<int[]> legalMoves;
    public List<int[]> defends;
    public boolean isPinned;

    public Piece(int x, int y, boolean isWhite, PieceType pieceType) {
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
        this.pieceType = pieceType;
        this.legalMoves = new ArrayList<int[]>();
        this.defends = new ArrayList<int[]>();
    }

    @Override
    public String toString() {
        return "Piece [x=" + x + ", y=" + y + ", isWhite=" + isWhite + ", pieceType=" + pieceType + ", legalMoves="
                + legalMoves + "]";
    }

    public void addLegalMove(int row, int col, Board board) {
        int[] list = { row, col };
        if (!board.isOnlyKingMoves()) {
            if (board.isRequiresMasks()) {
                for (int[] item : board.getPushMask()) {
                    if (Arrays.equals(item, list)) {
                        this.legalMoves.add(list);
                        board.addLegalMove(moveEncoder(new Move(new int[] { this.x, this.y }, list)));
                        return;
                    }
                }
                for (int[] item : board.getCaptureMask()) {
                    if (Arrays.equals(item, list)) {
                        this.legalMoves.add(list);
                        board.addLegalMove(moveEncoder(new Move(new int[] { this.x, this.y }, list)));
                        return;
                    }
                }
            } else {
                this.legalMoves.add(list);
                board.addLegalMove(moveEncoder(new Move(new int[] { this.x, this.y }, list)));
            }
        }
    }

    public void addDefends(int row, int col) {
        int[] list = { row, col };
        this.defends.add(list);
    }

    public void getLegalMoves(Board board) {
        System.out.println("Overwrite");
    }

    public void clearLegalMoves() {
        this.setLegalMoves(new ArrayList<int[]>());
    }

    public void clearDefenders() {
        this.setDefends(new ArrayList<int[]>());
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
