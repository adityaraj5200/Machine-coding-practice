import com.aditya.snakeandladder.models.*;
import com.aditya.snakeandladder.services.GameService;
import java.util.*;

public class SnakeAndLadderSystem {
    public static void main(String[] args) {
        // Setup board size
        int boardSize = 100;

        // Setup snakes
        List<Snake> snakes = Arrays.asList(
            new Snake(99, 7),
            new Snake(70, 55),
            new Snake(52, 42),
            new Snake(25, 2),
            new Snake(95, 72)
        );

        // Setup ladders
        List<Ladder> ladders = Arrays.asList(
            new Ladder(3, 22),
            new Ladder(5, 8),
            new Ladder(11, 26),
            new Ladder(20, 29),
            new Ladder(27, 56)
        );

        // Setup players
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // consume newline
        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String name = scanner.nextLine();
            players.add(new Player(name));
        }

        // Setup board and dice
        Board board = new Board(boardSize, snakes, ladders, players);
        Dice dice = new Dice(6);

        // Start game
        GameService gameService = new GameService(board, dice);
        gameService.startGame();
    }
}