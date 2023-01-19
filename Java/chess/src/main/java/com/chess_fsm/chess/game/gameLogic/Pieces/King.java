package com.chess_fsm.chess.game.gameLogic.Pieces;

import com.chess_fsm.chess.game.dto.Move;
import com.chess_fsm.chess.game.gameLogic.Board.Board;
import static com.chess_fsm.chess.game.gameState.Decoders.*;

import lombok.Data;

@Data
public class King extends Piece {
  public boolean inCheck = false;
  public boolean hasMoved = false;

  public King(int x, int y, boolean isWhite) {
    super(x, y, isWhite, PieceType.KING);
  }

  public void getLegalMoves(Board board) {
    this.clearLegalMoves();
    this.clearDefenders();
    for (int x = -1; x < 2; x++) {
      for (int y = -1; y < 2; y++) {
        if (x != 0 || y != 0) {
          if (this.x + x >= 0 && this.x + x < 8 && this.y + y < 8 && this.y + y >= 0) {
            if (board.isPieceOnSquare(this.x + x, this.y + y) != null
                && board.isPieceOnSquare(this.x + x, this.y + y).isWhite != this.isWhite) {
              this.addLegalMove(this.x + x, this.y + y, board);
            } else if (board.isPieceOnSquare(this.x + x, this.y + y) != null
                && board.isPieceOnSquare(this.x + x, this.y + y).isWhite == this.isWhite) {
              this.addDefends(this.x + x, this.y + y);
            } else if (board.isPieceOnSquare(this.x + x, this.y + y) == null) {
              this.addLegalMove(this.x + x, this.y + y, board);
            }
          }
        }
      }
    }
    this.canCastle(board);
  }

  public void canCastle(Board board) {
    int pos = this.isWhite ? 0 : 7;
    Piece kRook = board.getSquare(0, pos).getPiece();
    Piece qRook = board.getSquare(7, pos).getPiece();
    if (!this.inCheck && !this.hasMoved) {
      if (board.getSquare(1, pos).getPiece() == null && board.getSquare(2,
          pos).getPiece() == null
          && board.getSquare(3, pos).getPiece() == null && kRook != null &&
          !kRook.getHasMoved() && !board.isSquareAttacked(2, pos, this.isWhite)
          && !board.isSquareAttacked(3, pos, this.isWhite)) {
        this.addLegalMove(2, pos, board);
      }
      if (board.getSquare(5, pos).getPiece() == null && board.getSquare(6,
          pos).getPiece() == null
          && qRook != null &&
          !qRook.getHasMoved()) {
        this.addLegalMove(6, pos, board);
      }
    }

  }

  public void addLegalMove(int row, int col, Board board) {
    int[] list = { row, col };
    if (!board.isSquareAttacked(row, col, this.isWhite)) {
      this.legalMoves.add(list);
      board.addLegalMove(moveEncoder(new Move(new int[] { this.x, this.y }, list)));
    }
  }
}
