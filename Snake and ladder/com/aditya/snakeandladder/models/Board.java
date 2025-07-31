package com.aditya.snakeandladder.models;

import java.util.List;

public class Board {
    private int size;
    private List<Snake> snakes;
    private List<Ladder> ladders;
    private List<Player> players;

    public Board(int size, List<Snake> snakes, List<Ladder> ladders, List<Player> players) {
        this.size = size;
        this.snakes = snakes;
        this.ladders = ladders;
        this.players = players;
    }

    public int getSize() {
        return size;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }

    public List<Player> getPlayers() {
        return players;
    }
}