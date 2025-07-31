package com.aditya.tictactoe.services;

import com.aditya.tictactoe.models.*;

public class BoardService {
    
    public void displayBoard(Board board) {
        System.out.println("\nCurrent Board:");
        System.out.println("-------------");
        
        for (int i = 0; i < board.getSize(); i++) {
            System.out.print("| ");
            for (int j = 0; j < board.getSize(); j++) {
                System.out.print(board.getCell(i, j).getContent() + " | ");
            }
            System.out.println();
            if (i < board.getSize() - 1) {
                System.out.println("-------------");
            }
        }
        System.out.println("-------------");
    }

    public boolean isValidMove(Board board, int row, int col) {
        return row >= 0 && row < board.getSize() && 
               col >= 0 && col < board.getSize() && 
               board.isCellEmpty(row, col);
    }

    public boolean checkWin(Board board, String symbol) {
        // Check rows
        for (int i = 0; i < board.getSize(); i++) {
            if (board.getCell(i, 0).getContent().equals(symbol) &&
                board.getCell(i, 1).getContent().equals(symbol) &&
                board.getCell(i, 2).getContent().equals(symbol)) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < board.getSize(); j++) {
            if (board.getCell(0, j).getContent().equals(symbol) &&
                board.getCell(1, j).getContent().equals(symbol) &&
                board.getCell(2, j).getContent().equals(symbol)) {
                return true;
            }
        }

        // Check diagonals
        if (board.getCell(0, 0).getContent().equals(symbol) &&
            board.getCell(1, 1).getContent().equals(symbol) &&
            board.getCell(2, 2).getContent().equals(symbol)) {
            return true;
        }

        if (board.getCell(0, 2).getContent().equals(symbol) &&
            board.getCell(1, 1).getContent().equals(symbol) &&
            board.getCell(2, 0).getContent().equals(symbol)) {
            return true;
        }

        return false;
    }

    public void makeMove(Board board, int row, int col, String symbol) {
        board.setCell(row, col, symbol);
    }
} 