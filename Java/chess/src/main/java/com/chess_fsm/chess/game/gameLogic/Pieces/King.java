package com.chess_fsm.chess.game.gameLogic.Pieces;

public class King extends Piece {
      
        public King(int x, int y, boolean isWhite) {
          super(x,y,isWhite, PieceType.KING);
        }
      
        public boolean canMoveTo(int x, int y) {
          // Kings can move one square in any direction
          if (Math.abs(x - this.x) > 1 || Math.abs(y - this.y) > 1) {
            return false;
          }
      
          // Kings cannot move to their current position
          if (x == this.x && y == this.y) {
            return false;
          }
      
          return true;
        }
      }
      