package com.aditya.tictactoe.services;

import com.aditya.tictactoe.models.*;
import java.util.*;

public class GameService {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private BoardService boardService;

    public GameService(Player player1, Player player2, int boardSize) {
        this.board = new Board(boardSize);
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1; // Player 1 starts
        this.boardService = new BoardService();
    }

    public void startGame() {
        int boardSize = board.getSize();
        System.out.println("Starting Tic Tac Toe Game (" + boardSize + "x" + boardSize + ")!");
        System.out.println(player1.getName() + " (" + player1.getSymbol() + ") vs " + 
                         player2.getName() + " (" + player2.getSymbol() + ")");
        
        Scanner scanner = new Scanner(System.in);
        
        while (board.getGameState() == GameState.PLAYING) {
            boardService.displayBoard(board);
            System.out.println("\n" + currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
            
            // Get valid move from player
            int[] move = getValidMove(scanner);
            int row = move[0];
            int col = move[1];
            
            // Make the move
            boardService.makeMove(board, row, col, currentPlayer.getSymbol());
            
            // Check for win using optimized method (O(n) instead of O(n²))
            if (boardService.checkWin(board, currentPlayer.getSymbol(), row, col)) {
                board.setGameState(GameState.WIN);
                boardService.displayBoard(board);
                System.out.println("\n*** " + currentPlayer.getName() + " wins! ***");
                break;
            }
            
            // Check for draw (O(1) using movesCount)
            if (board.isBoardFull()) {
                board.setGameState(GameState.DRAW);
                boardService.displayBoard(board);
                System.out.println("\n*** It's a draw! ***");
                break;
            }
            
            // Switch players
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }
        
        scanner.close();
    }

    private int[] getValidMove(Scanner scanner) {
        int boardSize = board.getSize();
        int row, col;
        
        do {
            // Get row
            do {
                System.out.print("Enter row (0-" + (boardSize - 1) + "): ");
                row = scanner.nextInt();
                if (row < 0 || row >= boardSize) {
                    System.out.println("Invalid row! Please enter 0 to " + (boardSize - 1) + ".");
                }
            } while (row < 0 || row >= boardSize);
            
            // Get column
            do {
                System.out.print("Enter column (0-" + (boardSize - 1) + "): ");
                col = scanner.nextInt();
                if (col < 0 || col >= boardSize) {
                    System.out.println("Invalid column! Please enter 0 to " + (boardSize - 1) + ".");
                }
            } while (col < 0 || col >= boardSize);
            
            // Check if cell is already occupied
            if (!board.isCellEmpty(row, col)) {
                System.out.println("Cell (" + row + "," + col + ") is already occupied! Please choose an empty cell.");
                System.out.println("Current board state:");
                boardService.displayBoard(board);
                continue; // Ask for new input
            }
            
            // If we reach here, the move is valid
            break;
            
        } while (true);
        
        return new int[]{row, col};
    }

    public void validateAndMakeMove(int row, int col) {
        if (!boardService.isValidMove(board, row, col)) {
            System.out.println("Invalid move! Cell is already occupied or out of bounds.");
            return;
        }
        
        boardService.makeMove(board, row, col, currentPlayer.getSymbol());
        
        // Use optimized win check
        if (boardService.checkWin(board, currentPlayer.getSymbol(), row, col)) {
            board.setGameState(GameState.WIN);
            System.out.println("\n*** " + currentPlayer.getName() + " wins! ***");
        } else if (board.isBoardFull()) {
            board.setGameState(GameState.DRAW);
            System.out.println("\n*** It's a draw! ***");
        } else {
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }
    }
} 