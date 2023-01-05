package com.chess_fsm.chess.game.gameLogic.Pieces;

public class Rook extends Piece {

  
    public Rook(int x, int y, boolean isWhite) {
      super(x,y,isWhite, PieceType.ROOK);
    }
  
    public boolean canMoveTo(int x, int y) {
      // Rooks can only move along rows or columns
      if (x != this.x && y != this.y) {
        return false;
      }
  
      // Rooks cannot move to their current position
      if (x == this.x && y == this.y) {
        return false;
      }
  
      return true;
    }
  }
  