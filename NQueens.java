public class NQueens {

    // Function to solve N-Queens problem
    public static void solveNQueens(int n) {
        int[] board = new int[n];
        placeQueens(board, 0, n);
    }

    // Helper function to place queens recursively
    private static void placeQueens(int[] board, int row, int n) {
        if (row == n) {
            // All queens are successfully placed, print the board
            printBoard(board);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {
                // Place queen at this position
                board[row] = col;
                // Recur for the next row
                placeQueens(board, row + 1, n);
            }
        }
    }

    // Function to check if it's safe to place a queen at a particular position
    private static boolean isSafe(int[] board, int row, int col) {
        // Check for queens in the same column
        for (int i = 0; i < row; i++) {
            if (board[i] == col)
                return false;
            // Check for queens in diagonals
            if (Math.abs(row - i) == Math.abs(col - board[i]))
                return false;
        }
        return true;
    }

    // Function to print the chessboard with queens placed
    private static void printBoard(int[] board) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i] == j)
                    System.out.print("Q ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 4; // Size of the chessboard
        solveNQueens(n);
    }
}
