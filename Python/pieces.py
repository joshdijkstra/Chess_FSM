import pygame

def coord_converter(pos):
    return [30+pos[1]*60,30+pos[0]*60]

def square_name(pos):
    letters = ["A","B","C","D","E","F","G","H"]
    return str(letters[pos[1]] + str(8-pos[0]))

class Pieces():
    def __init__(self,team,pos):
        self.white = team
        self.pos = pos
        self.coords = coord_converter(pos)
        self.rectangle_draging = False
        self.selected = False
        self.hasMoved = False

    def load_image(self,piece):
        if self.white:
            file_name = "ChessPieces/Chess_" + piece + "lt60.png"
        else:
            file_name = "ChessPieces/Chess_" + piece + "dt60.png"
        image = pygame.image.load(file_name)
        rect = pygame.Rect(image.get_rect(center=(self.coords[0],self.coords[1])))
        return image , rect

    def move_image(self,x,y):
        self.pos = [y,x]
        self.coords =  [y*60,x*60]
        self.rect.x = self.coords[1]
        self.rect.y = self.coords[0]



    def load_legal_squares(self,wt,bl):
        rects = []
        image = pygame.image.load("ChessPieces/board3.png")
        moves = self.find_moves()
        squares = self.find_legal(moves,wt,bl)
        for x in range(len(squares)):
            coord = coord_converter(squares[x])
            rect = pygame.Rect(image.get_rect(center=(coord[0],coord[1])))
            rects.append(rect)
        return image , rects

    def snap_to_grid(self,mx,my,wt,bl):
        x = mx // 60
        y = my // 60
        print("Mouse Position: ", square_name([y,x]))
        moves = self.find_moves()
        legal_moves = self.find_legal(moves,wt,bl)
        if [y,x] in legal_moves:
            self.move(x,y)
            self.hasMoved = True
        else:
            print("Illegal Move")
            self.move(self.pos[1],self.pos[0])
        return self

    def find_legal(self,moves,white_pieces,black_pieces):
        legal_moves = []
        legal_squares = []
        black_loc = []
        white_loc = []
        true_moves = []
        for move in moves:
            if move[0] >= 0 and move[0] < 8:
                if move[1] >= 0 and move[1] < 8:
                    legal_moves.append(move)
        for piece in white_pieces:
            white_loc.append(piece.pos)
        for piece in black_pieces:
            black_loc.append(piece.pos)
        for x in range(len(legal_moves)):
            legal_squares.append(square_name(legal_moves[x]))
        #print("Current Square: ", square_name(self.pos))
        #print("Legal Squares: " , legal_squares)
        for move in legal_moves:
            if self.white:
                if move in white_loc:
                    legal_moves.remove(move)
        legal_moves = self.blocked(white_loc,black_loc,legal_moves)
        return legal_moves

    def move(self,x,y):
        self.move_image(x,y)

class Pawn(Pieces):
    def __init__(self,team,pos):
        Pieces.__init__(self,team,pos)
        self.image , self.rect = self.load_image("p")

    def find_moves(self):
        moves = []
        if self.white:
            return moves

class King(Pieces):
    def __init__(self,team,pos):
        Pieces.__init__(self,team,pos)
        self.image , self.rect = self.load_image("k")

    def find_moves(self):
        moves = []
        moves.append([self.pos[0] - 1, self.pos[1] + 1])
        moves.append([self.pos[0] - 1, self.pos[1]])
        moves.append([self.pos[0] - 1, self.pos[1] - 1])
        moves.append([self.pos[0],     self.pos[1] + 1])
        moves.append([self.pos[0],     self.pos[1] - 1])
        moves.append([self.pos[0] + 1, self.pos[1] + 1])
        moves.append([self.pos[0] + 1, self.pos[1]])
        moves.append([self.pos[0] + 1, self.pos[1] - 1])
        return moves

class Queen(Pieces):
    def __init__(self,team,pos):
        Pieces.__init__(self,team,pos)
        self.image , self.rect = self.load_image("q")

    def find_moves(self):
        moves = []
        for x in range(1,8):
            moves.append([self.pos[0] + x, self.pos[1] + x ])
            moves.append([self.pos[0] - x, self.pos[1] + x])
            moves.append([self.pos[0] + x, self.pos[1] - x])
            moves.append([self.pos[0] - x, self.pos[1] - x])
            moves.append([self.pos[0] + x, self.pos[1]])
            moves.append([self.pos[0] - x, self.pos[1]])
            moves.append([self.pos[0], self.pos[1] + x])
            moves.append([self.pos[0], self.pos[1] - x])
        return moves

class Knight(Pieces):
    def __init__(self,team,pos):
        Pieces.__init__(self,team,pos)
        self.image , self.rect = self.load_image("n")

    def find_moves(self):
        moves = []
        moves.append([self.pos[0]-2 , self.pos[1]+1])
        moves.append([self.pos[0]-1 , self.pos[1]+2])
        moves.append([self.pos[0]+1 , self.pos[1]+2])
        moves.append([self.pos[0]+2 , self.pos[1]+1])
        moves.append([self.pos[0]+2 , self.pos[1]-1])
        moves.append([self.pos[0]+1 , self.pos[1]-2])
        moves.append([self.pos[0]-1 , self.pos[1]-2])
        moves.append([self.pos[0]-2 , self.pos[1]-1])
        return moves

class Bishop(Pieces):
    def __init__(self,team,pos):
        Pieces.__init__(self,team,pos)
        self.image , self.rect = self.load_image("b")

    def find_moves(self):
        moves = []
        for x in range(1,8):
            moves.append([self.pos[0] + x, self.pos[1] + x ])
            moves.append([self.pos[0] - x, self.pos[1] + x])
            moves.append([self.pos[0] + x, self.pos[1] - x])
            moves.append([self.pos[0] - x, self.pos[1] - x])
        return moves

class Rook(Pieces):
    def __init__(self,team,pos):
        Pieces.__init__(self,team,pos)
        self.image , self.rect = self.load_image("r")

    def find_moves(self):
        moves = []
        for x in range(1,8):
            moves.append([self.pos[0] + x, self.pos[1] ])
            moves.append([self.pos[0] - x, self.pos[1] ])
            moves.append([self.pos[0] , self.pos[1] + x])
            moves.append([self.pos[0] , self.pos[1] - x])
        return moves

    def blocked(self,whites,blacks,legal):
        #print(legal)
        blocked = []
        for x in range(len(whites)):
            if whites[x][0] == self.pos[0]:
                for y in range(len(legal)):
                    if (legal[y][1] < whites[x][1]):
                        blocked.append(square_name(legal[y]))
        print(blocked)
        return legal