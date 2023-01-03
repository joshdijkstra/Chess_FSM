package com.chess_fsm.chess.game.gameLogic.Pieces;

import com.chess_fsm.chess.game.gameLogic.Piece;

public class Bishop extends Piece {
  
    public Bishop(int x, int y, boolean isWhite) {
      super(x, y, isWhite);
    }
  
    public boolean canMoveTo(int x, int y) {
      // Bishops can only move along diagonals
      if (Math.abs(x - this.x) != Math.abs(y - this.y)) {
        return false;
      }
  
      // Bishops cannot move to their current position
      if (x == this.x && y == this.y) {
        return false;
      }
  
      return true;
    }
  }
  