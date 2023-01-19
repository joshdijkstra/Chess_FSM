package com.chess_fsm.chess.game.gameLogic.Pieces;

import com.chess_fsm.chess.game.gameLogic.Board.Board;

import lombok.Data;

@Data
public class Rook extends Piece {
  public boolean hasMoved = false;

  public Rook(int x, int y, boolean isWhite) {
    super(x, y, isWhite, PieceType.ROOK);
  }

  public void getLegalMoves(Board board) {
    this.clearLegalMoves();
    this.clearDefenders();

    for (int inc : new int[] { 1, -1 }) {
      for (int row = this.x + inc; row < 8 && row >= 0; row += inc) {
        if (board.isPieceOnSquare(row, this.y) == null) {
          this.addLegalMove(row, this.y, board);
        } else if (board.isPieceOnSquare(row, this.y).isWhite != this.isWhite) {
          this.addLegalMove(row, this.y, board);
          break;
        } else if (board.isPieceOnSquare(row, this.y).isWhite == this.isWhite) {
          this.addDefends(row, this.y);
          break;
        }
      }
    }

    for (int inc : new int[] { 1, -1 }) {
      for (int col = this.y + inc; col < 8 && col >= 0; col += inc) {
        if (board.isPieceOnSquare(this.x, col) == null) {
          this.addLegalMove(this.x, col, board);
        } else if (board.isPieceOnSquare(this.x, col).isWhite != this.isWhite) {
          this.addLegalMove(this.x, col, board);
          break;
        } else if (board.isPieceOnSquare(this.x, col).isWhite == this.isWhite) {
          this.addDefends(this.x, col);
          break;
        }
      }
    }
  }
}
