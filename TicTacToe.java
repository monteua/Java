package tictactoe;

import java.util.Scanner;

public class TicTacToe {
    static boolean xWins;
    static boolean oWins;
    static boolean isValid;
    static boolean isFinished;

    public static char[][] getFieldMatrix(String cells) {
        char[][] fieldMatrix = new char[3][3];

        int pos = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fieldMatrix[i][j] = cells.charAt(pos);
                pos++;
            }
        }
        return fieldMatrix;
    }

    public static boolean isValid(char[][] matrix) {
        int countX = 0;
        int countO = 0;

        for (char[] row: matrix) {
            for (char ch: row) {
                if (ch == 'X') {
                    countX++;
                } else if (ch == 'O') {
                    countO++;
                }
            }
        }

        if (Math.abs(countX - countO) > 1) {
            return false;
        }

        return true;
    }

    public static boolean isFinished(char[][] matrix) {
        for (char[] ch: matrix) {
            if (ch[0] == '_' || ch[1] == '_' || ch[2] == '_') {
                return false;
            }
        }
        return true;
    }

    public static void getResult(char[][] matrix) {
        isValid = isValid(matrix);
        isFinished = isFinished(matrix);

        // check horizontal rows
        for (char[] row: matrix) {
            if (row[0] == 'X' && row[1] == 'X' && row[2] == 'X') {
                xWins = true;
            } else if (row[0] == 'O' && row[1] == 'O' && row[2] == 'O') {
                oWins = true;
            }
        }

        // check vertical rows
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[0][i] == 'X' && matrix[1][i] == 'X' && matrix[2][i] == 'X') {
                xWins = true;
            } else if (matrix[0][i] == 'O' && matrix[1][i] == 'O' && matrix[2][i] == 'O') {
                oWins = true;
            }
        }

        // check diagonal rows
        if (matrix[0][0] == 'X' && matrix[1][1] == 'X' && matrix[2][2] == 'X') {
            xWins = true;
        } else if (matrix[0][0] == 'O' && matrix[1][1] == 'O' && matrix[2][2] == 'O') {
            oWins = true;
        } else if (matrix[2][0] == 'X' && matrix[1][1] == 'X' && matrix[0][2] == 'X') {
            xWins = true;
        } else if (matrix[2][0] == 'O' && matrix[1][1] == 'O' && matrix[0][2] == 'O') {
            oWins = true;
        }

        if (!xWins && !oWins && isFinished) {
            System.out.println("Draw");
            isFinished = true;
        } else if (xWins && oWins || !isValid) {
            System.out.println("Impossible");
        } else if (xWins && isValid) {
            System.out.println("X wins");
            isFinished = true;
        } else if (oWins && isValid) {
            System.out.println("O wins");
            isFinished = true;
        }
    }

    public static String getRow(char[] rawRow) {
        String[] row = {
                "|",
                String.valueOf(rawRow[0]),
                String.valueOf(rawRow[1]),
                String.valueOf(rawRow[2]),
                "|"
        };

        return String.join(" ", row);
    }

    public static void printTable(char[][] matrix) {
        System.out.println("---------");
        for (char[] row: matrix) {
            System.out.println(getRow(row));
        }
        System.out.println("---------");
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] matrix = getFieldMatrix("_________");
        printTable(matrix);

        int currentPlayer = 0;
        while (!isFinished) {
            boolean isCoordinatesCorrect = false;
            int x = 0;
            int y = 0;

            while (!isCoordinatesCorrect) {
                System.out.print("Enter the coordinates: ");
                String[] coords = scanner.nextLine().split(" ");

                try {
                    x = Integer.parseInt(coords[0]) - 1;
                    y = Integer.parseInt(coords[1]) - 1;

                    if (!(x >= 0 && x < 3 && y >= 0 && y < 3)) {
                        isCoordinatesCorrect = false;
                        System.out.println("Coordinates should be from 1 to 3!");
                        continue;
                    } else {
                        if (matrix[2 - y][x] == '_') {
                            isCoordinatesCorrect = true;
                        } else {
                            isCoordinatesCorrect = false;
                            System.out.println("This cell is occupied! Choose another one!");
                        }

                    }
                } catch (NumberFormatException e) {
                    isCoordinatesCorrect = false;
                    System.out.println("You should enter numbers!");
                    continue;
                }
            }

            if (currentPlayer % 2 == 0) {
                matrix[2 - y][x] = 'X';
            } else {
                matrix[2 - y][x] = 'O';
            }
            currentPlayer++;
            printTable(matrix);
            getResult(matrix);
        }
    }
}
