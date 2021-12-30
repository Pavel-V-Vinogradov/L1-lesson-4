import java.util.Random;
import java.util.Scanner;

public class CrossZero {
    private static final int SIZE = 3;
    private static final short PLAYERS_COUNT = 2;
    private static final char[] DOT_MARK = new char[PLAYERS_COUNT];
    private static final boolean[] HUMAN = new boolean[PLAYERS_COUNT];
    private static final char DOT_EMPTY = '.';
    private static final char[][] MAP = new char[SIZE][SIZE];
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();
    private static int attemptNumber = 0;

    public static void main(String[] args) {
        initMap();
        printMap();
        boolean gameOver = false;
        while (true) {
            for(int player = 0; player< PLAYERS_COUNT; player++) {
                NextStep(HUMAN[player], player);
                printMap();
                if (isVirtory(DOT_MARK[player])) {
                    System.out.println("Выиграл игрок под номером " + (player + 1)+ " - маркер: " + DOT_MARK[player]);
                    gameOver = true;
                    break;
                }
                if (isMapFull()) {
                    System.out.println("Ничья");
                    gameOver = true;
                    break;
                }
            }
            if(gameOver) {
                break;
            }
        }

        SCANNER.close();
        System.out.println("Конец игры");
    }

    private static boolean isVirtory(char mark) {
        if (++attemptNumber < SIZE) {
            return false;
        }
        for (int i = 0; i < SIZE; i++) {
            int sum1 = 0;
            int sum2 = 0;
            for (int j = 0; j < SIZE; j++) {
                if (mark == MAP[i][j]) {
                    sum1++;
                }
                if (mark == MAP[j][i]) {
                    sum2++;
                }
            }
            if (sum1 == SIZE || sum2 == SIZE) {
                return true;
            }
        }

        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < SIZE; i++) {
            if (mark == MAP[i][i]) {
                sum1++;
            }
            if (mark == MAP[SIZE - i - 1][i]) {
                sum2++;
            }
        }
        if (sum1 == SIZE || sum2 == SIZE) {
            return true;
        }

        return false;
    }


    private static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (MAP[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean invalidStep(int x, int y) {
        return (x < 0 || x >= SIZE || y < 0 || y >= SIZE || MAP[y][x] != DOT_EMPTY);
    }

    private static void NextStep(boolean human, int markerIndex) {
        int x;
        int y;
        System.out.println("Ход игрока под № " +  + (markerIndex + 1) + " - маркер: " + DOT_MARK[markerIndex]);
        do {
            if(human) {
                x = SCANNER.nextInt() - 1;
                y = SCANNER.nextInt() - 1;
            } else {
                x = RANDOM.nextInt(SIZE);
                y = RANDOM.nextInt(SIZE);
            }
        } while (invalidStep(x, y));
        System.out.println("Игрок пошёл " + (x + 1) + ":" + (y + 1));
        MAP[y][x] = DOT_MARK[markerIndex];
    }

    private static void printMap() {
        for (int i = 0; i < SIZE + 1; i++) {
            System.out.print(i + " ");
        }
        System.out.println("");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(MAP[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private static void initMap() {
        char[] markersPool= new char[]{'X', 'O', 'W', 'H', 'M'};
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                MAP[i][j] = DOT_EMPTY;
            }
        }
        for (int i = 0; i < PLAYERS_COUNT && i < markersPool.length; i++){
            DOT_MARK[i] = markersPool[i];
        }
        HUMAN[0] = true;
    }

}
