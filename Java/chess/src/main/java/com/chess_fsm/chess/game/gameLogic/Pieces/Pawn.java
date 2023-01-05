package com.chess_fsm.chess.game.gameLogic.Pieces;

public class Pawn extends Piece {
      
        public Pawn(int x, int y, boolean isWhite) {
            super(x,y,isWhite, PieceType.PAWN);
        }
      
        public boolean canMoveTo(int x, int y) {
          // Pawns can only move forward
          if (isWhite && y < this.y) {
            return false;
          }
          if (!isWhite && y > this.y) {
            return false;
          }
      
          // Pawns can move one square at a time, or two squares on their first move
          int yDiff = Math.abs(y - this.y);
          if (yDiff > 2) {
            return false;
          }
          if (yDiff == 2 && this.y != (isWhite ? 1 : 6)) {
            return false;
          }
      
          // Pawns can only move diagonally to capture a piece
          int xDiff = Math.abs(x - this.x);
          if (xDiff != 1 || yDiff != 1) {
            return false;
          }
      
          return true;
        }
      }
      

