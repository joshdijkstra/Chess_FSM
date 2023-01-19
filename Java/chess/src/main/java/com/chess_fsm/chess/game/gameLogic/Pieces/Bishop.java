package com.chess_fsm.chess.game.gameLogic.Pieces;

import com.chess_fsm.chess.game.gameLogic.Board.Board;

public class Bishop extends Piece {

  public Bishop(int x, int y, boolean isWhite) {
    super(x, y, isWhite, PieceType.BISHOP);
  }

  public void getLegalMoves(Board board) {
    this.clearLegalMoves();
    this.clearDefenders();

    for (int inc : new int[] { -1, 1 }) {
      for (int row = 1; row < 8; row++) {
        if (this.x + (row * inc) < 8 && this.y + (row * inc) < 8 && this.x + (row * inc) >= 0
            && this.y + (row * inc) >= 0) {
          if (board.isPieceOnSquare(this.x + row * inc, this.y + row * inc) == null) {
            this.addLegalMove(this.x + row * inc, this.y + row * inc, board);
          } else if (board.isPieceOnSquare(this.x + row * inc, this.y + row * inc).isWhite != this.isWhite) {
            this.addLegalMove(this.x + row * inc, this.y + row * inc, board);
            // this.xRayAttack(board, this.x + row, this.y + row);
            break;
          } else if (board.isPieceOnSquare(this.x + row * inc, this.y + row * inc).isWhite == this.isWhite) {
            this.addDefends(this.x + row * inc, this.y + row * inc);
            break;
          }
        }
      }
    }
    for (int inc : new int[] { -1, 1 }) {
      for (int row = 1; row < 8; row++) {
        if (this.x + (row * inc) < 8 && this.y - (row * inc) < 8 && this.x + (row * inc) >= 0
            && this.y - (row * inc) >= 0) {
          if (board.isPieceOnSquare(this.x + row * inc, this.y - row * inc) == null) {
            this.addLegalMove(this.x + row * inc, this.y - row * inc, board);
          } else if (board.isPieceOnSquare(this.x + row * inc, this.y - row * inc).isWhite != this.isWhite) {
            this.addLegalMove(this.x + row * inc, this.y - row * inc, board);
            // this.xRayAttack(board, this.x + row, this.y - row);

            break;
          } else if (board.isPieceOnSquare(this.x + row * inc, this.y - row * inc).isWhite == this.isWhite) {
            this.addDefends(this.x + row * inc, this.y - row * inc);
            break;
          }
        }
      }
    }
  }

  // public void xRayAttack(Board board, int x, int y) {
  // int xDir = this.x - x >= 0 ? 1 : -1;
  // int yDir = this.y - y >= 0 ? 1 : -1;
  // for (int i = this.x; i >= 0 && i < 8; i += xDir) {
  // for (int j = this.y; j >= 0 && i < 8; j += yDir) {
  // if (board.getSquare(i, j).getPiece().isWhite != this.isWhite
  // && board.getSquare(i, j).getPiece().pieceType == PieceType.KING) {

  // }
  // }
  // }

  // }
}
