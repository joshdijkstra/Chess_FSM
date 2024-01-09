package com.chess_fsm.chess.game.gameLogic.Pieces;

import com.chess_fsm.chess.game.gameLogic.Board.Board;

public class Queen extends Piece {

  public Queen(int x, int y, boolean isWhite) {
    super(x, y, isWhite, PieceType.QUEEN);
  }

  public void pseudoMoveGenerator(Board board) {
    this.clearMoves();
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
    for (int com : new int[] { -1, 1 }) {
      for (int inc : new int[] { -1, 1 }) {
        for (int row = 1; row < 8; row++) {
          if (this.x + (row * inc) < 8 && this.y + (row * inc * com) < 8 && this.x + (row * inc) >= 0
              && this.y + (row * inc * com) >= 0) {
            if (board.isPieceOnSquare(this.x + row * inc, this.y + row * inc * com) == null) {
              this.addPseudoMove(this.x + row * inc, this.y + row * inc * com, board);
            } else if (board.isPieceOnSquare(this.x + row * inc, this.y + row * inc * com).isWhite != this.isWhite) {
              this.addPseudoMove(this.x + row * inc, this.y + row * inc * com, board);
              // this.xRayAttack(board, this.x + row, this.y + row);
              break;
            } else if (board.isPieceOnSquare(this.x + row * inc, this.y + row * inc * com).isWhite == this.isWhite) {
              this.addDefends(this.x + row * inc, this.y + row * inc * com);
              break;
            }
          }
        }
      }
    }
  }
}
