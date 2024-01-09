package com.chess_fsm.chess.game.gameLogic.Pieces;

import com.chess_fsm.chess.game.gameLogic.Board.Board;

public class Knight extends Piece {

  public Knight(int x, int y, boolean isWhite) {
    super(x, y, isWhite, PieceType.KNIGHT);
  }

  public void pseudoMoveGenerator(Board board) {
    this.clearMoves();

    int[] permutations = { -2, -1, 1, 2 };
    int[] ones = { -1, 1 };
    int[] twos = { -2, 2 };
    for (int perm : permutations) {
      for (int one : (Math.pow(perm, 2) == 4 ? ones : twos)) {
        if (this.x + perm >= 0 && this.x + perm < 8 && this.y + one >= 0 && this.y + one < 8) {
          if (board.isPieceOnSquare(this.x + perm, this.y + one) == null) {
            this.addPseudoMove(this.x + perm, this.y + one, board);
          }
          if (board.isPieceOnSquare(this.x + perm, this.y + one) != null
              && board.isPieceOnSquare(this.x + perm, this.y + one).isWhite != this.isWhite) {
            this.addPseudoMove(this.x + perm, this.y + one, board);
          }
          if (board.isPieceOnSquare(this.x + perm, this.y + one) != null
              && board.isPieceOnSquare(this.x + perm, this.y + one).isWhite == this.isWhite) {
            this.addDefends(this.x + perm, this.y + one);
          }
        }
      }
    }
  }
}
