package GameXO;

import java.util.Random;

public class LogikGame {

        static char[][] map;
        static int SIZE;
        static int DOTS_TO_WIN;
        public static final char DOT_EMPTY = '.';
        public static final char DOT_X = 'X';
        public static final char DOT_O = 'O';
        public static Random random = new Random();
        public static int x1, y1;
        public static int x2, y2;

        static boolean gamefinished;
        static boolean tie;
        private static String statusMessage;

        public static void go() {
            gamefinished = true;
            tie= false;
             printMap();
                if (checkWin(DOT_X)) {
                    statusMessage = "Вы победили!!!";
                    System.out.println(statusMessage);
                    return;
                }
                if (isMapFull()) {
                    tie = true;
                    statusMessage = "Ничья";
                    System.out.println(statusMessage);
                    return;
                }
                aiTurn();
                printMap();
                if (checkWin(DOT_O)) {
                    statusMessage = "Победил компьютер";
                    System.out.println(statusMessage);
                    return;
                }
                if (isMapFull()) {
                    tie = true;
                    statusMessage = "Ничья";
                    System.out.println(statusMessage);
                    return;
                }
                gamefinished = false;
             }
             public static String getStatusMessage(){
            return statusMessage;
             }

        public static void initMap() {
            map = new char[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    map[i][j] = DOT_EMPTY;
                }
            }
        }

        public static void printMap() {
            System.out.print("y"+"\\"+"x ");
            for (int i = 1; i <= SIZE; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
            for (int i = 0; i < SIZE; i++) {
                System.out.print((i + 1) + "   ");
                for (int j = 0; j < SIZE; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

        public static void humanTurn(int x, int y) {
            if (isCellValid(x, y)) {
                map[y][x] = DOT_X;
                go();
            }
        }

        public static boolean isCellValid(int x, int y) {
            if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
            if (map[y][x] == DOT_EMPTY) return true;
            return false;
        }

        public static void aiTurn() {
            // поиск выигрышной линии
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    double numbStep = Math.pow(SIZE, 2);
                    int k=0;
                    while (k < numbStep && map[i][j]== DOT_EMPTY) {
                        map[i][j] = DOT_O;
                        if (checkWin(DOT_O)) {
                            map[i][j] = DOT_O;
                            return;
                        } else {
                            map[i][j] = DOT_EMPTY;
                        }
                        k++;
                    }
                }
            }
            //блокирование игрока
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    double numbStep = Math.pow(SIZE, 2);
                    int k=0;
                    while (k < numbStep && map[i][j]== DOT_EMPTY) {
                        map[i][j] = DOT_X;
                        if (checkWin(DOT_X)) {
                            map[i][j] = DOT_O;
                            return;
                        } else {
                            map[i][j] = DOT_EMPTY;
                        }
                        k++;
                    }
                }
            }
            //построение удачной линии
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    double numbStep = Math.pow(SIZE, 2);
                    int k=0;
                    while (k < numbStep && map[i][j]== DOT_EMPTY) {
                        if (checkWin(DOT_EMPTY)) {
                            map[i][j] = DOT_O;
                            return;
                        } else {
                            map[i][j] = DOT_EMPTY;
                        }
                        k++;
                    }
                }
            }

            int x, y;
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while (!isCellValid(x, y));
            System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1) + " ");
            map[y][x] = DOT_O;
        }

        public static boolean checkWin(char symb) {
            //проверка диагонали
            for (int m = 0; m <= SIZE - DOTS_TO_WIN; m++) {
                for (int k = 0; k <= SIZE - DOTS_TO_WIN; k++) {
                    int i=0;
                    while (i < DOTS_TO_WIN && map[m+i][k + i] == symb) {

                        i++;
                    }
                    if (i == DOTS_TO_WIN) {
                         y1 = m;
                         y2 =m+DOTS_TO_WIN-1;
                         x1 = k;
                         x2 = k+DOTS_TO_WIN-1;
                        return true;
                    }
                }
            }

            //проверка диагонали 2
            for (int m = DOTS_TO_WIN-1; m < SIZE; m++) {
                for (int k = 0; k <= SIZE - DOTS_TO_WIN; k++) {
                    int i=0;
                    while (i < DOTS_TO_WIN && map[m-i][i + k] == symb) {
                        i++;
                    }
                    if (i == DOTS_TO_WIN) {
                         x1 = k;
                         x2 =k+DOTS_TO_WIN-1;
                         y1 = m;
                         y2 = m-DOTS_TO_WIN+1;
                        return true;
                    }
                }
            }

            // проверка по строке
            for (int m = 0; m <= SIZE - DOTS_TO_WIN; m++) {
                for (int i = 0; i < SIZE; i++) {
                    int j = 0;
                    while (j < DOTS_TO_WIN && map[i][j + m] == symb) {
                        j++;
                    }
                    if (j == DOTS_TO_WIN) {
                         x1 = m;
                         x2 = m+DOTS_TO_WIN-1;
                         y1 =i;
                         y2 =i;
                        return true;
                    }
                }
            }

            // проверка по столбцу
            for (int m = 0; m <= SIZE - DOTS_TO_WIN; m++) {
                for (int j = 0; j < SIZE; j++) {
                    int i = 0;
                    while (i < DOTS_TO_WIN && map[i + m][j] == symb) {
                        i++;
                    }
                    if (i == DOTS_TO_WIN) {
                        x1 =j;
                        x2 =j;
                        y1 =m;
                        y2 =m+DOTS_TO_WIN-1;
                        return true;
                    }
                }
            }
            return false;
        }

        public static boolean isMapFull() {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (map[i][j] == DOT_EMPTY) return false;
                }
            }
            return true;
        }
    }


