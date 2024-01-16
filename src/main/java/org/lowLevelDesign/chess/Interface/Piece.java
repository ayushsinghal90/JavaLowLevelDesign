package org.lowLevelDesign.chess.Interface;

import org.lowLevelDesign.chess.Model.Board;
import org.lowLevelDesign.chess.Model.Box;

public abstract class Piece {
    private boolean white = false;
    private boolean killed = false;

    public Piece(boolean white) {
        this.white = white;
    }

    public boolean isWhite() {
        return white;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public boolean isKilled() {
        return killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public abstract boolean canMove(Board board, Box start, Box end) throws Exception;
}
