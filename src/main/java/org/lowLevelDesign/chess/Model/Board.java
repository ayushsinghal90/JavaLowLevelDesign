package org.lowLevelDesign.chess.Model;

import org.lowLevelDesign.chess.Interface.Piece;
import org.lowLevelDesign.chess.Model.Pieces.Bishop;
import org.lowLevelDesign.chess.Model.Pieces.King;
import org.lowLevelDesign.chess.Model.Pieces.Knight;
import org.lowLevelDesign.chess.Model.Pieces.Pawn;
import org.lowLevelDesign.chess.Model.Pieces.Queen;
import org.lowLevelDesign.chess.Model.Pieces.Rook;

/**
 * Represents a chess board with boxes containing chess pieces.
 *
 * @author ayushsinghal90
 */
public class Board {
    /** 2D array representing the chess board's boxes. */
    private final Box[][] boxes;

    /**
     * Creates a new chess board and initializes it.
     */
    public Board() {
        // Initialize the chess board
        boxes = new Box[8][8];
        this.resetBoard();
    }

    /**
     * Gets the box at the specified coordinates on the chess board.
     *
     * @param x The row index.
     * @param y The column index.
     * @return The box at the specified coordinates.
     * @throws Exception If the indices are out of bounds.
     */
    public Box getBox(int x, int y) throws Exception {
        // Validate indices
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new Exception("Index out of bound");
        }

        // Return the box at the specified indices
        return boxes[x][y];
    }

    /**
     * Resets the chess board by initializing white and black pieces, along with empty boxes.
     */
    public void resetBoard() {
        // Initialize white pieces
        setPieces(true);

        // Initialize black pieces
        setPieces(false);

        // Initialize remaining boxes without any piece
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                boxes[i][j] = new Box(i, j, null);
            }
        }
    }

    private void setPieces(boolean isWhite) {
        // Determine the starting index based on the color
        int index = isWhite ? 7 : 0;

        // Initialize pieces for the specified color
        boxes[index][0] = new Box(index, 0, new Rook(isWhite));
        boxes[index][1] = new Box(index, 1, new Knight(isWhite));
        boxes[index][2] = new Box(index, 2, new Bishop(isWhite));
        boxes[index][3] = new Box(index, 3, new Queen(isWhite));
        boxes[index][4] = new Box(index, 4, new King(isWhite));
        boxes[index][5] = new Box(index, 5, new Bishop(isWhite));
        boxes[index][6] = new Box(index, 6, new Knight(isWhite));
        boxes[index][7] = new Box(index, 7, new Rook(isWhite));

        // Update the index for pawn placement
        index += isWhite ? -1 : 1;

        // Initialize pawns for the specified color
        for (int j = 0; j < 8; j++) {
            boxes[index][j] = new Box(index, j, new Pawn(isWhite));
        }
    }

    public void printBoard() {
        System.out.println("  a b c d e f g h");
        System.out.println(" +----------------");
        for (int i = 0; i < 8; i++) {
            System.out.print((8 - i) + "|");
            for (int j = 0; j < 8; j++) {
                Piece piece = boxes[i][j].getPiece();
                if (piece != null) {
                    System.out.print(" " + getSymbol(piece));
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
        System.out.println(" +----------------");
    }

    private String getSymbol(Piece piece) {
        // Assuming you have methods like isWhite() and isKing() in the Piece class
        String color = piece.isWhite() ? "W" : "B";

        if (piece instanceof Pawn) {
            return color + "P";
        } else if (piece instanceof Rook) {
            return color + "R";
        } else if (piece instanceof Knight) {
            return color + "N";
        } else if (piece instanceof Bishop) {
            return color + "B";
        } else if (piece instanceof Queen) {
            return color + "Q";
        } else if (piece instanceof King) {
            return color + "K";
        } else {
            // Handle other cases or throw an exception if needed
            return "  ";
        }
    }
}
