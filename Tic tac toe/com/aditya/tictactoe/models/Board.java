package com.aditya.tictactoe.models;

public class Board {
    private Cell[][] cells;
    private GameState gameState;
    private static final int SIZE = 3;

    public Board() {
        this.cells = new Cell[SIZE][SIZE];
        this.gameState = GameState.PLAYING;
        
        // Initialize all cells
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public void setCell(int row, int col, String content) {
        cells[row][col].setContent(content);
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getSize() {
        return SIZE;
    }

    public boolean isCellEmpty(int row, int col) {
        return cells[row][col].isEmpty();
    }

    public boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (cells[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
} 