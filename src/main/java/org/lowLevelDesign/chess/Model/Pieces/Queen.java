package org.lowLevelDesign.chess.Model.Pieces;

import org.lowLevelDesign.chess.Interface.Piece;
import org.lowLevelDesign.chess.Model.Board;
import org.lowLevelDesign.chess.Model.Box;

public class Queen extends Piece {
    public Queen(boolean white) {
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

        // Queen can move like a rook (vertically or horizontally)
        if (deltaX > 0 && deltaY == 0) {
            return true; // Move horizontally
        } else if (deltaX == 0 && deltaY > 0) {
            return true; // Move vertically
        }

        // Queen can move like a bishop (diagonally)
        return deltaX == deltaY;
    }
}
