package com.aditya.snakeandladder.services;

import com.aditya.snakeandladder.models.*;
import java.util.*;

public class GameService {
    private Board board;
    private Dice dice;
    private Queue<Player> playerQueue;
    private boolean isGameOver;

    public GameService(Board board, Dice dice) {
        this.board = board;
        this.dice = dice;
        this.playerQueue = new LinkedList<>(board.getPlayers());
        this.isGameOver = false;
    }

    public void startGame() {
        System.out.println("Starting Snake and Ladder Game!");
        while (!isGameOver) {
            Player currentPlayer = playerQueue.poll();
            System.out.println("\n" + currentPlayer.getName() + "'s turn. Press Enter to roll the dice...");
            new Scanner(System.in).nextLine();
            int roll = dice.roll();
            System.out.println(currentPlayer.getName() + " rolled a " + roll);
            int newPosition = currentPlayer.getPosition() + roll;
            if (newPosition > board.getSize()) {
                System.out.println("Roll exceeds board size. Stay at position " + currentPlayer.getPosition());
            } else {
                newPosition = getNewPositionAfterSnakesAndLadders(newPosition);
                System.out.println(currentPlayer.getName() + " moves to position " + newPosition);
                currentPlayer.setPosition(newPosition);
                if (newPosition == board.getSize()) {
                    System.out.println("\n*** " + currentPlayer.getName() + " wins the game! ***");
                    isGameOver = true;
                    break;
                }
            }
            playerQueue.offer(currentPlayer);
        }
    }

    private int getNewPositionAfterSnakesAndLadders(int position) {
        for (Snake snake : board.getSnakes()) {
            if (snake.getStart() == position) {
                System.out.println("Oops! Bitten by a snake at " + position + ". Go down to " + snake.getEnd());
                return snake.getEnd();
            }
        }
        for (Ladder ladder : board.getLadders()) {
            if (ladder.getStart() == position) {
                System.out.println("Yay! Climbed a ladder at " + position + ". Go up to " + ladder.getEnd());
                return ladder.getEnd();
            }
        }
        return position;
    }
}