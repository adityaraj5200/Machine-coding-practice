package com.aditya.snakeandladder.models;

import java.util.Random;

public class Dice {
    private int faces;
    private Random random;

    public Dice(int faces) {
        this.faces = faces;
        this.random = new Random();
    }

    public int roll() {
        return 1 + random.nextInt(faces);
    }
}