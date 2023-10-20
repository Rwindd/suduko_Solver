public class SudokuSolver {

    private static final int gridsize = 9;

    public static void main(String[] args) {
        int[][] board = {
            {7, 0, 2, 0, 5, 0, 6, 0, 0},
            {0, 0, 0, 0, 0, 3, 0, 0, 0},
            {1, 0, 0, 0, 0, 9, 5, 0, 0},
            {8, 0, 0, 0, 0, 0, 0, 9, 0},
            {0, 4, 3, 0, 0, 0, 7, 5, 0},
            {0, 9, 0, 0, 0, 0, 0, 0, 8},
            {0, 0, 9, 7, 0, 0, 0, 0, 8},
            {0, 0, 0, 2, 0, 0, 0, 0, 0},
            {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };
        if (solveBoard(board)) {
            System.out.println("Solved successfully");
        } else {
            System.out.println("Unsolved");
        }
        printBoard(board);
    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < gridsize; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int column = 0; column < gridsize; column++) {
                if (column % 3 == 0 && column != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

    // Check if the number is present in the row
    public static boolean isNumRow(int[][] board, int number, int row) {
        for (int i = 0; i < gridsize; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    // Check if the number is present in the column
    public static boolean isNumColumn(int[][] board, int number, int column) {
        for (int i = 0; i < gridsize; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    // Check if the number is present anywhere else in the box 3x3
    public static boolean isNumInBox(int[][] board, int number, int row, int column) {
        int lowBoxRow = row - row % 3;
        int lowBoxColumn = column - column % 3;
        for (int i = lowBoxRow; i < lowBoxRow + 3; i++) {
            for (int j = lowBoxColumn; j < lowBoxColumn + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    // Check all three conditions above
    private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return !isNumColumn(board, number, column) && !isNumRow(board, number, row) && !isNumInBox(board, number, row, column);
    }

    // Recursion and backtracking
    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < gridsize; row++) {
            for (int column = 0; column < gridsize; column++) {
                if (board[row][column] == 0) {
                    for (int numberToTry = 1; numberToTry <= gridsize; numberToTry++) {
                        if (isValidPlacement(board, numberToTry, row, column)) {
                            board[row][column] = numberToTry;
                            if (solveBoard(board)) {
                                return true;
                            } else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
