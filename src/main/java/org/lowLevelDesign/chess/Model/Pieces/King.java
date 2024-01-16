package org.lowLevelDesign.chess.Model.Pieces;

import org.lowLevelDesign.chess.Interface.Piece;
import org.lowLevelDesign.chess.Model.Board;
import org.lowLevelDesign.chess.Model.Box;

public class King extends Piece {
    private boolean castlingMove = false;
    private boolean hasMoved = false;

    public King(boolean white) {
        super(white);
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {this.hasMoved = hasMoved;}

    public boolean isCastlingMove() {
        return this.castlingMove;
    }

    public void setCastlingMove(boolean castlingDone) {
        this.castlingMove = castlingDone;
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) throws Exception {
        // we can't move the piece to a box that has a piece of the same color
        // and king will not get attacked.
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite() && isAttacked(board, start)) {
            // check if this move will not result in the king being attacked, if so return true
            return false;
        }

        try {
            return this.isValidCastling(board, start, end);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isValidCastling(Board board, Box start, Box end) throws Exception {
        // Check if castling is already done
        if (this.isHasMoved()) {
            return false;
        }

        // Check if the move is a castling move
        if ((start.getX() == 0 || start.getX() == 7) && start.getY() == 4 && Math.abs(end.getY() - start.getY()) == 2) {
            // Check if there is a Rook in the correct position
            Box rookBox = end.getY() == 2 ? board.getBox(start.getX(), 0) : board.getBox(start.getX(), 7);

            if (rookBox != null && rookBox.getPiece() instanceof Rook) {
                return checkRookForCastling(board, rookBox, end);
            }
        }

        return false;
    }

    private boolean checkRookForCastling(Board board, Box rookBox, Box kingPos) throws Exception {
        int rookYIncrement = rookBox.getY() == 0 ? -1 : 1;
        int rookY = 4 + rookYIncrement;

        // Check if there are no pieces between the king and the rook
        while (rookY != rookBox.getY()) {
            if (board.getBox(rookBox.getX(), rookY) != null) {
                return false;
            }
            rookY += rookYIncrement;
        }

        // Check if this move will not result in the king being attacked
        Box newRookPos = rookBox.getY() == 0 ? board.getBox(rookBox.getX(), 3) : board.getBox(rookBox.getX(), 5);
        if (!isAttacked(board, kingPos) && !isAttacked(board, newRookPos)) {
            this.setCastlingMove(true);
            return true;
        }

        return false;
    }

    private boolean isAttacked(Board board, Box box) throws Exception {
        // Iterate over all boxes on the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Box start = board.getBox(i, j);
                Piece piece = start.getPiece();

                // Check if the piece exists and is an opponent's piece
                if (piece != null && piece.isWhite() != this.isWhite()) {
                    // Check if the opponent piece can move to the target box
                    if (piece.canMove(board, start, box)) {
                        return true; // The box is attacked
                    }
                }
            }
        }

        return false; // The box is not attacked
    }
}
