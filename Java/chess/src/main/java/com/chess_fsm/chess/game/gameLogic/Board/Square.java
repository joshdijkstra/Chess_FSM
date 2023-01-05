package com.chess_fsm.chess.game.gameLogic.Board;

import com.chess_fsm.chess.game.gameLogic.Pieces.Piece;

public class Square {
    private Piece piece;
    private int x;
    private int y;
    private boolean isWhite;
  
    public Square(int x, int y) {
      this.x = x;
      this.y = y;
      this.isWhite = (x + y) % 2 == 0;
    }
  
    @Override
    public String toString() {
        return "Square [piece=" + piece + ", x=" + x + ", y=" + y + ", isWhite=" + isWhite + "]";
    }

    public void setPiece(Piece piece) {
      this.piece = piece;
    }
  
    public Piece getPiece() {
      return piece;
    }
  
    public int getX() {
      return x;
    }
  
    public int getY() {
      return y;
    }
  
    public boolean isWhite() {
      return isWhite;
    }
  }
  