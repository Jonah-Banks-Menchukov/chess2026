//Jonah Banks
//This class stores the board constructor and instance variables
package com.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
import java.net.URL;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.*;

//You will be implmenting a part of a function and a whole function in this document. Please follow the directions for the 
//suggested order of completion that should make testing easier.
@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener, MouseMotionListener {
    // Resource location constants for piece images
    public static final String PICTURE_PATH = "/src/main/java/com/example/Pictures/";
    private static final String RESOURCES_WBISHOP_PNG = PICTURE_PATH+"wbishop.png";
    private static final String RESOURCES_BBISHOP_PNG = PICTURE_PATH+"bbishop.png";
    private static final String RESOURCES_WKING_PNG=PICTURE_PATH+"wking.png";
    private static final String RESOURCES_BKING_PNG=PICTURE_PATH+"bking.png";

    //constant used to keep track of where the piece should be drawn when the user is dragging it
    private static final int PIECE_OFFSET = 24;

    // Logical and graphical representations of board
    private final Square[][] board;
    private final GameWindow g;

    // contains true if it's white's turn.
    private boolean whiteTurn;
    private ArrayList<Square> blackOccupiedSquares;
    private ArrayList<Square> whiteOccupiedSquares;

    // if the player is currently dragging a piece this variable contains it.
    Piece currPiece;
    //the square your piece came from when the user tries to move it.
    private Square fromMoveSquare;
    //the square your piece tries to go to when the user tries to move it.
    private Square endSquare;

    // used to keep track of the x/y coordinates of the mouse.
    private int currX;
    private int currY;

    public Board(GameWindow g) {
        this.g = g;
        board = new Square[8][8];
        setLayout(new GridLayout(8, 8, 0, 0));

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        // TO BE IMPLEMENTED FIRST

        // for (.....)
        // populate the board with squares here. Note that the board is composed of 64
        // squares alternating from white to black.
        //IMPORTANT**** : please note for each square you create you HAVE to do "this.add(<your new square here>)" 
        //the reason this is required has to do with how visual components are rendered, so if you neglect to do this
        //you will not see any of your squares show up on the board!
        // Where's the "add" method? Stay tuned for next unit where we discover where it is and why we can do this action.
        boolean white=false;
        for (int row=0;row<8;row++){
            white=!white;
            for(int col=0;col<8;col++){
                Square s=new Square(this,white,row,col);
                board[row][col]=s;
                this.add(s);
                white=!white;
            }
        }
        
        initializePieces();

        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(400, 400));

        whiteTurn = true;

    }
    //precondition - the board is initialized and contains a king of either color. The boolean kingColor corresponds to the color of the king we wish to know the status of.
    //postcondition - returns true of the king is in check and false otherwise.
	public boolean isInCheck(boolean kingColor){
        if(kingColor){
            for(Square s:blackOccupiedSquares){
                ArrayList<Square> controlledSqs=s.getOccupyingPiece().getControlledSquares(board, s);
                for(Square c:controlledSqs){
                    if(c.getOccupyingPiece()!=null&&c.getOccupyingPiece().getColor()!=kingColor&&c.getOccupyingPiece().instanceOf(King)){
                        return true;
                    }
                }
            }
        }
    }
    // set up the board such that the black pieces are on one side and the white
    // pieces are on the other.
    // since we only have one kind of piece for now you need only set the same
    // number of pieces on either side.
    // it's up to you how you wish to arrange your pieces.
    public void initializePieces() {

        board[0][2].put(new HopShop(true,RESOURCES_WBISHOP_PNG));
        board[0][5].put(new HopShop(true, RESOURCES_WBISHOP_PNG));
        board[0][3].put(new King(true,RESOURCES_WBISHOP_PNG));
        whiteOccupiedSquares.add(board[0][2]);
        whiteOccupiedSquares.add(board[0][5]);
        whiteOccupiedSquares.add(board[0][3]);
        board[7][2].put(new HopShop(false,RESOURCES_BBISHOP_PNG));
        board[7][5].put(new HopShop(false,RESOURCES_BBISHOP_PNG));
        board[0][3].put(new King(false,RESOURCES_BKING_PNG));
        blackOccupiedSquares.add(board[7][2]);
        blackOccupiedSquares.add(board[7][5]);
        blackOccupiedSquares.add(board[7][3]);
    }

    public Square[][] getSquareArray() {
        return this.board;
    }

    public boolean getTurn() {
        return whiteTurn;
    }

    public void setCurrPiece(Piece p) {
        this.currPiece = p;
    }

    public Piece getCurrPiece() {
        return this.currPiece;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col].draw(g);
            }
        }

        if (currPiece != null) {
            if (currPiece.getColor() == whiteTurn) {
                final Image img = currPiece.getImage();
                g.drawImage(img, currX, currY, null);
            }
        }
    }

    @Override
    //Precondition: Mouse pressed, there is a piece on the square clicked
    //Postcondition: squares that piece can move to are highlighted, currpiece is now the piece that was pressed.
    public void mousePressed(MouseEvent e) {
        currX = e.getX();
        currY = e.getY();

        Square sq = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));

        if (sq.isOccupied() && sq.getOccupyingPiece().getColor() == whiteTurn) {
            currPiece = sq.getOccupyingPiece();
            fromMoveSquare = sq;
            ArrayList<Square> l=currPiece.getControlledSquares(board, fromMoveSquare);
            for(int i=0; i<l.size();i++){
                l.get(i).setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLUE));
            }
            sq.setDisplay(false);
        }
        repaint();
    }

    // TO BE IMPLEMENTED!
    // should move the piece to the desired location only if this is a legal move.
    // use the pieces "legal move" function to determine if this move is legal, then
    // complete it by moving the new piece to it's new board location.
    //Precondition: currPiece not null, mouse is released, the piece has same color as whose turn it is
    //Postcondition: currpiece moved to new space if the move is legal according to the rules. 
    @Override
    public void mouseReleased(MouseEvent e) {
        endSquare = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
        for(Square[] row: board){
            for(Square s:row){
                s.setBorder(null);
            }
        }
        if(fromMoveSquare!= null){
            if(currPiece!=null&&currPiece.getLegalMoves(this, fromMoveSquare).contains(endSquare)){
                fromMoveSquare.setDisplay(false);
                endSquare.put(currPiece);
                endSquare.setDisplay(true);
                fromMoveSquare.removePiece();
                whiteTurn=!whiteTurn;
            }else{
                fromMoveSquare.setDisplay(true);
                fromMoveSquare.put(currPiece);
            }
        }
        currPiece = null;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currX = e.getX() - PIECE_OFFSET;
        currY = e.getY() - PIECE_OFFSET;

        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}