package org.lowLevelDesign.chess.Model.Pieces;

import org.lowLevelDesign.chess.Interface.Piece;
import org.lowLevelDesign.chess.Model.Board;
import org.lowLevelDesign.chess.Model.Box;

public class Bishop extends Piece {
    public Bishop(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {

        // we can't move the piece to a box that has a piece of the same color
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return x == y;
    }
}
