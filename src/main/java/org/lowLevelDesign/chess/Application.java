package org.lowLevelDesign.chess;

import java.util.Scanner;

import org.lowLevelDesign.chess.Model.Player;
import org.lowLevelDesign.chess.Service.Game;

public class Application {
    private static Game chessGame;
    private static Player player1;
    private static Player player2;

    private static void initialize() {
        // Create players
        player1 = new Player(true); // White side
        player2 = new Player(false); // Black side

        // Create a chess game
        chessGame = new Game(player1, player2);

        // Display initial board
        System.out.println("Initial Chess Board:");
        chessGame.printBoard();
    }

    public static void main(String[] args) {
        // Demo Play moves
        try {
            demoChessGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Should start game");

        boolean start = sc.nextBoolean();

        if (start) {
            startSimulateGame();
        }
    }

    private static void startSimulateGame() {
        Player currentPlayer = player1;
        int startX, endX, startY, endY;
        Scanner sc = new Scanner(System.in);

        System.out.println("White turn:");
        System.out.println("Provide start location Ex: 0 2");
        startX = sc.nextInt();
        endX = sc.nextInt();

        System.out.println("Provide destination location Ex: 0 2");
        startY = sc.nextInt();
        endY = sc.nextInt();

        while (!chessGame.isEnd()) {
            try {
                if (chessGame.playerMove(currentPlayer, startX, endX, startY, endY)) {
                    currentPlayer = currentPlayer == player1 ? player2 : player1;
                    System.out.println((currentPlayer.isWhiteSide() ? "White" : "Black") + " turn now.%n");
                } else {
                    System.out.println("Invalid move");
                    System.out.println((currentPlayer.isWhiteSide() ? "White" : "Black") + " turn again.%n");
                }

                System.out.println("Provide start location Ex: 0 2");
                startX = sc.nextInt();
                endX = sc.nextInt();

                System.out.println("Provide destination location Ex: 0 2");
                startY = sc.nextInt();
                endY = sc.nextInt();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void displayBoard() {
        System.out.println("\nAfter Moves:");
        chessGame.printBoard();

        // Check game status
        if (chessGame.isEnd()) {
            System.out.println("\nGame Over! Winner: " + chessGame.getStatus());
        } else {
            System.out.println("\nGame still in progress.");
        }
    }

    public static void demoChessGame() throws Exception {
        chessGame.playerMove(player1, 6, 4, 4, 4); // Pawn for Player 1 moves
        chessGame.playerMove(player2, 1, 4, 3, 4); // Pawn for Player 2 moves
        chessGame.playerMove(player1, 6, 3, 4, 3); // Another pawn for Player 1 moves
        chessGame.playerMove(player2, 0, 6, 2, 5); // Knight for Player 2 moves

        // Competitive moves
        chessGame.playerMove(player1, 7, 5, 4, 2); // Bishop for Player 1 moves
        chessGame.playerMove(player2, 1, 6, 2, 6); // Pawn for Player 2 moves
        chessGame.playerMove(player1, 6, 6, 5, 6); // Another pawn for Player 1 moves
        chessGame.playerMove(player2, 3, 4, 4, 3); // Another pawn for Player 2 moves

        // Competitive moves leading to checkmate
        chessGame.playerMove(player1, 7, 3, 4, 3); // Queen for Player 1 moves (check)
        chessGame.playerMove(player2, 2, 5, 4, 4); // Knight for Player 2 moves
        chessGame.playerMove(player1, 4, 3, 4, 4); // Bishop for Player 1 moves (checkmate)

        chessGame.playerMove(player2, 0, 5, 1, 4);
        chessGame.playerMove(player1, 4, 4, 1, 4);
        chessGame.playerMove(player2, 0, 3, 1, 4);
        chessGame.playerMove(player1, 6, 5, 5, 5);
        chessGame.playerMove(player2, 1, 4, 7, 4);
    }
}
