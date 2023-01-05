package com.chess_fsm.chess.game.gameLogic.Pieces;

public class Queen extends Piece {
      
        public Queen(int x, int y, boolean isWhite) {
          super(x, y, isWhite, PieceType.QUEEN);
        }
      
        public boolean canMoveTo(int x, int y) {
          // Queens can move along rows, columns, or diagonals
          if (x != this.x && y != this.y && Math.abs(x - this.x) != Math.abs(y - this.y)) {
            return false;
          }
      
          // Queens cannot move to their current position
          if (x == this.x && y == this.y) {
            return false;
          }
      
          return true;
        }
      }
      
