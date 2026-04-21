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
//Kuang Miao
//MyCoolQueen
//Currently, it'll move like a queen; in the future, it'll move like a queen, and after killing 3 times, it'll turn into a pawn
//you will need to implement two functions in this file.
public class MyCoolQueen extends Piece {
   

    private int pieceTaken;
    public MyCoolQueen(boolean isWhite, String img_file){
        super(isWhite, img_file); 
    }

    
    //precondition: a piece is clicked and it's not null
    //postcondition: return an arraylist of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.

    public ArrayList<Square> getControlledSquares(Board b, Square start) {
        ArrayList<Square> moves = new ArrayList<>();
        //check right
        for (int i = 1; i+start.getCol() <= 7; i++)
        {
            Square rights = b.getSquareArray()[start.getRow()][start.getCol()+i];
            if (rights.isOccupied())
            {
                    moves.add(rights);
                    break;
            }
            moves.add(rights);

        }
        //check left
        for (int i = 1; start.getCol()-i >= 0 ; i++)
        {
            Square left = b.getSquareArray()[start.getRow()][start.getCol()-i];
            if (left.isOccupied())
            {
                    moves.add(left);
                    break;
            }
            moves.add(left);
        }
        //check down
        for (int i = 1; start.getRow()+i <= 7; i++)
        {
            Square down = b.getSquareArray()[start.getRow()+i][start.getCol()];
            if (down.isOccupied())
            {
                    moves.add(down);
                    break;
            }
            moves.add(down);
        }
        //check up
        for (int i = 1; start.getRow()-i >= 0; i++)
        {
            Square up = b.getSquareArray()[start.getRow()-i][start.getCol()];
            if (up.isOccupied())
            {
                moves.add(up);
                break;
            }
            moves.add(up);
        }
        //check upright
        for (int i = 1; start.getRow()-i >= 0 && start.getCol()+i <=7; i++)
        {
            Square upright = b.getSquareArray()[start.getRow()-i][start.getCol()+i];
            if (upright.isOccupied())
            {
                moves.add(upright);
                break;
            }
            moves.add(upright);
        }
        //check upleft
        for (int i = 1; start.getRow()-i >= 0 && start.getCol()-i >= 0; i++)
        {
            Square upleft = b.getSquareArray()[start.getRow()-i][start.getCol()-i];
            if (upleft.isOccupied())
            {
                moves.add(upleft);
                break;
            }
            moves.add(upleft);
        }
        //check downleft
        for (int i = 1; start.getRow()+i <= 7 && start.getCol()-i >= 0; i++)
        {
            Square downleft = b.getSquareArray()[start.getRow()+i][start.getCol()-i];
            if (downleft.isOccupied())
            {
                moves.add(downleft);
                break;
            }
            moves.add(downleft);
        }
        //check downright
        for (int i = 1; start.getRow()+i <= 7 && start.getCol()+i <=7; i++)
        {
            Square downright = b.getSquareArray()[start.getRow()+i][start.getCol()+i];
            if (downright.isOccupied())
            {
                moves.add(downright);
                break;
            }
            moves.add(downright);
        }

        
        return moves;
    }
    
    public String toString(){
        if (color)
            return "White Cool Queen";
        else
            return "Black Cool Queen";
    }

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.

    //precondition: the mouse is clicked on the piece, it would show all the possible squares that I can move 
    //postcondition: return an arraylist of squares which are legal to move to
    public ArrayList<Square> getLegalMoves(Board b, Square start)
    {
        ArrayList<Square> moves = new ArrayList<>();
        //check right
        for (int i = 1; i+start.getCol() <= 7; i++)
        {
            Square rights = b.getSquareArray()[start.getRow()][start.getCol()+i];
            if (rights.isOccupied())
            {
                if (this.color != rights.getOccupyingPiece().getColor())
                {
                    moves.add(rights);
                }
                else
                {
                    break;
                }
                break;
            } 
            moves.add(rights);

        }
        //check left
        for (int i = 1; start.getCol()-i >= 0 ; i++)
        {
            Square left = b.getSquareArray()[start.getRow()][start.getCol()-i];
            if (left.isOccupied())
            {
                if (this.color != left.getOccupyingPiece().getColor())
                {
                    moves.add(left);
                }
                else 
                {
                    break;
                }
                break;
            }
            moves.add(left);
        }
        //check down
        for (int i = 1; start.getRow()+i <= 7; i++)
        {
            Square down = b.getSquareArray()[start.getRow()+i][start.getCol()];
            if (down.isOccupied())
            {
                if (this.color != down.getOccupyingPiece().getColor())
                {
                    moves.add(down);
                }
                else
                {
                    break;
                }
                break;
            }
            moves.add(down);
        }
        //check up
        for (int i = 1; start.getRow()-i >= 0; i++)
        {
            Square up = b.getSquareArray()[start.getRow()-i][start.getCol()];
            if (up.isOccupied())
            {
                if (this.color != up.getOccupyingPiece().getColor())
                {
                    moves.add(up);
                }
                else
                {
                    break;
                }
                break;
            }
            moves.add(up);
        }
        //check upright
        for (int i = 1; start.getRow()-i >= 0 && start.getCol()+i <=7; i++)
        {
            Square upright = b.getSquareArray()[start.getRow()-i][start.getCol()+i];
            if (upright.isOccupied())
            {
                if (this.color != upright.getOccupyingPiece().getColor())
                {
                    moves.add(upright);
                }
                else
                {
                    break;
                }
                break;
            }
            moves.add(upright);
        }
        //check upleft
        for (int i = 1; start.getRow()-i >= 0 && start.getCol()-i >= 0; i++)
        {
            Square upleft = b.getSquareArray()[start.getRow()-i][start.getCol()-i];
            if (upleft.isOccupied())
            {
                if (this.color != upleft.getOccupyingPiece().getColor())
                {
                    moves.add(upleft);
                }
                else
                {
                    break;
                }
                break;
            }
            moves.add(upleft);
        }
        //check downleft
        for (int i = 1; start.getRow()+i <= 7 && start.getCol()-i >= 0; i++)
        {
            Square downleft = b.getSquareArray()[start.getRow()+i][start.getCol()-i];
            if (downleft.isOccupied())
            {
                if (this.color != downleft.getOccupyingPiece().getColor())
                {
                    moves.add(downleft);
                }
                else
                {
                    break;
                }
                break;
            }
            moves.add(downleft);
        }
        //check downright
        for (int i = 1; start.getRow()+i <= 7 && start.getCol()+i <=7; i++)
        {
            Square downright = b.getSquareArray()[start.getRow()+i][start.getCol()+i];
            if (downright.isOccupied())
            {
                if (this.color != downright.getOccupyingPiece().getColor())
                {
                    moves.add(downright);
                }
                else
                {
                    break;
                }
                break;
            }
            moves.add(downright);
        }

        
        return moves;

    }
    //precondition: there are pieces on the board, and queen successfully captured another piece
    //postcondition: return the number of pieces taken by the queen
    public int getPiecesTaken()
    {
        
        pieceTaken++;
        
        return pieceTaken; 
    }

}



//Garbage code beyond this point ---------------------------------------------------------------


//if the queen has taken over 3 pieces, then the queen turns into pawn


        /* 
        
        //Queen movements
        if (isQueen)
        {
            if (pieceTaken > 3)
            {
                isQueen = false; 
            }
            for (int i = start.getRow(); i < 8 && i >=0; i++)
            {
                for (int j = start.getCol(); j < 8 && j >=0; j++)
                {
                //if queen saw the same color, it can't move past its own piece
                if (this.color)
                {
                    break;
                }
                //if queen saw the opponent color, it can take the piece, but could not move pass
                //if (!this.color  /* && something.get != null */
                
                //    pieceTaken++;
              //     break;
          //      }
                //legalmoves.add(b.getSquareArray()[i][j])
          //     }  
            
        //    }
       // }
        //pawn movements
       // else 
//         {
//             if (!isBlocked && start.getRow() == 0)
//             {
//                 isQueen = true; 
//                 pieceTaken = 0; 
//             }

//         }
//         return moves; 
       
//     }
// }
    
        
//--ddeadend
    // isQueen - is it still a queen
    // if isQueen - queen moves
    // if isNotQueen - pawn moves
    // eat - count++
    // move - pieceTaken 3 - check every time after move
    // 3- false
    // pawn to end - isQueen

/* 



        //track how many times the queen has taken a piece, and if it's taken 3 pieces, it can move like a pawn for the rest of the game.
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (queenHasTaken3Pieces) {
                    // Add knight moves to legalMoves
                    if (isLegalPawnMove(start, i, j)) {
                        legalMoves.add(b.getSquareArray()[i][j]);
                    }
                } else {
                    // Add regular queen moves to legalMoves
                    if (isLegalQueenMove(start, i, j)) {
                        legalMoves.add(b.getSquareArray()[i][j]);
                }   
            }
        }       
    	return null;
    }

public boolean queenHasTaken3Pieces() {
    // Implement logic to check if the queen has taken 3 pieces
    int piecesTakenCount = 0;
    for (Piece piece : piecesTaken) {
        if (piece.getColor() != this.color) {
            piecesTakenCount++;
            if (piecesTakenCount >= 3) {
                return true;
            }
        }
    }
    // This could involve tracking the number of pieces taken in a variable and returning true if it reaches 3
    return false; // Placeholder return value
} 
public boolean isLegalPawnMove(Square start, int row, int col) {
    // Implement logic to check if moving to (row, col) is a legal pawn move from the start square
    // This could involve checking if the move is one square forward (depending on the color of the piece) and if the target square is not occupied by a piece of the same color
    while(start.getRow() > 0) {
        if (this.color) { // If the piece is white
            if (row == start.getRow() - 1 && col == start.getCol() && !b.getSquareArray()[row][col].isOccupied()) {
                return true; // Legal move for white pawn
            }
        } else { // If the piece is black
            if (row == start.getRow() + 1 && col == start.getCol() && !b.getSquareArray()[row][col].isOccupied()) {
                return true; // Legal move for black pawn
            }
        }
    }
    return false; // Placeholder return value

    }  
}


 */
