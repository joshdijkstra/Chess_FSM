package com.chess_fsm.chess.game.gameLogic.Pieces;

import com.chess_fsm.chess.game.gameLogic.Board.Board;

import lombok.Data;

@Data
public class Rook extends Piece {
  public boolean hasMoved = false;

  public Rook(int x, int y, boolean isWhite) {
    super(x, y, isWhite, PieceType.ROOK);
  }

  public void pseudoMoveGenerator(Board board) {
    this.clearLegalMoves();
    this.clearDefenders();

    for (int com : new int[] { 1, 0 }) {
      for (int inc : new int[] { 1, -1 }) {
        for (int row = 1; row < 8; row++) {
          if (this.x + (row * (1 - com) * inc) < 8 && this.x + (row * (1 - com) * inc) >= 0
              && this.y + (row * com) * inc >= 0
              && this.y + (row * com) * inc < 8) {
            if (board.isPieceOnSquare(this.x + (row * (1 - com) * inc), this.y + (row * com) * inc) == null) {
              this.addPseudoMove(this.x + (row * (1 - com) * inc), this.y + (row * com) * inc, board);
            } else if (board.isPieceOnSquare(this.x + (row * (1 - com) * inc),
                this.y + (row * com) * inc).isWhite != this.isWhite) {
              this.addPseudoMove(this.x + (row * (1 - com) * inc), this.y + (row * com) * inc, board);
              break;
            } else if (board.isPieceOnSquare(this.x + (row * (1 - com) * inc),
                this.y + (row * com) * inc).isWhite == this.isWhite) {
              this.addDefends(this.x + (row * (1 - com) * inc), this.y + (row * com) * inc);
              break;
            }
          }
        }
      }

    }
  }
}
