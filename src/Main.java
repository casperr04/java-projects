import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void renderGame(String[][] grid) {
        System.out.println();
        System.out.print(grid[0][0] + "|" + grid[0][1] + "|" + grid[0][2]);
        System.out.print("\n-----\n");
        System.out.print(grid[1][0] + "|" + grid[1][1] + "|" + grid[1][2]);
        System.out.print("\n-----\n");
        System.out.print(grid[2][0] + "|" + grid[2][1] + "|" + grid[2][2]);
        System.out.print("\n\n\n");
    }

    public static int startGame() {
        int decision = 0;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Hello, welcome to Java Tic-Tac Toe!");
            System.out.println("Please select your option below");
            System.out.println("Start Game: 1");
            System.out.println("Exit: 2");
            try {
                decision = Integer.parseInt(scanner.nextLine());
            } catch (Exception NumberFormatException) {
                System.out.println("Invalid input, please try again!");
                continue;
            }
            break;
        }
        return decision;
    }

    public static void getRandomChoice(String[][] grid) {
        Random rand = new Random();
        boolean selected = false;
        while (!selected) {
            int x = rand.nextInt(0, 3);
            int y = rand.nextInt(0, 3);
            String randgrid = grid[x][y];
            if (randgrid.equals(" ")) {
                grid[x][y] = "O";
                selected = true;
            }
        }
    }

    /**
     * <pre>
     * Returns an int depending on any wins
     * 0 = No wins
     * 1 = PC win
     * 2 = Player win
     * </pre>
     */
    public static int checkWins(String[][] grid) {
        //Check for columns
        for (int row = 0; row < 3; row++) {
            if (grid[row][0].equals("O") && grid[row][1].equals("O") && grid[row][2].equals("O")) {
                return 1;
            } else if (grid[row][0].equals("X") && grid[row][1].equals("X") && grid[row][2].equals("X")) {
                return 2;
            }
        }
        //Check for rows
        for (int col = 0; col < 3; col++) {
            if (grid[0][col].equals("O") && grid[1][col].equals("O") && grid[2][col].equals("O")) {
                return 1;
            } else if (grid[0][col].equals("X") && grid[1][col].equals("X") && grid[2][col].equals("X")) {
                return 2;
            }
        }
        // Check diagonals
        if (grid[0][0].equals("O") && grid[1][1].equals("O") && grid[2][2].equals("O") || grid[2][0].equals("O") && grid[1][1].equals("O") && grid[0][2].equals("O")) {
            return 1;
        } else if (grid[0][0].equals("X") && grid[1][1].equals("X") && grid[2][2].equals("X") || grid[0][2].equals("X") && grid[1][1].equals("X") && grid[2][0].equals("X")) {
            return 2;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] grid = new String[3][3];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = " ";
            }
        }
        // Game starts here
        int decision = startGame();
        if (decision == 1) {
            int turn = 0;
            while (turn <= 4) {
                turn++;
                renderGame(grid);
                while (true) {
                    System.out.println("Select row (0 - 2)");
                    int rowInput;
                    try {
                        rowInput = Integer.parseInt(scanner.nextLine());
                    } catch (Exception NumberFormatException) {
                        System.out.println("Invalid input, please try again!");
                        continue;
                    }
                    System.out.println("Select column (0 - 2)");
                    int colInput;
                    try {
                        colInput = Integer.parseInt(scanner.nextLine());
                    } catch (Exception NumberFormatException) {
                        System.out.println("Invalid input, please try again!");
                        continue;
                    }
                    String selectedGrid;
                    int col = colInput;
                    int row = rowInput;
                    try {
                        selectedGrid = grid[row][col];
                    } catch (Exception ArrayIndexOutOfBoundsException) {
                        System.out.println("Invalid input, please try again!");
                        renderGame(grid);
                        continue;
                    }
                    if (selectedGrid.equals(" ")) {
                        grid[row][col] = "X";
                        break;
                    } else {
                        System.out.println("Invalid input, select another spot.");
                        renderGame(grid);
                    }
                }
                if (turn < 5) {
                    getRandomChoice(grid);
                }
                if (checkWins(grid) == 1) {
                    renderGame(grid);
                    System.out.println("PC Wins!");
                    break;
                } else if (checkWins(grid) == 2) {
                    renderGame(grid);
                    System.out.println("Player wins!");
                    break;
                }
            }
            if (turn == 5) {
                System.out.println();
                System.out.println("It's a draw!");
            }
        }
        System.out.println("Thanks for playing.");
    }
}
