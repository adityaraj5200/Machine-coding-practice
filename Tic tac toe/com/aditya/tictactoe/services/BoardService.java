package com.aditya.tictactoe.services;

import com.aditya.tictactoe.models.*;

public class BoardService {
    
    public void displayBoard(Board board) {
        System.out.println("\nCurrent Board:");
        int size = board.getSize();
        
        // Print top border
        printBorder(size);
        
        for (int i = 0; i < size; i++) {
            System.out.print("| ");
            for (int j = 0; j < size; j++) {
                String content = board.getCell(i, j).getContent();
                // Pad with space for consistent alignment
                System.out.print(content + " | ");
            }
            System.out.println();
            if (i < size - 1) {
                printBorder(size);
            }
        }
        printBorder(size);
    }

    private void printBorder(int size) {
        for (int i = 0; i < size; i++) {
            System.out.print("----");
        }
        System.out.println("-");
    }

    public boolean isValidMove(Board board, int row, int col) {
        int size = board.getSize();
        
        // Check if coordinates are within bounds
        if (row < 0 || row >= size || col < 0 || col >= size) {
            System.out.println("Invalid coordinates! Row and column must be between 0 and " + (size - 1) + ".");
            return false;
        }
        
        // Check if cell is already occupied
        if (!board.isCellEmpty(row, col)) {
            System.out.println("Cell (" + row + "," + col + ") is already occupied by '" + 
                             board.getCell(row, col).getContent() + "'! Please choose an empty cell.");
            return false;
        }
        
        return true;
    }

    public boolean checkWin(Board board, String symbol, int lastRow, int lastCol) {
        int size = board.getSize();
        
        // Only check the row, column, and diagonals that include the last move
        // This reduces time complexity from O(n²) to O(n)
        
        // Check row (O(n))
        boolean rowWin = true;
        for (int j = 0; j < size; j++) {
            if (!board.getCell(lastRow, j).getContent().equals(symbol)) {
                rowWin = false;
                break;
            }
        }
        if (rowWin) return true;
        
        // Check column (O(n))
        boolean colWin = true;
        for (int i = 0; i < size; i++) {
            if (!board.getCell(i, lastCol).getContent().equals(symbol)) {
                colWin = false;
                break;
            }
        }
        if (colWin) return true;
        
        // Check main diagonal (O(n)) - only if the move is on the diagonal
        if (lastRow == lastCol) {
            boolean diagWin = true;
            for (int i = 0; i < size; i++) {
                if (!board.getCell(i, i).getContent().equals(symbol)) {
                    diagWin = false;
                    break;
                }
            }
            if (diagWin) return true;
        }
        
        // Check anti-diagonal (O(n)) - only if the move is on the anti-diagonal
        if (lastRow + lastCol == size - 1) {
            boolean antiDiagWin = true;
            for (int i = 0; i < size; i++) {
                if (!board.getCell(i, size - 1 - i).getContent().equals(symbol)) {
                    antiDiagWin = false;
                    break;
                }
            }
            if (antiDiagWin) return true;
        }
        
        return false;
    }

    // Legacy method for backward compatibility - checks entire board
    public boolean checkWin(Board board, String symbol) {
        int size = board.getSize();
        
        // Check rows
        for (int i = 0; i < size; i++) {
            boolean rowWin = true;
            for (int j = 0; j < size; j++) {
                if (!board.getCell(i, j).getContent().equals(symbol)) {
                    rowWin = false;
                    break;
                }
            }
            if (rowWin) return true;
        }

        // Check columns
        for (int j = 0; j < size; j++) {
            boolean colWin = true;
            for (int i = 0; i < size; i++) {
                if (!board.getCell(i, j).getContent().equals(symbol)) {
                    colWin = false;
                    break;
                }
            }
            if (colWin) return true;
        }

        // Check main diagonal
        boolean diagWin = true;
        for (int i = 0; i < size; i++) {
            if (!board.getCell(i, i).getContent().equals(symbol)) {
                diagWin = false;
                break;
            }
        }
        if (diagWin) return true;

        // Check anti-diagonal
        boolean antiDiagWin = true;
        for (int i = 0; i < size; i++) {
            if (!board.getCell(i, size - 1 - i).getContent().equals(symbol)) {
                antiDiagWin = false;
                break;
            }
        }
        return antiDiagWin;
    }

    public void makeMove(Board board, int row, int col, String symbol) {
        board.setCell(row, col, symbol);
    }
} 