package com.aditya.tictactoe.models;

public class Cell {
    private int row;
    private int col;
    private String content;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.content = " "; // Empty cell
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isEmpty() {
        return content.equals(" ");
    }
} 