package com.chess_fsm.chess.game.gameLogic.Pieces;

import com.chess_fsm.chess.game.gameLogic.Board.Board;

public class Queen extends Piece {

  public Queen(int x, int y, boolean isWhite) {
    super(x, y, isWhite, PieceType.QUEEN);
  }

  public void getLegalMoves(Board board) {
    this.clearLegalMoves();
    this.clearDefenders();
    for (int row = this.x + 1; row < 8; row++) {
      if (board.isPieceOnSquare(row, this.y) == null) {
        this.addLegalMove(row, this.y);
      } else if (board.isPieceOnSquare(row, this.y).isWhite != this.isWhite) {
        this.addLegalMove(row, this.y);
        break;
      } else if (board.isPieceOnSquare(row, this.y).isWhite == this.isWhite) {
        this.addDefends(row, this.y);
        break;
      }
    }

    for (int row = this.x - 1; row >= 0; row--) {
      if (board.isPieceOnSquare(row, this.y) == null) {
        this.addLegalMove(row, this.y);
      } else if (board.isPieceOnSquare(row, this.y).isWhite != this.isWhite) {
        this.addLegalMove(row, this.y);
        break;
      } else if (board.isPieceOnSquare(row, this.y).isWhite == this.isWhite) {
        this.addDefends(row, this.y);
        break;
      }
    }

    for (int col = this.y + 1; col < 8; col++) {
      if (board.isPieceOnSquare(this.x, col) == null) {
        this.addLegalMove(this.x, col);
      } else if (board.isPieceOnSquare(this.x, col).isWhite != this.isWhite) {
        this.addLegalMove(this.x, col);
        break;
      } else if (board.isPieceOnSquare(this.x, col).isWhite == this.isWhite) {
        this.addDefends(this.x, col);

        break;
      }
    }

    for (int col = this.y - 1; col >= 0; col--) {
      if (board.isPieceOnSquare(this.x, col) == null) {
        this.addLegalMove(this.x, col);
      } else if (board.isPieceOnSquare(this.x, col).isWhite != this.isWhite) {
        this.addLegalMove(this.x, col);
        break;
      } else if (board.isPieceOnSquare(this.x, col).isWhite == this.isWhite) {
        this.addDefends(this.x, col);

        break;
      }
    }
    for (int row = 1; row < 8; row++) {
      if (this.x + row < 8 && this.y + row < 8) {
        if (board.isPieceOnSquare(this.x + row, this.y + row) == null) {
          this.addLegalMove(this.x + row, this.y + row);
        } else if (board.isPieceOnSquare(this.x + row, this.y + row).isWhite != this.isWhite) {
          this.addLegalMove(this.x + row, this.y + row);
          break;
        } else if (board.isPieceOnSquare(this.x + row, this.y + row).isWhite == this.isWhite) {
          this.addDefends(this.x + row, this.y + row);
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
          this.addDefends(this.x + row, this.y - row);
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
          this.addDefends(this.x - row, this.y + row);
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
          this.addDefends(this.x - row, this.y - row);
          break;
        }
      }
    }
  }
}
