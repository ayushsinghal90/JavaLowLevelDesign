package org.lowLevelDesign.chess.Model.Pieces;

import org.lowLevelDesign.chess.Interface.Piece;
import org.lowLevelDesign.chess.Model.Board;
import org.lowLevelDesign.chess.Model.Box;

public class Rook extends Piece {
    private boolean hasMoved = false;

    public Rook(boolean white) {
        super(white);
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {

        // we can't move the piece to a box that has a piece of the same color
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int deltaX = Math.abs(start.getX() - end.getX());
        int deltaY = Math.abs(start.getY() - end.getY());

        // Rook can move horizontally or vertically
        return (deltaX > 0 && deltaY == 0) || (deltaX == 0 && deltaY > 0);
    }
}
