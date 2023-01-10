package com.chess_fsm.chess.game.gameLogic.Pieces;

import com.chess_fsm.chess.game.gameLogic.Board.Board;

public class King extends Piece {

  public King(int x, int y, boolean isWhite) {
    super(x, y, isWhite, PieceType.KING);
  }

  public void getLegalMoves(Board board) {
    this.clearLegalMoves();
    for (int x = -1; x < 2; x++) {
      for (int y = -1; y < 2; y++) {
        if (x != 0 || y != 0) {
          if (this.x + x >= 0 && this.x + x < 8 && this.y + y < 8 && this.y + y >= 0) {
            if (board.isPieceOnSquare(this.x + x, this.y + y) != null
                && board.isPieceOnSquare(this.x + x, this.y + y).isWhite != this.isWhite) {
              this.addLegalMove(this.x + x, this.y + y);
            } else if (board.isPieceOnSquare(this.x + x, this.y + y) == null) {
              this.addLegalMove(this.x + x, this.y + y);
            }
          }
        }
      }
    }
  }
}
