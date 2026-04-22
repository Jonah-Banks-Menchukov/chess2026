//Filip Milicevic
//Pawn2
//This specific pawn acts like pawns usually do but they can only eliminate other piece 2 diagonal instead of 1.

package com.example;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Pawn2 extends Piece {
    
   

    public Pawn2(boolean isWhite, String img_file) {

       super(isWhite, img_file);
    }

   


    // TO BE IMPLEMENTED!
    // return a list of every square that is "controlled" by this piece. A square is
    // controlled
    // if the piece capture into it legally.

    // Precondition: The board and starting square are valid and inside the board
    // limits.
    // Postcondition: Returns a list of all squares controlled by the piece from the
    // starting square.
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {

        ArrayList<Square> controlled = new ArrayList<>();
        int row = start.getRow();
        int col = start.getCol();

        if (this.getColor()) { // White Logic
            // Controls ONLY the diagonal "jump" squares (row + 2, col +/- 2)
            if (row + 2 < 8) {
                if (col - 2 >= 0) controlled.add(board[row + 2][col - 2]);
                if (col + 2 < 8)  controlled.add(board[row + 2][col + 2]);
            }
        } else { // Black Logic
            // Controls ONLY the diagonal "jump" squares (row - 2, col +/- 2)
            if (row - 2 >= 0) {
                if (col - 2 >= 0) controlled.add(board[row - 2][col - 2]);
                if (col + 2 < 8)  controlled.add(board[row - 2][col + 2]);
            }
        }
        return controlled;
    }
    // TO BE IMPLEMENTED!
    // implement the move function here
    // it's up to you how the piece moves, but at the very least the rules should be
    // logical and it should never move off the board!
    // returns an arraylist of squares which are legal to move to
    // please note that your piece must have some sort of logic. Just being able to
    // move to every square on the board is not
    // going to score any points.

    // Precondition: The board and starting square are valid and within the board’s
    // limits.
    // Postcondition: A list of possible legal moves from the starting square is
    // returned.
    public ArrayList<Square> getLegalMoves(Board b, Square start) {
        ArrayList<Square> moves = new ArrayList<>();
        if (this.getColor() == true) {
            if (start.getRow() < 7) {

                Square up = b.getSquareArray()[start.getRow() + 1][start.getCol()];
                moves.add(up);

                // check diagonal left
                if (start.getCol()-2 >= 0 && start.getRow()+2<8 && b.getSquareArray()[start.getRow() + 2][start.getCol() - 2].isOccupied()
                        && b.getSquareArray()[start.getRow() + 2][start.getCol() - 2].getOccupyingPiece()
                                .getColor() != this.getColor()) {
                    Square downLeft = b.getSquareArray()[start.getRow() + 2][start.getCol() - 2];
                    moves.add(downLeft);
                }
                // check diagonal right

                if (start.getCol()+2<8 && start.getRow()+2 <8 && b.getSquareArray()[start.getRow() + 2][start.getCol() + 2].isOccupied()
                        && b.getSquareArray()[start.getRow() + 2][start.getCol() + 2].getOccupyingPiece()
                                .getColor() != this.getColor()) {
                    Square downRight = b.getSquareArray()[start.getRow() + 2][start.getCol() + 2];
                    moves.add(downRight);
                }

            }
        } else {
            if (start.getRow() > 0) {
                Square down = b.getSquareArray()[start.getRow() - 1][start.getCol()];
                if (!down.isOccupied()) {
                    moves.add(down);
                }

                // check diagonal left (moving DOWN and LEFT)
                if (start.getCol() - 2 >= 0 && start.getRow() - 2 >= 0) {
                    Square diagLeft = b.getSquareArray()[start.getRow() - 2][start.getCol() - 2];
                    if (diagLeft.isOccupied() && diagLeft.getOccupyingPiece().getColor() != this.getColor()) {
                        moves.add(diagLeft);
                    }
                }

                // check diagonal right (moving DOWN and RIGHT)
                if (start.getCol() + 2 < 8 && start.getRow() - 2 >= 0) {
                    Square diagRight = b.getSquareArray()[start.getRow() - 2][start.getCol() + 2];
                    if (diagRight.isOccupied() && diagRight.getOccupyingPiece().getColor() != this.getColor()) {
                        moves.add(diagRight);
                    }
                }
            }
        }
        return moves;
    }
}
