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
//CoolQueen
//Currently, it'll move like a queen; in the future
// after killing 3 times, it'll turn into a pawn
//you will need to implement two functions in this file.
public class CoolQueen extends Piece {
    public CoolQueen(boolean isWhite, String img_file){
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
        return super.toString()+" Cool Queen";
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
                if (this.getColor() != rights.getOccupyingPiece().getColor())
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
                if (this.getColor() != left.getOccupyingPiece().getColor())
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
                if (this.getColor() != down.getOccupyingPiece().getColor())
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
                if (this.getColor() != up.getOccupyingPiece().getColor())
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
                if (this.getColor() != upright.getOccupyingPiece().getColor())
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
                if (this.getColor() != upleft.getOccupyingPiece().getColor())
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
                if (this.getColor() != downleft.getOccupyingPiece().getColor())
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
                if (this.getColor() != downright.getOccupyingPiece().getColor())
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
}