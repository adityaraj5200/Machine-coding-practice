import com.aditya.tictactoe.models.*;
import com.aditya.tictactoe.services.GameService;
import java.util.*;

public class TicTacToeSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("========================\n");
        
        // Get board size
        int boardSize = getBoardSize(scanner);
        
        // Get player names
        System.out.print("Enter name for Player 1 (X): ");
        String player1Name = scanner.nextLine();
        
        System.out.print("Enter name for Player 2 (O): ");
        String player2Name = scanner.nextLine();
        
        // Create players
        Player player1 = new Player(player1Name, "X");
        Player player2 = new Player(player2Name, "O");
        
        // Display game instructions
        System.out.println("\nGame Instructions:");
        System.out.println("- Players take turns placing their symbol (X or O)");
        System.out.println("- Enter row (0-" + (boardSize - 1) + ") and column (0-" + (boardSize - 1) + ") to make a move");
        System.out.println("- First player to get " + boardSize + " in a row (horizontally, vertically, or diagonally) wins");
        System.out.println("- If all cells are filled without a winner, it's a draw");
        System.out.println("\nBoard Layout (" + boardSize + "x" + boardSize + "):");
        displayBoardLayout(boardSize);
        
        // Start the game
        GameService gameService = new GameService(player1, player2, boardSize);
        gameService.startGame();
        
        scanner.close();
    }
    
    private static int getBoardSize(Scanner scanner) {
        int boardSize;
        do {
            System.out.print("Enter board size (3-10 recommended): ");
            boardSize = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            if (boardSize < 3) {
                System.out.println("Board size must be at least 3. Please try again.");
            } else if (boardSize > 15) {
                System.out.println("Board size too large. Please enter a size between 3 and 15.");
            }
        } while (boardSize < 3 || boardSize > 15);
        
        return boardSize;
    }
    
    private static void displayBoardLayout(int size) {
        // Print top border
        for (int i = 0; i < size; i++) {
            System.out.print("----");
        }
        System.out.println("-");
        
        for (int i = 0; i < size; i++) {
            System.out.print("| ");
            for (int j = 0; j < size; j++) {
                System.out.print(i + "," + j + " | ");
            }
            System.out.println();
            if (i < size - 1) {
                for (int k = 0; k < size; k++) {
                    System.out.print("----");
                }
                System.out.println("-");
            }
        }
        
        // Print bottom border
        for (int i = 0; i < size; i++) {
            System.out.print("----");
        }
        System.out.println("-");
    }
} 