//Jonah Banks
//This stores the constructor for my piece, the jumping bishop
//This piece can move diagonally up/down, left/right like a regular bishop, but it can also jump over as many pieces of any color as necessary
package com.example;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class HopShop extends Piece {
    public HopShop(boolean color,String img_file) {
        super(color, img_file);
    }
    
    

    
    //precondition: g and currentSquare must be on-null valid objects.
    //postcondition: the image stored in the img property of this object is drawn to the screen.
    
    
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.
    //Precondition: board[][] and start are not null
    //Postcondition: returns an arraylist of squares the piece can move into 
    @Override
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
    	ArrayList<Square> legals=new ArrayList<>();
        int currRow=start.getRow();
        int currCol=start.getCol();
        //moving diagonally downwards
        int col=currCol+1;
        for(int row=currRow+1;row<8&&col<8; row++){
            legals.add(board[row][col]);
            col++;
        }
        col=currCol-1;
        //moving diagonally upwards
        for(int row2=currRow-1;row2>=0&&col>=0;row2--){
            legals.add(board[row2][col]);
            col--;
        }
        //moving down a row, moving up a column
        col=currCol-1;
        for(int row3=currRow+1;row3<8&&col>=0; row3++){
            legals.add(board[row3][col]);
            col--;
        }
        col=currCol+1;
        //moving up a row, moving down a column
        for(int row4=currRow-1; row4>=0&&col<8; row4--){
            legals.add(board[row4][col]);
            col++;
        }
        for(int i=0;i<legals.size();i++){
            if(legals.get(i).isOccupied()&&legals.get(i).getOccupyingPiece().getColor()==super.getColor()){
                legals.remove(i);
                i--;
            }
        }
        return legals;
    }
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.
    //Precondition: called from a piece object; b and start are not null.
    //Postcondition: returns an array con
    @Override
    public String toString(){
        return super.toString()+" Hopshop";
    }
    @Override
    public ArrayList<Square> getLegalMoves(Board b, Square start){
        ArrayList<Square> legals=new ArrayList<>();
        Square[][]  squares=b.getSquareArray();
        int currRow=start.getRow();
        int currCol=start.getCol();
        //moving diagonally downwards
        int col=currCol+1;
        for(int row=currRow+1;row<8&&col<8; row++){
            legals.add(squares[row][col]);
            col++;
        }
        col=currCol-1;
        //moving diagonally upwards
        for(int row2=currRow-1;row2>=0&&col>=0;row2--){
            legals.add(squares[row2][col]);
            col--;
        }
        //moving down a row, moving up a column
        col=currCol-1;
        for(int row3=currRow+1;row3<8&&col>=0; row3++){
            legals.add(squares[row3][col]);
            col--;
        }
        col=currCol+1;
        //moving up a row, moving down a column
        for(int row4=currRow-1; row4>=0&&col<8; row4--){
            legals.add(squares[row4][col]);
            col++;
        }
        //Remove any squares where piece is same color
        for(int i=0;i<legals.size();i++){
            if(legals.get(i).isOccupied()&&legals.get(i).getOccupyingPiece().getColor()==super.getColor()){
                legals.remove(i);
                i--;
            }
        }
        return legals;
    }
}