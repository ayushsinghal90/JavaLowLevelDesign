package org.lowLevelDesign.chess.Model;

import org.lowLevelDesign.chess.Interface.Piece;
import org.lowLevelDesign.chess.Model.Pieces.King;
import org.lowLevelDesign.chess.Model.Pieces.Rook;

public class Move {
    private final Player player;
    private final Box start;
    private final Box end;
    private Piece pieceMoved;
    private Piece pieceKilled;
    private boolean castlingMove = false;

    public Move(Player player, Box start, Box end) {
        this.player = player;
        this.start = start;
        this.end = end;
        this.pieceMoved = start.getPiece();
    }

    public boolean isCastlingMove() {
        return this.castlingMove;
    }

    public void setCastlingMove(boolean castlingMove) {
        this.castlingMove = castlingMove;
    }

    public Player getPlayer() {
        return player;
    }

    public Box getStart() {
        return start;
    }

    public Box getEnd() {
        return end;
    }

    public Piece getPieceMoved() {
        return pieceMoved;
    }

    public void setPieceMoved(Piece pieceMoved) {
        if (pieceMoved instanceof King) {
            ((King) pieceMoved).setHasMoved(true);
        } else if (pieceMoved instanceof Rook) {
            ((Rook) pieceMoved).setHasMoved(true);
        }
        this.pieceMoved = pieceMoved;
    }

    public Piece getPieceKilled() {
        return pieceKilled;
    }

    public void setPieceKilled(Piece pieceKilled) {this.pieceKilled = pieceKilled;}
}
