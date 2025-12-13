/*
    ============================================================
                        SWAP DIAGONALS
    ============================================================

    🧠 PROBLEM INTUITION
    ------------------------------------------------------------
    In a square matrix of size n x n:

    1️⃣ Major Diagonal:
        Elements where row index == column index
        → mat[0][0], mat[1][1], mat[2][2], ...

    2️⃣ Minor Diagonal:
        Elements where row index + column index == n - 1
        → mat[0][n-1], mat[1][n-2], mat[2][n-3], ...

    👉 The task is to swap the diagonal elements of the SAME ROW.
       That means:
           mat[i][i] ↔ mat[i][n - 1 - i]

    ------------------------------------------------------------
    🧩 APPROACH
    ------------------------------------------------------------
    - Maintain two pointers:
        lc → left column (major diagonal index)
        rc → right column (minor diagonal index)

    - Traverse each row:
        • Swap mat[row][lc] with mat[row][rc]
        • Move lc forward and rc backward

    ------------------------------------------------------------
    🕒 TIME & SPACE COMPLEXITY
    ------------------------------------------------------------
    Time Complexity  : O(n)
    Space Complexity : O(1) (in-place swapping)

    ------------------------------------------------------------
    ✔ This approach works efficiently for all constraints.
    ============================================================
*/

import java.util.*;

public class Swapdiagonals {

    // Method to swap major and minor diagonals
    public void swapDiagonal(int[][] mat) {

        int n = mat.length;

        int lc = 0;        // left column (major diagonal)
        int rc = n - 1;    // right column (minor diagonal)

        // Traverse each row and swap diagonal elements
        for (int i = 0; i < n; i++) {

            // Swap elements of same row
            int temp = mat[i][lc];
            mat[i][lc] = mat[i][rc];
            mat[i][rc] = temp;

            // Move pointers inward
            lc++;
            rc--;
        }
    }

    // ============================================================
    //                         MAIN METHOD
    // ============================================================
    public static void main(String[] args) {

        Swapdiagonals obj = new Swapdiagonals();

        int[][] mat = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8}
        };

        System.out.println("Original Matrix:");
        printMatrix(mat);

        obj.swapDiagonal(mat);

        System.out.println("\nMatrix After Swapping Diagonals:");
        printMatrix(mat);
    }

    // Helper method to print matrix
    private static void printMatrix(int[][] mat) {
        for (int[] row : mat) {
            System.out.println(Arrays.toString(row));
        }
    }
}
