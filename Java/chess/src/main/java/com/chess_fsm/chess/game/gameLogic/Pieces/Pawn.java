package com.chess_fsm.chess.game.gameLogic.Pieces;

import com.chess_fsm.chess.game.gameLogic.Board.Board;

public class Pawn extends Piece {

  public Pawn(int x, int y, boolean isWhite) {
    super(x, y, isWhite, PieceType.PAWN);
  }

  public void pseudoMoveGenerator(Board board) {
    this.clearLegalMoves();

    int increment = this.isWhite ? 1 : -1;
    int boardPos = this.isWhite ? 1 : 6;
    if (this.y + increment < 8 && this.y + increment >= 0) {
      if (board.isPieceOnSquare(this.x, this.y + increment) == null) {
        this.addPseudoMove(this.x, this.y + increment, board);
      }
      if (this.y == boardPos && board.isPieceOnSquare(this.x, this.y + (increment * 2)) == null
          && board.isPieceOnSquare(this.x, this.y + increment) == null) {
        this.addPseudoMove(this.x, this.y + (increment * 2), board);
      }
      if (this.x + 1 < 8) {
        if (board.isPieceOnSquare(this.x + 1, this.y + increment) != null
            && board.isPieceOnSquare(this.x + 1, this.y + increment).isWhite != this.isWhite) {
          this.addPseudoMove(this.x + 1, this.y + increment, board);
        }
      }
      if (this.x - 1 >= 0) {
        if (board.isPieceOnSquare(this.x - 1, this.y + increment) != null
            && board.isPieceOnSquare(this.x - 1, this.y + increment).isWhite != this.isWhite) {
          this.addPseudoMove(this.x - 1, this.y + increment, board);
        }
      }
    }
  }
}
