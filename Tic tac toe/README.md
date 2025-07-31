# Tic Tac Toe Game

A complete CLI-based implementation of the classic Tic Tac Toe game, designed with clean architecture and modular design principles. Now supports NxN boards with optimized time complexity and robust error handling.

## Project Structure

```
Tic tac toe/
├── com/aditya/tictactoe/
│   ├── models/
│   │   ├── Player.java          # Player entity with name and symbol
│   │   ├── Board.java           # NxN game board with cells
│   │   ├── Cell.java            # Individual cell on the board
│   │   └── GameState.java       # Game state enum (PLAYING, WIN, DRAW)
│   └── services/
│       ├── GameService.java     # Core game logic and flow management
│       └── BoardService.java    # Board operations and optimized win detection
├── TicTacToeSystem.java         # Main entry point
└── README.md                    # This file
```

## Features

### Core Game Features
- **NxN Board Support**: Configurable board size (3x3 to 15x15)
- **Two-Player Game**: Support for two human players
- **Turn-Based Play**: Players take turns placing X and O
- **Optimized Win Detection**: O(n) time complexity for win checking
- **Efficient Draw Detection**: O(1) time complexity using move counter
- **Robust Input Validation**: Comprehensive error handling for invalid moves
- **Occupied Cell Handling**: Prevents players from placing on already occupied cells
- **Dynamic Board Display**: Adapts to any board size

### Technical Features
- **Clean Architecture**: Separation of concerns with models and services
- **Modular Design**: Each component has a single responsibility
- **CLI Interface**: Interactive command-line user experience
- **Extensible**: Easy to add new features or modify game rules
- **Optimized Performance**: Minimal time complexity for all operations
- **Error Recovery**: Graceful handling of invalid inputs with retry logic

## Performance Optimizations

### Time Complexity Improvements
- **Win Detection**: Reduced from O(n²) to O(n) by only checking relevant lines after each move
- **Draw Detection**: Reduced from O(n²) to O(1) using a move counter
- **Board Display**: O(n²) but optimized with early termination
- **Move Validation**: O(1) constant time validation
- **Occupied Cell Check**: O(1) constant time check

### Space Complexity
- **Board Storage**: O(n²) for NxN board
- **Win Checking**: O(1) additional space for optimized algorithm

## Game Rules

1. **Starting**: Player 1 (X) goes first, followed by Player 2 (O)
2. **Board Size**: Configurable NxN grid (3x3 to 15x15 recommended)
3. **Moves**: Players enter row (0 to N-1) and column (0 to N-1) to place their symbol
4. **Winning**: First player to get N symbols in a row (horizontally, vertically, or diagonally) wins
5. **Draw**: If all N² cells are filled without a winner, the game is a draw
6. **Invalid Moves**: Players cannot place symbols in already occupied cells
7. **Error Handling**: Invalid moves are rejected with clear error messages and retry prompts

## Error Handling

### Invalid Input Scenarios
- **Out of Bounds**: Coordinates outside the board range
- **Occupied Cells**: Attempting to place on already filled cells
- **Invalid Format**: Non-numeric input for coordinates

### Error Recovery
- **Clear Messages**: Specific error messages for each type of invalid input
- **Retry Logic**: Automatic prompting for new input after errors
- **Board Display**: Shows current board state after invalid moves
- **Graceful Recovery**: Continues game flow without crashes

## Board Layout Example (4x4)

```
-----------------
| 0,0 | 0,1 | 0,2 | 0,3 |
-----------------
| 1,0 | 1,1 | 1,2 | 1,3 |
-----------------
| 2,0 | 2,1 | 2,2 | 2,3 |
-----------------
| 3,0 | 3,1 | 3,2 | 3,3 |
-----------------
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
   - Enter board size (3-15 recommended)
   - Enter names for Player 1 (X) and Player 2 (O)
   - View game instructions and dynamic board layout

2. **Game Phase**:
   - Current board state is displayed
   - Current player's turn is shown
   - Player enters row and column coordinates
   - Move is validated with comprehensive error checking
   - Invalid moves are rejected with clear error messages
   - Win/draw conditions are checked using optimized algorithms
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

### Performance Optimizations
- **Smart Win Detection**: Only check relevant lines after each move
- **Move Counter**: Track total moves for O(1) draw detection
- **Early Termination**: Break loops as soon as win condition is found
- **Dynamic Validation**: Adapt input validation to board size

### Error Handling Strategy
- **Defensive Programming**: Validate all inputs before processing
- **User-Friendly Messages**: Clear, specific error messages
- **Retry Logic**: Allow users to correct mistakes without restarting
- **State Preservation**: Maintain game state during error recovery

## Algorithm Analysis

### Win Detection Algorithm
```java
// Optimized win detection - O(n) time complexity
public boolean checkWin(Board board, String symbol, int lastRow, int lastCol) {
    // Only check the row, column, and diagonals that include the last move
    // This reduces time complexity from O(n²) to O(n)
    
    // Check row: O(n)
    // Check column: O(n) 
    // Check diagonals: O(n) only if move is on diagonal
}
```

### Draw Detection Algorithm
```java
// O(1) time complexity using move counter
public boolean isBoardFull() {
    return movesCount == size * size;
}
```

### Move Validation Algorithm
```java
// O(1) time complexity for comprehensive validation
public boolean isValidMove(Board board, int row, int col) {
    // Check bounds: O(1)
    // Check if occupied: O(1)
    // Return validation result
}
```

## Extensibility

The design allows for easy extension:

### Adding New Features
- **Larger Boards**: Already supports up to 15x15 (configurable)
- **AI Players**: Add computer player logic with minimax algorithm
- **Score Tracking**: Track wins across multiple games
- **Custom Symbols**: Allow players to choose their symbols
- **Tournament Mode**: Support for multiple games

### Potential Enhancements
- **GUI Interface**: Replace CLI with graphical interface
- **Network Play**: Add multiplayer over network
- **Game History**: Save and replay previous games
- **Difficulty Levels**: Add AI with different difficulty levels
- **Custom Win Conditions**: Support for different win patterns

## Example Game Session

```
Welcome to Tic Tac Toe!
========================

Enter board size (3-10 recommended): 4
Enter name for Player 1 (X): Alice
Enter name for Player 2 (O): Bob

Game Instructions:
- Players take turns placing their symbol (X or O)
- Enter row (0-3) and column (0-3) to make a move
- First player to get 4 in a row (horizontally, vertically, or diagonally) wins
- If all cells are filled without a winner, it's a draw

Board Layout (4x4):
-----------------
| 0,0 | 0,1 | 0,2 | 0,3 |
-----------------
| 1,0 | 1,1 | 1,2 | 1,3 |
-----------------
| 2,0 | 2,1 | 2,2 | 2,3 |
-----------------
| 3,0 | 3,1 | 3,2 | 3,3 |
-----------------

Starting Tic Tac Toe Game (4x4)!
Alice (X) vs Bob (O)

Current Board:
-----------------
|   |   |   |   |
-----------------
|   |   |   |   |
-----------------
|   |   |   |   |
-----------------
|   |   |   |   |
-----------------

Alice's turn (X)
Enter row (0-3): 1
Enter column (0-3): 1

Current Board:
-----------------
|   |   |   |   |
-----------------
|   | X |   |   |
-----------------
|   |   |   |   |
-----------------
|   |   |   |   |
-----------------

Bob's turn (O)
Enter row (0-3): 1
Enter column (0-3): 1
Cell (1,1) is already occupied by 'X'! Please choose an empty cell.
Current board state:

Current Board:
-----------------
|   |   |   |   |
-----------------
|   | X |   |   |
-----------------
|   |   |   |   |
-----------------
|   |   |   |   |
-----------------

Bob's turn (O)
Enter row (0-3): 0
Enter column (0-3): 0

...

*** Alice wins! ***
```

## Testing

The implementation includes several test scenarios:
- **Basic Gameplay**: Players can make valid moves on any board size
- **Win Detection**: All winning combinations detected with O(n) complexity
- **Draw Detection**: Game correctly identifies draws in O(1) time
- **Input Validation**: Invalid moves are rejected for any board size
- **Boundary Conditions**: Out-of-bounds inputs are handled
- **Occupied Cell Handling**: Proper error messages when placing on occupied cells
- **Error Recovery**: Graceful handling of all invalid input scenarios
- **Performance**: Large boards (10x10+) tested for performance

## Contributing

This is a learning project demonstrating clean code principles, object-oriented design, algorithmic optimization, and robust error handling. Feel free to:
- Add new features
- Improve the documentation
- Optimize the code further
- Add unit tests
- Implement AI players

## License

This project is for educational purposes and practice. 