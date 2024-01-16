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

/**
 * Represents a chess game between two players.
 *
 * @author ayushsinghal90
 */
public class Game {

    private final Player[] players;
    private final Board board;
    private final List<Move> movesPlayed;
    private Player currentTurn;
    private GameStatus status;

    /**
     * Initializes a new chess game with two players.
     *
     * @param p1 The first player.
     * @param p2 The second player.
     */
    public Game(Player p1, Player p2) {
        this.players = new Player[2];
        this.movesPlayed = new ArrayList<>();
        this.board = new Board();
        initialize(p1, p2);
    }

    /**
     * Initializes the game with players and the chessboard.
     *
     * @param p1 The first player.
     * @param p2 The second player.
     */
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

    /**
     * Checks if the game has ended.
     *
     * @return True if the game has ended, false otherwise.
     */
    public boolean isEnd() {
        return getStatus() != GameStatus.ACTIVE;
    }

    /**
     * Gets the current status of the game.
     *
     * @return The game status.
     */
    public GameStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the game.
     *
     * @param status The new status of the game.
     */
    public void setStatus(GameStatus status) {
        this.status = status;
    }

    /**
     * Handles a player's move on the chessboard.
     *
     * @param player The player making the move.
     * @param startX The starting X-coordinate.
     * @param endX   The ending X-coordinate.
     * @param startY The starting Y-coordinate.
     * @param endY   The ending Y-coordinate.
     * @return True if the move is valid and executed, false otherwise.
     * @throws Exception If an error occurs during the move execution.
     */
    public boolean playerMove(Player player, int startX, int endX, int startY, int endY) throws Exception {
        Box startBox = board.getBox(startX, startY);
        Box endBox = board.getBox(endX, endY);
        Move move = new Move(player, startBox, endBox);
        return makeMove(move, player);
    }

    /**
     * Executes the given move on the chessboard.
     *
     * @param move   The move to be executed.
     * @param player The player making the move.
     * @return True if the move is valid and executed, false otherwise.
     * @throws Exception If an error occurs during the move execution.
     */
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

    /**
     * Performs a castling move on the chessboard.
     *
     * @param move The castling move to be executed.
     * @throws Exception If an error occurs during the move execution.
     */
    private void performCastlingMove(Move move) throws Exception {
        int x = move.getStart().getX();
        int y = move.getEnd().getY();

        board.getBox(x, y).setPiece(move.getStart().getPiece());

        int rootCurrentY = (y == 2) ? 0 : 7;
        int rookNewY = (y == 2) ? 3 : 5;

        board.getBox(x, rookNewY).setPiece(board.getBox(x, rootCurrentY).getPiece());
    }

    /**
     * Checks for a checkmate situation on the chessboard.
     *
     * @param destPiece The piece at the destination after the move.
     * @param player    The player making the move.
     */
    private void checkForCheckmate(Piece destPiece, Player player) {
        if (destPiece instanceof King) {
            if (player.isWhiteSide()) {
                setStatus(GameStatus.WHITE_WIN);
            } else {
                setStatus(GameStatus.BLACK_WIN);
            }
        }
    }

    /**
     * Switches the turn to the other player.
     */
    private void switchTurn() {
        currentTurn = (currentTurn == players[0]) ? players[1] : players[0];
    }

    /**
     * Prints the current state of the chessboard.
     */
    public void printBoard() {
        board.printBoard();
    }
}
