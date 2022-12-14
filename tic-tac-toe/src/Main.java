import chess.CheckResult;

import java.util.Random;
import java.util.Scanner;

import static chess.CheckResult.*;

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
    public static CheckResult checkWins(String[][] grid) {
        //Check for columns
        for (int row = 0; row < 3; row++) {
            if (isPlayerWinningRow(grid, row, "O")) {
                return PC_WIN;
            } else if (isPlayerWinningRow(grid, row, "X")) {
                return PLAYER_WIN;
            }
        }
        //Check for rows
        for (int col = 0; col < 3; col++) {
            if (isPlayerWinningColumn(grid, col, "O")) {
                return PC_WIN;
            } else if (isPlayerWinningColumn(grid, col, "X")) {
                return PLAYER_WIN;
            }
        }
        // Check diagonals
        if (isPlayerWinningDiagonals(grid, "O")) {
            return PC_WIN;
        } else if (isPlayerWinningDiagonals(grid, "X")) {
            return PLAYER_WIN;
        }
        return NO_WIN;
    }

    static boolean isPlayerWinningRow(String [][] grid, int row, String playerType) {
        return grid[row][0].equals(playerType) && grid[row][1].equals(playerType) && grid[row][2].equals(playerType);
    }

    static boolean isPlayerWinningColumn(String [][] grid, int col, String playerType) {
        return grid[0][col].equals(playerType) && grid[1][col].equals(playerType) && grid[2][col].equals(playerType);
    }

    static boolean isPlayerWinningDiagonals(String [][] grid, String playerType) {
        return (grid[0][0].equals(playerType)
                && grid[1][1].equals(playerType)
                && grid[2][2].equals(playerType))
                ||
                (grid[2][0].equals(playerType)
                && grid[1][1].equals(playerType)
                && grid[0][2].equals(playerType));
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
                if (checkWins(grid) == PC_WIN) {
                    renderGame(grid);
                    System.out.println("PC Wins!");
                    break;
                } else if (checkWins(grid) == PLAYER_WIN) {
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
