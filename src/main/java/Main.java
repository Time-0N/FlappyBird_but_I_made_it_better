import java.util.Random;
import java.util.Scanner;

public class Main {

    //coded by Time_ON aka the xXMasterBaiterXx

    public static boolean checkForGameOver(int[][] playField) {
        boolean gameOver = false;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (playField[i][j] == 3) {
                    gameOver = true;
                }
            }
        }
        return gameOver;
    }

    public static void printPlayField(int[][] playField) {
        for (int i = 0; i < 9; i++) {
            System.out.println();
            for (int j = 0; j < 9; j++) {
                if (playField[i][j] == 0) {
                    System.out.print(" " + " " + " ");
                } else if (playField[i][j] == 1) {
                    System.out.print(" " + "*" + " ");
                } else if (playField[i][j] == 2) {
                    System.out.print(" " + "|" + " ");
                } else if (playField[i][j] == 3) {
                    System.out.print(" " + "X" + " ");
                }
            }
        }
        System.out.println();
    }

    public static int[][] obstacle(int[][] playField) {
        Random random = new Random();
        int obstacleGenerator = random.nextInt(6 + 1) + 1;

        for (int i = 0; i < 9; i++) {
            playField[i][8] = 2;
        }
        playField[obstacleGenerator + 1][8] = 0;
        playField[obstacleGenerator][8] = 0;
        playField[obstacleGenerator - 1][8] = 0;

        return playField;
    }

    public static String userInput() {
        Scanner sc = new Scanner(System.in);
        return sc.next().toUpperCase();
    }

    public static int[][] movePlayingField(int[][] playField, int[] player) {

        if (playField[player[0] + player[1]][3] == 1) {
            playField[player[0] + player[1]][3] = 0;
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j != 8) {
                    playField[i][j] += playField[i][j + 1];
                    playField[i][j + 1] = 0;
                }
                if (playField[i][0] > 0) {
                    playField[i][0] = 0;
                }
            }
        }
        if (playField[player[0]][2] == 1) {
            playField[player[0]][2] = 0;
        }

        playField[player[0]][3] += 1;

        return playField;
    }

    public static int[] movePlayer(int[] player) {

        boolean validInput = false;


        while (!validInput) {
            System.out.println("Input: ");
            String input = userInput();

            switch (input) {
                case "W":
                    if (player[0] == 0) {
                        return player;
                    }
                    player[1] = 1;
                    player[0] -= 1;
                    validInput = true;
                    break;
                case "S":
                    if (player[0] == 8) {
                        return player;
                    }
                    player[1] = -1;
                    player[0] += 1;
                    validInput = true;
                    break;
                case "D":
                    player[1] = 0;
                    return player;
                default:
                    System.out.println("Invalid input");
            }
        }
        return player;
    }

    public static void main(String[] args) {
        int player[] = {4,0,0};
        int generateObstacle = 0;
        boolean gameOver = false;

        int[][] playField = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        while(!gameOver) {
            System.out.println("Score: " + player[2]);
            player = movePlayer(player);
            playField = movePlayingField(playField,player);
            if (generateObstacle == 3) {
                playField = obstacle(playField);
                generateObstacle = 0;
                player[2]++;
            }
            generateObstacle++;
            gameOver = checkForGameOver(playField);
            printPlayField(playField);
        }

        System.out.println("G A M E O V E R");
        System.out.println("You finished with score: " + player[2]);
    }
}
