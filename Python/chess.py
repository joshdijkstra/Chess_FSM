import sys, pygame, math
from pieces import *
from board import *


pygame.init()

size = width, height = 480, 480
black = 0, 0, 0
starting_fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
fen2 = "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2"
test_fen = "8/8/8/8/8/8/3R1N2/4K3"
test_fen2 = "8/8/8/8/8/8/8/4K3"
chessBoard= Board(test_fen)
screen = pygame.display.set_mode(size)

boards , boardRects = chessBoard.load_board(size)
white_pieces , black_pieces = chessBoard.load_pieces()


while 1:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            sys.exit()
        elif event.type == pygame.MOUSEBUTTONDOWN:
            if event.button == 1:
                for rectangle in white_pieces:
                    if rectangle.rect.collidepoint(event.pos):
                        rectangle.rectangle_draging = True
                        for other_rectangles in white_pieces:
                            if other_rectangles.selected:
                                other_rectangles.selected = False
                        rectangle.selected = True
                        mouse_x, mouse_y = event.pos
                        offset_x = rectangle.rect.x - mouse_x
                        offset_y = rectangle.rect.y - mouse_y
        elif event.type == pygame.MOUSEBUTTONUP:
            if event.button == 1:
                for rectangle in white_pieces:
                    if rectangle.rectangle_draging:
                        mouse_x, mouse_y = event.pos
                        rectangle = rectangle.snap_to_grid(mouse_x,mouse_y,white_pieces,black_pieces)
                    rectangle.rectangle_draging = False
        elif event.type == pygame.MOUSEMOTION:
            for rectangle in white_pieces:
                if rectangle.rectangle_draging:
                    mouse_x, mouse_y = event.pos
                    rectangle.rect.x = mouse_x + offset_x
                    rectangle.rect.y = mouse_y + offset_y

    #screen.fill(black)
    for x in range(len(boards)):
        screen.blit(boards[x], boardRects[x])

    for piece in white_pieces:
        if piece.selected:
            image_legal , rects_legal = piece.load_legal_squares(white_pieces,black_pieces)
            for x in range(len(rects_legal)):
                screen.blit(image_legal,rects_legal[x])
        screen.blit(piece.image, piece.rect)

    for piece in black_pieces:
        screen.blit(piece.image, piece.rect)
    pygame.display.flip()
