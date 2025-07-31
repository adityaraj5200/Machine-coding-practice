package com.aditya.tictactoe.models;

public class Board {
    private Cell[][] cells;
    private GameState gameState;
    private int size;
    private int movesCount; // Track total moves for quick draw detection

    public Board(int size) {
        this.size = size;
        this.cells = new Cell[size][size];
        this.gameState = GameState.PLAYING;
        this.movesCount = 0;
        
        // Initialize all cells
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public void setCell(int row, int col, String content) {
        cells[row][col].setContent(content);
        movesCount++;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getSize() {
        return size;
    }

    public boolean isCellEmpty(int row, int col) {
        return cells[row][col].isEmpty();
    }

    public boolean isBoardFull() {
        return movesCount == size * size;
    }

    public int getMovesCount() {
        return movesCount;
    }
} 