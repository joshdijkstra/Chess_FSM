package com.chess_fsm.chess.game.gameLogic.Board;

import java.util.Arrays;

import com.chess_fsm.chess.game.gameLogic.Piece;
import com.chess_fsm.chess.game.gameLogic.Square;
import com.chess_fsm.chess.game.gameLogic.Pieces.Bishop;
import com.chess_fsm.chess.game.gameLogic.Pieces.King;
import com.chess_fsm.chess.game.gameLogic.Pieces.Knight;
import com.chess_fsm.chess.game.gameLogic.Pieces.Pawn;
import com.chess_fsm.chess.game.gameLogic.Pieces.Queen;
import com.chess_fsm.chess.game.gameLogic.Pieces.Rook;

public class Board {
    @Override
  public String toString() {
    return "Board [squares=" + Arrays.toString(squares) + "]";
  }

    private Square[][] squares;
  
    public Board() {
      squares = new Square[8][8];
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          squares[i][j] = new Square(i, j);
        }
      }
    }
  
    public Square getSquare(int x, int y) {
      return squares[x][y];
    }

    public void initPieces(String fen){
      // Split the FEN string into its different parts
    String[] parts = fen.split(" ");
    String[] rows = parts[0].split("/");

    // Iterate through each row and set the pieces on the board
    for (int y = 7; y >= 0; y--) {
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
  
  