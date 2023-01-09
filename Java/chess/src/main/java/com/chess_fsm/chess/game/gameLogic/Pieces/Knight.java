package com.chess_fsm.chess.game.gameLogic.Pieces;

import com.chess_fsm.chess.game.gameLogic.Board.Board;
import com.chess_fsm.chess.game.gameLogic.Board.Square;

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
    public void getLegalMoves(Board board){
      Square[][] squares = board.getSquares();
      for (int row = 0; row < squares.length; row++) {
          for (int col = 0; col < squares[row].length; col++) {
              if (this.canMoveTo(row,col)){
                this.addLegalMove(row, col);

              }
          }
       }
  }
  }
  