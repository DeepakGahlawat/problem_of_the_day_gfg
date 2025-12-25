/*
    ============================================================
            FIND THE PEAK ELEMENT IN A 2D MATRIX
    ============================================================

    🧠 INTUITION
    ------------------------------------------------------------
    A peak element in a 2D matrix is an element that is:
        ≥ top, bottom, left, and right neighbors.

    Edge and corner cells treat missing neighbors as -∞.

    There can be multiple peak elements.
    We only need to find ANY ONE valid peak.

    ------------------------------------------------------------
    🧩 KEY OBSERVATION
    ------------------------------------------------------------
    Instead of checking every cell (O(n*m)),
    we can use Binary Search on COLUMNS.

    For a chosen column:
        - Find the row having the maximum element in that column.
        - Compare it with its left and right neighbors.
        - Decide the direction of search.

    This reduces the problem to:
        👉 O(n log m)

    ------------------------------------------------------------
    🧮 APPROACH (Binary Search on Columns)
    ------------------------------------------------------------
    1️⃣ Perform binary search on columns
    2️⃣ For mid column:
         - Find the row index of the maximum element
         - Compare with left and right neighbors
    3️⃣ If it satisfies peak condition → return it
    4️⃣ Else move towards the larger neighbor

    ------------------------------------------------------------
    ⏱️ TIME & SPACE COMPLEXITY
    ------------------------------------------------------------
    Time Complexity:
        O(n log m)
        where n = rows, m = columns

    Space Complexity:
        O(1)

    ------------------------------------------------------------
    ✔ Optimal solution for large matrices
      Very common FAANG / GFG problem
    ============================================================
*/

import java.util.*;

public class FindThePeakElementInA2DMatrix {

    public ArrayList<Integer> findPeakGrid(int[][] mat) {

        int n = mat.length;
        int m = mat[0].length;

        int low = 0, high = m - 1;

        // Binary search on columns
        while (low <= high) {

            int mid = low + (high - low) / 2;

            // Find row index of maximum element in mid column
            int rowIndex = findMaxIndInCol(mat, mid);

            int left  = (mid == 0)     ? Integer.MIN_VALUE : mat[rowIndex][mid - 1];
            int right = (mid == m - 1) ? Integer.MIN_VALUE : mat[rowIndex][mid + 1];

            // Check peak condition
            if (mat[rowIndex][mid] >= left && mat[rowIndex][mid] >= right) {
                return new ArrayList<>(Arrays.asList(rowIndex, mid));
            }
            // Move left
            else if (left > mat[rowIndex][mid]) {
                high = mid - 1;
            }
            // Move right
            else {
                low = mid + 1;
            }
        }

        return new ArrayList<>();
    }

    // Finds index of maximum element in a given column
    int findMaxIndInCol(int[][] mat, int col) {

        int maxVal = Integer.MIN_VALUE;
        int index = -1;

        for (int i = 0; i < mat.length; i++) {
            if (mat[i][col] > maxVal) {
                maxVal = mat[i][col];
                index = i;
            }
        }
        return index;
    }

    // ============================================================
    //                         MAIN METHOD
    // ============================================================
    public static void main(String[] args) {

        FindThePeakElementInA2DMatrix solver =
                new FindThePeakElementInA2DMatrix();

        int[][] mat = {
                {10, 20, 15},
                {21, 30, 14},
                {7, 16, 32}
        };

        System.out.println(
                "Peak position = " +
                        solver.findPeakGrid(mat)
        );
    }
}
