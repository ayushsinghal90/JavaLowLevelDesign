package org.lowLevelDesign.chess.Model;

public class Player {
    private boolean whiteSide = false;

    public Player(boolean whiteSide) {
        this.whiteSide = whiteSide;
    }

    public boolean isWhiteSide() {
        return this.whiteSide;
    }
}
