package com.example;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;



import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class AprenticeRook extends Piece {
    
    
    public AprenticeRook (boolean isWhite, String img_file) {
        super(isWhite,img_file);
    }
    
    
    // TO BE IMPLEMENTED!
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.
    //pre condition the piece is on  the square start and the board is made
    //post condition gives an arraylist of square that are controled by the piece.
    @Override
    public ArrayList<Square> getControlledSquares(Square[][] board, Square currentSquare) {
     
     ArrayList<Square> Cmoves= new ArrayList<>();
     
     if (currentSquare.getCol()<7){
            Square cright= board[currentSquare.getRow()][currentSquare.getCol()+1];
           
            Cmoves.add(cright);
         
        }
        
        if (currentSquare.getCol()>=1){
            Square cLeft= board[currentSquare.getRow()][currentSquare.getCol()-1];
           
            Cmoves.add(cLeft);
         
        }

        if (currentSquare.getRow()<7){
            Square cUp= board[currentSquare.getRow()+1][currentSquare.getCol()];
            
            Cmoves.add(cUp);
         
        }

        if (currentSquare.getRow()>=1){
            Square cDown= board[currentSquare.getRow()-1][currentSquare.getCol()];

            Cmoves.add(cDown);
         
        }
        return Cmoves;
    }
    
    @Override
    public String toString() {
      return "A " + super.toString() + " AprenticeRook";
    }

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.
    //pre condition the board is made and the pice is on the square start
    //post condition gives an arraylist of squares that are legal to move to.
    public ArrayList<Square> getLegalMoves(Board b, Square currentSquare){
    	 
         ArrayList<Square> moves= new ArrayList<>();
        
        if (currentSquare.getCol()<7){
            Square right= b.getSquareArray()[currentSquare.getRow()][currentSquare.getCol()+1];
            if (!right.isOccupied() || right.getOccupyingPiece().getColor()!=this.getColor()){
            moves.add(right);
         }
        }
        
        if (currentSquare.getCol()>=1){
            Square Left= b.getSquareArray()[currentSquare.getRow()][currentSquare.getCol()-1];
            if (!Left.isOccupied() || Left.getOccupyingPiece().getColor()!=this.getColor()){
            moves.add(Left);
         }
        }

        if (currentSquare.getRow()<7){
            Square Up= b.getSquareArray()[currentSquare.getRow()+1][currentSquare.getCol()];
            if (!Up.isOccupied() || Up.getOccupyingPiece().getColor()!=this.getColor()){
            moves.add(Up);
         }
        }

        if (currentSquare.getRow()>=1){
            Square Down= b.getSquareArray()[currentSquare.getRow()-1][currentSquare.getCol()];
            if (!Down.isOccupied() || Down.getOccupyingPiece().getColor()!=this.getColor()){
            moves.add(Down);
         }
        }
        
        
        
         
        
    
        
    
        return moves;
}

}