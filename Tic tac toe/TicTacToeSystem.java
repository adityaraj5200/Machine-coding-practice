import com.aditya.tictactoe.models.*;
import com.aditya.tictactoe.services.GameService;
import java.util.*;

public class TicTacToeSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("========================\n");
        
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
        System.out.println("- Enter row (0-2) and column (0-2) to make a move");
        System.out.println("- First player to get 3 in a row (horizontally, vertically, or diagonally) wins");
        System.out.println("- If all cells are filled without a winner, it's a draw");
        System.out.println("\nBoard Layout:");
        System.out.println("-------------");
        System.out.println("| 0,0 | 0,1 | 0,2 |");
        System.out.println("-------------");
        System.out.println("| 1,0 | 1,1 | 1,2 |");
        System.out.println("-------------");
        System.out.println("| 2,0 | 2,1 | 2,2 |");
        System.out.println("-------------");
        
        // Start the game
        GameService gameService = new GameService(player1, player2);
        gameService.startGame();
        
        scanner.close();
    }
} 