package com.chess_fsm.chess.game.gameLogic.Pieces;

import com.chess_fsm.chess.game.gameLogic.Board.Board;
import com.chess_fsm.chess.game.gameLogic.Board.Square;

public class Pawn extends Piece {

  public Pawn(int x, int y, boolean isWhite) {
    super(x, y, isWhite, PieceType.PAWN);
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

  public void getLegalMoves(Board board) {
    int increment = this.isWhite ? 1 : -1;
    int boardPos = this.isWhite ? 1 : 6;
    if (this.y + increment < 8 && this.y + increment >= 0) {
      if (board.isPieceOnSquare(this.x, this.y + increment) == null) {
        this.addLegalMove(this.x, this.y + increment);
      }
      if (board.isPieceOnSquare(this.x, this.y + (increment * 2)) == null && this.y == boardPos) {
        this.addLegalMove(this.x, this.y + (increment * 2));
      }
      if (this.x + 1 < 8) {
        if (board.isPieceOnSquare(this.x + 1, this.y + increment) != null
            && board.isPieceOnSquare(this.x + 1, this.y + increment).isWhite != this.isWhite) {
          this.addLegalMove(this.x + 1, this.y + increment);
        }
      }
      if (this.x - 1 >= 0) {
        if (board.isPieceOnSquare(this.x - 1, this.y + increment) != null
            && board.isPieceOnSquare(this.x + 1, this.y + increment).isWhite != this.isWhite) {
          this.addLegalMove(this.x - 1, this.y + increment);
        }
      }
    }
  }
}
