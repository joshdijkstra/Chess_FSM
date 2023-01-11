package com.chess_fsm.chess.game.gameLogic.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.chess_fsm.chess.game.gameLogic.Pieces.Bishop;
import com.chess_fsm.chess.game.gameLogic.Pieces.King;
import com.chess_fsm.chess.game.gameLogic.Pieces.Knight;
import com.chess_fsm.chess.game.gameLogic.Pieces.Pawn;
import com.chess_fsm.chess.game.gameLogic.Pieces.Piece;
import com.chess_fsm.chess.game.gameLogic.Pieces.PieceType;
import com.chess_fsm.chess.game.gameLogic.Pieces.Queen;
import com.chess_fsm.chess.game.gameLogic.Pieces.Rook;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Board {

  @JsonProperty("squares")
  private Square[][] squares;

  public Board() {
    squares = new Square[8][8];
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        squares[i][j] = new Square(i, j);
      }
    }
  }

  public List<Piece> getAllPieces() {
    List<Piece> pieces = new ArrayList<Piece>();
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (isPieceOnSquare(i, j) != null) {
          pieces.add(isPieceOnSquare(i, j));
        }
      }
    }
    return pieces;
  }

  public String toString() {
    return Arrays.deepToString(this.squares);
  }

  public Square getSquare(int x, int y) {
    return squares[x][y];
  }

  public void setSquare(int x, int y, Square square) {
    squares[x][y] = square;
  }

  public Piece isPieceOnSquare(int x, int y) {
    return getSquare(x, y).getPiece();
  }

  public boolean getPawnAttacks(Piece piece, int x, int y, boolean isWhite) {
    int increment = piece.isWhite ? 1 : -1;
    if (piece.x + 1 < 8) {
      if (piece.x + 1 == x && piece.y + increment == y) {
        return true;
      }
    }
    if (piece.x - 1 >= 0) {
      if (piece.x - 1 == x && piece.y + increment == y) {
        return true;
      }
    }
    return false;

  }

  public boolean isSquareAttacked(int x, int y, boolean isWhite) {
    List<Piece> pieces = this.getAllPieces();
    for (Piece piece : pieces) {
      for (int[] defend : piece.getDefends()) {
        if (piece.isWhite != isWhite && defend[0] == x && defend[1] == y) {
          return true;
        }
      }
      for (int[] legalMove : piece.getLegalMoves()) {
        if (piece.pieceType != PieceType.PAWN && piece.isWhite != isWhite && legalMove[0] == x && legalMove[1] == y) {
          return true;
        }
      }
      if (piece.pieceType == PieceType.PAWN && piece.isWhite != isWhite) {
        if (this.getPawnAttacks(piece, x, y, isWhite)) {
          return true;
        }
      }
    }
    return false;
  }

  public void initPieces(String fen) {
    // Split the FEN string into its different parts
    String[] parts = fen.split(" ");
    String[] rows = parts[0].split("/");

    // Iterate through each row and set the pieces on the board
    for (int y = 0; y <= 7; y++) {
      String row = rows[7 - y];
      int x = 0;
      for (int i = 0; i < row.length(); i++) {
        char c = row.charAt(i);
        if (c >= '1' && c <= '8') {
          // Advance the position by the number of empty squares
          x += c - '0';
        } else {
          // Create a new piece and place it on the board
          Piece piece = createPiece(c, x, y, Character.isUpperCase(c));
          this.getSquare(x, y).setPiece(piece);

          x++;
        }
      }
    }
  }

  private static Piece createPiece(char c, int x, int y, boolean isWhite) {
    switch (Character.toUpperCase(c)) {
      case 'P':
        return new Pawn(x, y, isWhite);
      case 'N':
        return new Knight(x, y, isWhite);
      case 'B':
        return new Bishop(x, y, isWhite);
      case 'R':
        return new Rook(x, y, isWhite);
      case 'Q':
        return new Queen(x, y, isWhite);
      case 'K':
        return new King(x, y, isWhite);
      default:
        throw new IllegalArgumentException("Invalid piece type: " + c);
    }
  }
}
