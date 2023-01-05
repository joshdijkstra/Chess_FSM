import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { take } from 'rxjs';
import { Pieces } from '../model/Piece';
import { Board } from '../model/Board';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css'],
})
export class BoardComponent {
  board: any;
  constructor(private http: HttpClient) {}

  axisVertical = ['1', '2', '3', '4', '5', '6', '7', '8'];
  axisHorizontal = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'];
  url = 'api/v1/game';

  ngOnInit() {
    this.showBoard();
  }

  public getBoard = () => {
    let header = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.get(this.url, { headers: header });
  };

  public showBoard = () => {
    this.getBoard()
      .pipe(take(1))
      .subscribe({
        next: (res) => {
          this.diplayPieces(res);
        },
        error: () => {
          console.log('error');
        },
      });
  };

  public hasPieceOn = (x: number, y: number) => {
    if (this.board.squares[x][y].piece != null) {
      return true;
    } else {
      return false;
    }
  };

  public lookUpImage = (x: number, y: number) => {
    const colour = this.board.squares[x][y].piece.isWhite ? 'w' : 'b';
    switch (this.board.squares[x][y].piece.pieceType) {
      case Pieces.PAWN:
        return `assets/pawn_${colour}.png`;
      case Pieces.ROOK:
        return `assets/rook_${colour}.png`;
      case Pieces.KNIGHT:
        return `assets/knight_${colour}.png`;
      case Pieces.BISHOP:
        return `assets/bishop_${colour}.png`;
      case Pieces.KING:
        return `assets/king_${colour}.png`;
      case Pieces.QUEEN:
        return `assets/queen_${colour}.png`;
      default:
        return ``;
    }
  };

  public diplayPieces = (res: any) => {
    this.board = res;
    console.log(res);
    return res;
  };
}
