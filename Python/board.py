import pygame
from  pieces import *

class Board():
    def __init__(self,fen):
        self.fen = fen
        self.board =  self.init_pieces()
        #for rank in self.coords:
    #        print(rank)


    def init_pieces(self):
        chessBoard = [["-"] * 8 for i in range(8)]
        rank = 0
        ind = 0
        char = self.fen.split(" ")[0]
        for x in range(len(char)):
            if (char[x] == "/"):
                rank += 1
                ind = 0
            else:
                if (char[x].isdigit()):
                    ind += int(char[x])
                else:
                    chessBoard[rank][ind] = char[x]
                    ind +=1
        return chessBoard

    def load_board(self,size):
        boards = []
        boardRects = []
        for x in range(4):
            for y in range(4):
                board1 = pygame.image.load('ChessPieces/board2.png')
                ballrect = board1.get_rect(center=(x*120+30,y*120+30))
                board2 = pygame.image.load('ChessPieces/board1.png')
                ballrect2 = board2.get_rect(center=(x*120+90, y*120+30))

                board3 = pygame.image.load('ChessPieces/board2.png')
                ballrect3 = board1.get_rect(center=(x*120+90,y*120+90))
                board4 = pygame.image.load('ChessPieces/board1.png')
                ballrect4 = board2.get_rect(center=(x*120+30, y*120+90))

                boards.append(board1)
                boards.append(board2)
                boardRects.append(ballrect)
                boardRects.append(ballrect2)
                boards.append(board3)
                boards.append(board4)
                boardRects.append(ballrect3)
                boardRects.append(ballrect4)
        font = pygame.font.Font('freesansbold.ttf', 12)
        green = (0, 255, 0)
        blue = (0, 0, 128)
        for x in range(0,8):
            for y in range(0,8):
                square = square_name([x,y])
                coords = coord_converter([x,y])
                text = font.render(square, True, blue,)
                textRect = text.get_rect()
                textRect.center = (coords[0]-size[0]/24, coords[1]-size[1]/24)
                boards.append(text)
                boardRects.append(textRect)
        return boards , boardRects

    def load_pieces(self):
        white_pieces = []
        black_pieces = []
        for y in range(8):
            for x in range(8):
                if self.board[x][y] == "p":
                    black_pieces.append(Pawn(False,[x,y]))
                elif self.board[x][y] == "r":
                    black_pieces.append(Rook(False,[x,y]))
                elif self.board[x][y] == "n":
                    black_pieces.append(Knight(False,[x,y]))
                elif self.board[x][y] == "b":
                    black_pieces.append(Bishop(False,[x,y]))
                elif self.board[x][y] == "k":
                    black_pieces.append(King(False,[x,y]))
                elif self.board[x][y] == "q":
                    black_pieces.append(Queen(False,[x,y]))
                #### Light square pieces
                if self.board[x][y] == "P":
                    white_pieces.append(Pawn(True,[x,y]))
                elif self.board[x][y] == "R":
                    white_pieces.append(Rook(True,[x,y]))
                elif self.board[x][y] == "N":
                    white_pieces.append(Knight(True,[x,y]))
                elif self.board[x][y] == "B":
                    white_pieces.append(Bishop(True,[x,y]))
                elif self.board[x][y] == "K":
                    white_pieces.append(King(True,[x,y]))
                elif self.board[x][y] == "Q":
                    white_pieces.append(Queen(True,[x,y]))
        return white_pieces , black_pieces
