package com.chess_fsm.chess.game.gameLogic.Pieces;

public class Knight extends Piece{
  
    public Knight(int x, int y, boolean isWhite) {
        super(x,y,isWhite, PieceType.KNIGHT);
    }
  
    public boolean canMoveTo(int x, int y) {
      // Knights move in an L-shape: two squares in one direction, then one square perpendicular to that direction
      int xDiff = Math.abs(x - this.x);
      int yDiff = Math.abs(y - this.y);
      if ((xDiff == 2 && yDiff == 1) || (xDiff == 1 && yDiff == 2)) {
        return true;
      }
      return false;
    }
  }
  