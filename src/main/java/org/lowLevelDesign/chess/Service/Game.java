package org.lowLevelDesign.chess.Service;

import java.util.ArrayList;
import java.util.List;

import org.lowLevelDesign.chess.Constant.GameStatus;
import org.lowLevelDesign.chess.Interface.Piece;
import org.lowLevelDesign.chess.Model.Board;
import org.lowLevelDesign.chess.Model.Box;
import org.lowLevelDesign.chess.Model.Move;
import org.lowLevelDesign.chess.Model.Pieces.King;
import org.lowLevelDesign.chess.Model.Player;

public class Game {
    private final Player[] players;
    private final Board board;
    private final List<Move> movesPlayed;
    private Player currentTurn;
    private GameStatus status;

    public Game(Player p1, Player p2) {
        this.players = new Player[2];
        this.movesPlayed = new ArrayList<>();
        this.board = new Board();
        initialize(p1, p2);
    }

    private void initialize(Player p1, Player p2) {
        players[0] = p1;
        players[1] = p2;

        board.resetBoard();

        if (p1.isWhiteSide()) {
            this.currentTurn = p1;
        } else {
            this.currentTurn = p2;
        }

        movesPlayed.clear();
        status = GameStatus.ACTIVE;
    }

    public boolean isEnd() {
        return getStatus() != GameStatus.ACTIVE;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public boolean playerMove(Player player, int startX, int endX, int startY, int endY) throws Exception {
        Box startBox = board.getBox(startX, endX);
        Box endBox = board.getBox(startY, endY); // Fix: Corrected endBox coordinates
        Move move = new Move(player, startBox, endBox);
        return makeMove(move, player);
    }

    private boolean makeMove(Move move, Player player) throws Exception {
        Piece sourcePiece = move.getStart().getPiece();

        if (sourcePiece == null || player != currentTurn || sourcePiece.isWhite() != player.isWhiteSide()) {
            return false;
        }

        if (!sourcePiece.canMove(board, move.getStart(), move.getEnd())) {
            return false;
        }

        if (sourcePiece instanceof King && ((King) sourcePiece).isCastlingMove()) {
            move.setCastlingMove(true);
        }

        Piece destPiece = move.getEnd().getPiece();
        if (!move.isCastlingMove() && destPiece != null) {
            destPiece.setKilled(true);
            move.setPieceKilled(destPiece);
        }

        move.setPieceMoved(move.getStart().getPiece());
        movesPlayed.add(move);

        if (move.isCastlingMove()) {
            performCastlingMove(move);
        } else {
            move.getEnd().setPiece(move.getStart().getPiece());
        }
        move.getStart().setPiece(null);

        checkForCheckmate(destPiece, player);
        switchTurn();

        printBoard();
        return true;
    }

    private void performCastlingMove(Move move) throws Exception {
        int x = move.getStart().getX();
        int y = move.getEnd().getY();

        board.getBox(x, y).setPiece(move.getStart().getPiece());

        int rootCurrentY = (y == 2) ? 0 : 7;
        int rookNewY = (y == 2) ? 3 : 5;

        board.getBox(x, rookNewY).setPiece(board.getBox(x, rootCurrentY).getPiece());
    }

    private void checkForCheckmate(Piece destPiece, Player player) {
        if (destPiece instanceof King) {
            if (player.isWhiteSide()) {
                setStatus(GameStatus.WHITE_WIN);
            } else {
                setStatus(GameStatus.BLACK_WIN);
            }
        }
    }

    private void switchTurn() {
        currentTurn = (currentTurn == players[0]) ? players[1] : players[0];
    }

    public void printBoard() {
        board.printBoard();
    }
}
