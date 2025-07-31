package com.aditya.tictactoe.services;

import com.aditya.tictactoe.models.*;
import java.util.*;

public class GameService {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private BoardService boardService;

    public GameService(Player player1, Player player2) {
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1; // Player 1 starts
        this.boardService = new BoardService();
    }

    public void startGame() {
        System.out.println("Starting Tic Tac Toe Game!");
        System.out.println(player1.getName() + " (" + player1.getSymbol() + ") vs " + 
                         player2.getName() + " (" + player2.getSymbol() + ")");
        
        Scanner scanner = new Scanner(System.in);
        
        while (board.getGameState() == GameState.PLAYING) {
            boardService.displayBoard(board);
            System.out.println("\n" + currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
            
            // Get move from player
            int row = getValidRow(scanner);
            int col = getValidCol(scanner);
            
            // Make the move
            boardService.makeMove(board, row, col, currentPlayer.getSymbol());
            
            // Check for win
            if (boardService.checkWin(board, currentPlayer.getSymbol())) {
                board.setGameState(GameState.WIN);
                boardService.displayBoard(board);
                System.out.println("\n*** " + currentPlayer.getName() + " wins! ***");
                break;
            }
            
            // Check for draw
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

    private int getValidRow(Scanner scanner) {
        int row;
        do {
            System.out.print("Enter row (0-2): ");
            row = scanner.nextInt();
            if (row < 0 || row >= board.getSize()) {
                System.out.println("Invalid row! Please enter 0, 1, or 2.");
            }
        } while (row < 0 || row >= board.getSize());
        return row;
    }

    private int getValidCol(Scanner scanner) {
        int col;
        do {
            System.out.print("Enter column (0-2): ");
            col = scanner.nextInt();
            if (col < 0 || col >= board.getSize()) {
                System.out.println("Invalid column! Please enter 0, 1, or 2.");
            }
        } while (col < 0 || col >= board.getSize());
        return col;
    }

    public void validateAndMakeMove(int row, int col) {
        if (!boardService.isValidMove(board, row, col)) {
            System.out.println("Invalid move! Cell is already occupied or out of bounds.");
            return;
        }
        
        boardService.makeMove(board, row, col, currentPlayer.getSymbol());
        
        if (boardService.checkWin(board, currentPlayer.getSymbol())) {
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