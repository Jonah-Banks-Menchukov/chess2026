package com.example;
import java.util.ArrayList;
//you will need to implement two functions in this file.
public class Telekenis extends Piece{
public Telekenis(boolean color, String img){
super(color, img);
}
// TO BE IMPLEMENTED!
//return a list of every square that is "controlled" by this piece. A square is controlled
//if the piece capture into it legally.
//takes pieace one diagonal square away
//Precondtion board and start are valid.
//Postcondition: Returns all diagonal adjacent squares in bounds.
public ArrayList<Square> getControlledSquares(Square [][]board, Square start) {
ArrayList<Square> moves=new ArrayList<Square>();
//check up-left, up-right, down-left, down-right
int [] drow = {-1, -1, 1, 1};
int [] dcol = {-1, 1, -1, 1};
for(int i=0; i<drow.length; i++){
//in bounds and there's a piece there of the opposotie color
if(start.getRow()+drow[i]>=0 && start.getRow()+drow[i]<8&&
start.getCol()+dcol[i]>=0&&start.getCol()+dcol[i]<8){
moves.add(board[start.getRow()+drow[i]][start.getCol()
+dcol[i]]);
}
}
return moves;
}
//TO BE IMPLEMENTED!
//implement the move function here
//it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
//returns an arraylist of squares which are legal to move to
//please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
//going to score any points.
//This gets all the legal moves for the piece
//moves anywhere on same color of piece
//Precondition: board and start are valid.
//Postcondition:Returns all legal Telekenis moves.
public ArrayList<Square> getLegalMoves(Board board, Square start){
ArrayList<Square> moves=new ArrayList<Square>();
for(Square [] row: board.getSquareArray()){
for(Square s: row){
if(s.getColor()==this.color){
if(!s.isOccupied()){
moves.add(s);
System.out.println("Moves size: " + moves.size());
}
}
}
}
int [] drow = {-1, -1, 1, 1};
int [] dcol = {-1, 1, -1, 1};
for(int i=0; i<drow.length; i++){
//in bounds and there's a piece there of the opposotie color
if(start.getRow()+drow[i]>=0 && start.getRow()+drow[i]<8&&
start.getCol()+dcol[i]>=0&&start.getCol()+dcol[i]<8 &&
board.getSquareArray()[start.getRow()+drow[i]][start.getCol()
+dcol[i]].isOccupied() &&
board.getSquareArray()[start.getRow()+drow[i]][start.getCol()
+dcol[i]].getOccupyingPiece().getColor()!=color){
moves.add(board.getSquareArray()[start.getRow()+drow[i]]
[start.getCol()+dcol[i]]);
}
}
return moves;
}
}