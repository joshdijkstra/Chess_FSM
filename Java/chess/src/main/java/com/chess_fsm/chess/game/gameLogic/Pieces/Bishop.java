package com.chess_fsm.chess.game.gameLogic.Pieces;

import com.chess_fsm.chess.game.gameLogic.Board.Board;

public class Bishop extends Piece {

  public Bishop(int x, int y, boolean isWhite) {
    super(x, y, isWhite, PieceType.BISHOP);
  }

  public void getLegalMoves(Board board) {
    this.clearLegalMoves();

    for (int row = 1; row < 8; row++) {
      if (this.x + row < 8 && this.y + row < 8) {
        if (board.isPieceOnSquare(this.x + row, this.y + row) == null) {
          this.addLegalMove(this.x + row, this.y + row);
        } else if (board.isPieceOnSquare(this.x + row, this.y + row).isWhite != this.isWhite) {
          this.addLegalMove(this.x + row, this.y + row);
          break;
        } else if (board.isPieceOnSquare(this.x + row, this.y + row).isWhite == this.isWhite) {
          break;
        }
      }
    }

    for (int row = 1; row < 8; row++) {
      if (this.x + row < 8 && this.y - row >= 0) {
        if (board.isPieceOnSquare(this.x + row, this.y - row) == null) {
          this.addLegalMove(this.x + row, this.y - row);
        } else if (board.isPieceOnSquare(this.x + row, this.y - row).isWhite != this.isWhite) {
          this.addLegalMove(this.x + row, this.y - row);
          break;
        } else if (board.isPieceOnSquare(this.x + row, this.y - row).isWhite == this.isWhite) {
          break;
        }
      }
    }

    for (int row = 1; row < 8; row++) {
      if (this.x - row >= 0 && this.y + row < 8) {
        if (board.isPieceOnSquare(this.x - row, this.y + row) == null) {
          this.addLegalMove(this.x - row, this.y + row);
        } else if (board.isPieceOnSquare(this.x - row, this.y + row).isWhite != this.isWhite) {
          this.addLegalMove(this.x - row, this.y + row);
          break;
        } else if (board.isPieceOnSquare(this.x - row, this.y + row).isWhite == this.isWhite) {
          break;
        }
      }
    }

    for (int row = 1; row < 8; row++) {
      if (this.x - row >= 0 && this.y - row >= 0) {
        if (board.isPieceOnSquare(this.x - row, this.y - row) == null) {
          this.addLegalMove(this.x - row, this.y - row);
        } else if (board.isPieceOnSquare(this.x - row, this.y - row).isWhite != this.isWhite) {
          this.addLegalMove(this.x - row, this.y - row);
          break;
        } else if (board.isPieceOnSquare(this.x - row, this.y - row).isWhite == this.isWhite) {
          break;
        }
      }
    }

  }
}
