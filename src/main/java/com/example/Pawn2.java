//Jonah Banks
//Pawn2
/*This pawn can move backwards and forwards, and 
can capture diagonally in all four directions. 
It can become a queen if it reaches the end 
of the board. It cannot move off the board, and it cannot move 
into a square occupied by a piece of the same color. 
*/
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
        // Check all four diagonal directions for captures
        if (row > 0 && col > 0) {
            controlled.add(board[row - 1][col - 1]); // Diagonal up-left
        }
        if (row > 0 && col < 7) {
            controlled.add(board[row - 1][col + 1]); // Diagonal up-right
        }
        if (row < 7 && col > 0) {
            controlled.add(board[row + 1][col - 1]); // Diagonal down-left
        }
        if (row < 7 && col < 7) {
            controlled.add(board[row + 1][col + 1]); // Diagonal down-right
        }
        //Remove any squares that are occupied by pieces of the same color
        for (int i = controlled.size() - 1; i >= 0; i--) {
            Square sq = controlled.get(i);
            if (sq.isOccupied() && sq.getOccupyingPiece().getColor() == this.getColor()) {
                controlled.remove(i);
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
        Square[][] board = b.getSquareArray();
        int row = start.getRow();
        int col = start.getCol();
        // Check forward move
        if(row+1<8){
            if(board[row+1][col].getOccupyingPiece()==null){
                moves.add(board[row+1][col]);
            }
        }
        // Check backward move
        if(row-1>=0){
            if(board[row-1][col].getOccupyingPiece()==null){
                moves.add(board[row-1][col]);
            }
        }
        return moves;
    }
}
