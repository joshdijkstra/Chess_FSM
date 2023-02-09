package com.chess_fsm.chess.game.gameLogic.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.chess_fsm.chess.game.dto.moveDTO;
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
  private List<Piece> piecesAll;
  private List<Piece> whitePieces;
  private List<Piece> blackPieces;
  private Piece blackKing;
  private Piece whiteKing;
  private boolean whiteToMove = true;
  private boolean onlyKingMoves;
  private List<String> legalMoves;
  private List<int[]> captureMask;
  private List<int[]> pushMask;
  private boolean requiresMasks;

  public Board() {
    this.piecesAll = new ArrayList<Piece>();
    this.whitePieces = new ArrayList<Piece>();
    this.blackPieces = new ArrayList<Piece>();
    this.legalMoves = new ArrayList<String>();
    squares = new Square[8][8];
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        squares[i][j] = new Square(i, j);
      }
    }
  }

  public void getAllPieces() {
    List<Piece> aPieces = new ArrayList<Piece>();
    List<Piece> wPieces = new ArrayList<Piece>();
    List<Piece> bPieces = new ArrayList<Piece>();
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (isPieceOnSquare(i, j) != null) {
          Piece piece = isPieceOnSquare(i, j);
          aPieces.add(isPieceOnSquare(i, j));
          if (piece.isWhite) {
            wPieces.add(piece);
          } else {
            bPieces.add(piece);
          }
        }
      }
    }
    this.setPiecesAll(aPieces);
    this.setBlackPieces(bPieces);
    this.setWhitePieces(wPieces);
  }

  public String toString() {
    return Arrays.deepToString(this.squares);
  }

  // public void getAttackedSquares() {
  // for (int x = 0; x < 8; x++) {
  // for (int y = 0; y < 8; y++) {
  // getSquare(x, y).isAttacked(this.isSquareAttacked(x, y, false), false);
  // getSquare(x, y).isAttacked(this.isSquareAttacked(x, y, true), true);

  // }
  // }
  // }

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

  // public boolean isSquareAttacked(int x, int y, boolean isWhite) {
  // List<Piece> pieces = isWhite ? this.getBlackPieces() : this.getWhitePieces();
  // for (Piece piece : pieces) {
  // for (int[] defend : piece.getDefends()) {
  // if (piece.isWhite != isWhite && defend[0] == x && defend[1] == y) {
  // return true;
  // }
  // }
  // for (int[] legalMove : piece.getLegalMoves()) {
  // if (piece.pieceType != PieceType.PAWN && piece.isWhite != isWhite &&
  // legalMove[0] == x && legalMove[1] == y) {
  // return true;
  // }
  // }
  // if (piece.pieceType == PieceType.PAWN && piece.isWhite != isWhite) {
  // if (this.getPawnAttacks(piece, x, y, isWhite)) {
  // return true;
  // }
  // }
  // }
  // return false;
  // }

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

  private Piece createPiece(char c, int x, int y, boolean isWhite) {
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
        Piece king = new King(x, y, isWhite);
        if (isWhite) {
          this.setWhiteKing(king);
        } else {
          this.setBlackKing(king);
        }
        return king;
      default:
        throw new IllegalArgumentException("Invalid piece type: " + c);
    }
  }

  public void addLegalMove(moveDTO move) {
    this.legalMoves.add(move.getMove());
  }

  public void getCheckedMoves(boolean isWhite, BoardService boardService) {
    this.setCaptureMask(null);
    this.setPushMask(new ArrayList<int[]>());

    List<Piece> pieces = isWhite ? this.blackPieces : this.whitePieces;
    Piece king = isWhite ? this.whiteKing : this.blackKing;
    int numCheckers = 0;
    Piece checker = null;
    for (Piece piece : pieces) {
      for (int[] legalMove : piece.legalMoves) {
        if (legalMove[0] == king.x && legalMove[1] == king.y) {
          numCheckers++;
          checker = piece;
          king.setInCheck(true);
        }
      }
    }
    System.out.println(numCheckers);
    this.onlyKingMoves = false;
    if (numCheckers > 1) {
      this.onlyKingMoves = true;
      boardService.recalculateLegalMoves(this);
      this.setRequiresMasks(false);

      return;
    }
    if (numCheckers == 1) {
      // Find capture mask
      List<int[]> captureMask = new ArrayList<int[]>();
      captureMask.add(new int[] { checker.x, checker.y });
      this.setCaptureMask(captureMask);
      this.getPushMask(isWhite, checker, king);
      this.setRequiresMasks(true);
      boardService.recalculateLegalMoves(this);
      this.setRequiresMasks(false);

      return;
    }
    king.setInCheck(false);
    this.setRequiresMasks(false);

  }

  public void removeKing(boolean isWhite) {
    Piece king = isWhite ? this.getWhiteKing() : this.getBlackKing();
    this.setSquare(king.x, king.y, new Square(king.x, king.y));
  }

  public void returnKingToBoard(boolean isWhite) {
    Piece king = isWhite ? this.getWhiteKing() : this.getBlackKing();
    this.getSquare(king.x, king.y).setPiece(king);
  }

  public void getPushMask(boolean isWhite, Piece checker, Piece king) {
    List<int[]> pushMask = new ArrayList<int[]>();
    int xMin = Math.min(checker.x, king.x);
    int xMax = Math.max(checker.x, king.x);
    int yMin = Math.min(checker.y, king.y);
    int yMax = Math.max(checker.y, king.y);

    if (checker.x == king.x || checker.y == king.y) {
      // rook case
      for (int i = xMin + 1; i < xMax; i++) {
        pushMask.add(new int[] { i, checker.y });
      }
      for (int i = yMin + 1; i < yMax; i++) {
        pushMask.add(new int[] { checker.x, i });

      }
    } else if (Math.abs(checker.x - king.x) == Math.abs(checker.y - king.y)) {
      // bishop case
      for (int i = xMin + 1, j = yMin + 1; i < xMax; i++, j++) {
        pushMask.add(new int[] { i, j });
      }
    }
    this.setPushMask(pushMask);
  }
}
