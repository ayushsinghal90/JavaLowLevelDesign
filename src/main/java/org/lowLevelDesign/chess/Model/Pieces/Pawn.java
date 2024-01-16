package org.lowLevelDesign.chess.Model.Pieces;

import org.lowLevelDesign.chess.Interface.Piece;
import org.lowLevelDesign.chess.Model.Board;
import org.lowLevelDesign.chess.Model.Box;

public class Pawn extends Piece {
    public Pawn(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {

        // we can't move the piece to a box that has a piece of the same color
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int deltaX = Math.abs(start.getX() - end.getX());
        int deltaY = Math.abs(start.getY() - end.getY());

        // For a pawn, it can move forward one square, or two squares on its first move
        // It captures diagonally
        if (this.isWhite()) {
            if (deltaX == 1 && deltaY == 0 && end.getPiece() == null) {
                return true; // Move forward one square
            } else if (start.getX() == 6 && deltaX == 2 && deltaY == 0 && end.getPiece() == null) {
                // Move forward two squares on the first move
                return true;
            } else return deltaX == 1 && deltaY == 1 && end.getPiece() != null && !end.getPiece().isWhite(); // Capture diagonally
        } else {
            if (deltaX == 1 && deltaY == 0 && end.getPiece() == null) {
                return true; // Move forward one square
            } else if (start.getX() == 1 && deltaX == 2 && deltaY == 0 && end.getPiece() == null) {
                // Move forward two squares on the first move
                return true;
            } else return deltaX == 1 && deltaY == 1 && end.getPiece() != null && end.getPiece().isWhite(); // Capture diagonally
        }
    }
}
