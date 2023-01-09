package com.chess_fsm.chess.game.gameLogic.Pieces;

import com.chess_fsm.chess.game.gameLogic.Board.Board;
import com.chess_fsm.chess.game.gameLogic.Board.Square;

public class Rook extends Piece {

  
    public Rook(int x, int y, boolean isWhite) {
      super(x,y,isWhite, PieceType.ROOK);
    }
  
    public void getLegalMoves(Board board){
      for (int row = this.x + 1 ; row < 8; row++){
        System.out.println(row + " , " + this.y);
        if ( board.isPieceOnSquare(row, this.y) == null ){
          this.addLegalMove(row, this.y);
        } else if (board.isPieceOnSquare(row, this.y).isWhite != this.isWhite){
          this.addLegalMove(row, this.y);
          break;
        } else if (board.isPieceOnSquare(row, this.y).isWhite == this.isWhite){
          break;
        }
      }

      for (int row = this.x -1 ; row >= 0; row--){
        if ( board.isPieceOnSquare(row, this.y) == null ){
          this.addLegalMove(row, this.y);
        } else if (board.isPieceOnSquare(row, this.y).isWhite != this.isWhite){
          this.addLegalMove(row, this.y);
          break;
        }
        else if (board.isPieceOnSquare(row, this.y).isWhite == this.isWhite){
          break;
        }
      }

      for (int col = this.y + 1 ; col < 8; col++){
        if ( board.isPieceOnSquare(this.x, col) == null ){
          this.addLegalMove(this.x, col);
        } else if (board.isPieceOnSquare(this.x, col).isWhite != this.isWhite){
          this.addLegalMove(this.x, col);
          break;
        }
        else if (board.isPieceOnSquare(this.x, col).isWhite == this.isWhite){
          break;
        }
      }

      for (int col = this.y - 1 ; col >= 0; col--){
        if ( board.isPieceOnSquare(this.x, col) == null ){
          this.addLegalMove(this.x, col);
        } else if (board.isPieceOnSquare(this.x, col).isWhite != this.isWhite){
          this.addLegalMove(this.x, col);
          break;
        }
        else if (board.isPieceOnSquare(this.x, col).isWhite == this.isWhite){
          break;
        }
      }
  }
  }
  