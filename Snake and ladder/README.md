# Snake and Ladder Game

A complete CLI-based implementation of the classic Snake and Ladder game, designed with clean architecture and modular design principles.

## Project Structure

```
Snake and ladder/
├── com/aditya/snakeandladder/
│   ├── models/
│   │   ├── Player.java          # Player entity with name and position
│   │   ├── Snake.java           # Snake entity with start and end positions
│   │   ├── Ladder.java          # Ladder entity with start and end positions
│   │   ├── Dice.java            # Dice entity with roll functionality
│   │   └── Board.java           # Board entity containing game state
│   └── services/
│       └── GameService.java     # Core game logic and flow management
├── SnakeAndLadderSystem.java    # Main entry point
└── README.md                    # This file
```

## Features

### Core Game Features
- **Multi-player Support**: Support for 2 or more players
- **Customizable Board**: 100-cell board with predefined snakes and ladders
- **Dice Rolling**: 6-sided dice with random number generation
- **Snake Mechanics**: Landing on snake head moves player down to tail
- **Ladder Mechanics**: Landing on ladder bottom moves player up to top
- **Win Condition**: First player to reach exactly position 100 wins
- **Boundary Check**: Players stay in place if roll exceeds board size

### Technical Features
- **Clean Architecture**: Separation of concerns with models and services
- **Modular Design**: Each component has a single responsibility
- **CLI Interface**: Interactive command-line user experience
- **Extensible**: Easy to add new features or modify game rules

## Game Rules

1. **Starting Position**: All players start at position 0
2. **Turn Order**: Players take turns in the order they were added
3. **Dice Roll**: Each turn, player rolls a 6-sided dice
4. **Movement**: Player moves forward by the number rolled
5. **Snakes**: If player lands on a snake's head, they move down to the snake's tail
6. **Ladders**: If player lands on a ladder's bottom, they climb up to the ladder's top
7. **Boundary**: If roll would exceed position 100, player stays in place
8. **Winning**: First player to reach exactly position 100 wins

## Predefined Snakes and Ladders

### Snakes (Head → Tail)
- Position 99 → 7
- Position 70 → 55
- Position 52 → 42
- Position 25 → 2
- Position 95 → 72

### Ladders (Bottom → Top)
- Position 3 → 22
- Position 5 → 8
- Position 11 → 26
- Position 20 → 29
- Position 27 → 56

## How to Run

### Prerequisites
- Java 8 or higher
- Command line access

### Compilation and Execution

1. **Navigate to the project directory**:
   ```bash
   cd "Snake and ladder"
   ```

2. **Compile all Java files**:
   ```bash
   javac -cp . com/aditya/snakeandladder/models/*.java
   javac -cp . com/aditya/snakeandladder/services/*.java
   javac -cp . SnakeAndLadderSystem.java
   ```

3. **Run the game**:
   ```bash
   java SnakeAndLadderSystem
   ```

### Game Flow

1. **Setup Phase**:
   - Enter the number of players
   - Enter names for each player

2. **Game Phase**:
   - Each player's turn is displayed
   - Press Enter to roll the dice
   - Game shows dice result and new position
   - If snake/ladder encountered, shows the effect
   - Continues until a player wins

3. **End Game**:
   - Winner is announced
   - Game terminates

## Design Decisions

### Architecture
- **Model-View-Controller Pattern**: Models for data, Services for logic
- **Single Responsibility**: Each class has one clear purpose
- **Dependency Injection**: Services receive dependencies through constructor

### Code Organization
- **Package Structure**: Follows Java conventions with clear separation
- **Naming Conventions**: Consistent naming across all classes
- **Documentation**: Clear comments and self-documenting code

### Game Logic
- **Queue-based Turn Management**: Fair turn rotation using LinkedList
- **Position Validation**: Prevents invalid moves beyond board boundaries
- **Immediate Effect**: Snakes and ladders are applied immediately upon landing

## Extensibility

The design allows for easy extension:

### Adding New Features
- **Custom Board Sizes**: Modify `boardSize` in main method
- **Different Dice**: Create new Dice implementation
- **More Snakes/Ladders**: Add to the predefined lists
- **Special Rules**: Extend GameService with new logic

### Potential Enhancements
- **GUI Interface**: Replace CLI with graphical interface
- **Save/Load Games**: Add persistence layer
- **Statistics Tracking**: Track player performance
- **Custom Rules**: Add special squares or power-ups

## Example Game Session

```
Enter number of players: 2
Enter name for Player 1: Alice
Enter name for Player 2: Bob

Starting Snake and Ladder Game!

Alice's turn. Press Enter to roll the dice...
Alice rolled a 4
Alice moves to position 4

Bob's turn. Press Enter to roll the dice...
Bob rolled a 6
Bob moves to position 6

Alice's turn. Press Enter to roll the dice...
Alice rolled a 3
Yay! Climbed a ladder at 7. Go up to 22
Alice moves to position 22

...

*** Alice wins the game! ***
```

## Testing

The implementation includes several test scenarios:
- **Basic Movement**: Players move according to dice rolls
- **Snake Effects**: Players move down when landing on snake heads
- **Ladder Effects**: Players move up when landing on ladder bottoms
- **Boundary Conditions**: Players stay in place when roll exceeds 100
- **Win Condition**: Game ends when player reaches position 100

## Contributing

This is a learning project demonstrating clean code principles and object-oriented design. Feel free to:
- Add new features
- Improve the documentation
- Optimize the code
- Add unit tests

## License

This project is for educational purposes and practice. 