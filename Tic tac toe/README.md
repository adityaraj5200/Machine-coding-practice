# Tic Tac Toe Game

A complete CLI-based implementation of the classic Tic Tac Toe game, designed with clean architecture and modular design principles.

## Project Structure

```
Tic tac toe/
├── com/aditya/tictactoe/
│   ├── models/
│   │   ├── Player.java          # Player entity with name and symbol
│   │   ├── Board.java           # 3x3 game board with cells
│   │   ├── Cell.java            # Individual cell on the board
│   │   └── GameState.java       # Game state enum (PLAYING, WIN, DRAW)
│   └── services/
│       ├── GameService.java     # Core game logic and flow management
│       └── BoardService.java    # Board operations and win detection
├── TicTacToeSystem.java         # Main entry point
└── README.md                    # This file
```

## Features

### Core Game Features
- **Two-Player Game**: Support for two human players
- **3x3 Board**: Classic 3x3 grid layout
- **Turn-Based Play**: Players take turns placing X and O
- **Win Detection**: Automatic detection of winning combinations
- **Draw Detection**: Detects when the game ends in a draw
- **Input Validation**: Ensures valid moves and prevents invalid inputs
- **Visual Board Display**: Clear ASCII representation of the game board

### Technical Features
- **Clean Architecture**: Separation of concerns with models and services
- **Modular Design**: Each component has a single responsibility
- **CLI Interface**: Interactive command-line user experience
- **Extensible**: Easy to add new features or modify game rules
- **Input Validation**: Robust error handling for user inputs

## Game Rules

1. **Starting**: Player 1 (X) goes first, followed by Player 2 (O)
2. **Board Layout**: 3x3 grid with positions labeled (0,0) to (2,2)
3. **Moves**: Players enter row (0-2) and column (0-2) to place their symbol
4. **Winning**: First player to get 3 symbols in a row (horizontally, vertically, or diagonally) wins
5. **Draw**: If all 9 cells are filled without a winner, the game is a draw
6. **Invalid Moves**: Players cannot place symbols in already occupied cells

## Board Layout

```
-------------
| 0,0 | 0,1 | 0,2 |
-------------
| 1,0 | 1,1 | 1,2 |
-------------
| 2,0 | 2,1 | 2,2 |
-------------
```

## How to Run

### Prerequisites
- Java 8 or higher
- Command line access

### Compilation and Execution

1. **Navigate to the project directory**:
   ```bash
   cd "Tic tac toe"
   ```

2. **Compile all Java files**:
   ```bash
   javac -cp . com/aditya/tictactoe/models/*.java
   javac -cp . com/aditya/tictactoe/services/*.java
   javac -cp . TicTacToeSystem.java
   ```

3. **Run the game**:
   ```bash
   java TicTacToeSystem
   ```

### Game Flow

1. **Setup Phase**:
   - Enter names for Player 1 (X) and Player 2 (O)
   - View game instructions and board layout

2. **Game Phase**:
   - Current board state is displayed
   - Current player's turn is shown
   - Player enters row and column coordinates
   - Move is validated and applied
   - Win/draw conditions are checked
   - Players alternate turns until game ends

3. **End Game**:
   - Winner is announced or draw is declared
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
- **State Management**: GameState enum tracks current game status
- **Board Representation**: 2D array of Cell objects for clean data structure
- **Win Detection**: Comprehensive checking of rows, columns, and diagonals
- **Input Validation**: Robust error handling for user inputs

## Extensibility

The design allows for easy extension:

### Adding New Features
- **Larger Boards**: Modify Board class to support NxN grids
- **AI Players**: Add computer player logic
- **Score Tracking**: Track wins across multiple games
- **Custom Symbols**: Allow players to choose their symbols

### Potential Enhancements
- **GUI Interface**: Replace CLI with graphical interface
- **Network Play**: Add multiplayer over network
- **Game History**: Save and replay previous games
- **Difficulty Levels**: Add AI with different difficulty levels

## Example Game Session

```
Welcome to Tic Tac Toe!
========================

Enter name for Player 1 (X): Alice
Enter name for Player 2 (O): Bob

Game Instructions:
- Players take turns placing their symbol (X or O)
- Enter row (0-2) and column (0-2) to make a move
- First player to get 3 in a row (horizontally, vertically, or diagonally) wins
- If all cells are filled without a winner, it's a draw

Board Layout:
-------------
| 0,0 | 0,1 | 0,2 |
-------------
| 1,0 | 1,1 | 1,2 |
-------------
| 2,0 | 2,1 | 2,2 |
-------------

Starting Tic Tac Toe Game!
Alice (X) vs Bob (O)

Current Board:
-------------
|   |   |   |
-------------
|   |   |   |
-------------
|   |   |   |
-------------

Alice's turn (X)
Enter row (0-2): 1
Enter column (0-2): 1

Current Board:
-------------
|   |   |   |
-------------
|   | X |   |
-------------
|   |   |   |
-------------

Bob's turn (O)
Enter row (0-2): 0
Enter column (0-2): 0

...

*** Alice wins! ***
```

## Testing

The implementation includes several test scenarios:
- **Basic Gameplay**: Players can make valid moves
- **Win Detection**: All winning combinations are detected
- **Draw Detection**: Game correctly identifies draws
- **Input Validation**: Invalid moves are rejected
- **Boundary Conditions**: Out-of-bounds inputs are handled

## Contributing

This is a learning project demonstrating clean code principles and object-oriented design. Feel free to:
- Add new features
- Improve the documentation
- Optimize the code
- Add unit tests

## License

This project is for educational purposes and practice. 