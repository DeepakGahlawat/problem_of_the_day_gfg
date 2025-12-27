/*
    ============================================================
            KTH SMALLEST ELEMENT IN A MATRIX
    ============================================================

    🧠 INTUITION
    ------------------------------------------------------------
    We are given an n x n matrix where:
        - Each ROW is sorted in non-decreasing order
        - Each COLUMN is sorted in non-decreasing order

    We need to find the k-th smallest element in the matrix.

    ------------------------------------------------------------
    🧩 KEY OBSERVATION
    ------------------------------------------------------------
    Instead of flattening the matrix (O(n^2 log n)),
    we can apply BINARY SEARCH on the VALUE RANGE.

    Why does this work?
        - If we guess a value `mid`
        - We can COUNT how many elements in the matrix
          are <= mid in O(n) time using matrix properties
        - The count is monotonic with respect to `mid`

    ------------------------------------------------------------
    🧮 APPROACH (Binary Search on Answer)
    ------------------------------------------------------------
    1️⃣ Set low = smallest element, high = largest element
    2️⃣ While low <= high:
         - mid = candidate value
         - count how many elements <= mid
         - If count < k → need larger values
         - Else → mid could be answer, move left
    3️⃣ Return the smallest value whose count >= k

    ------------------------------------------------------------
    🧮 COUNTING ELEMENTS ≤ X
    ------------------------------------------------------------
    Start from top-right corner:
        - If mat[row][col] <= x:
              → all elements to the left are <= x
              → add (col + 1), move down
        - Else:
              → move left

    This works in O(n) time.

    ------------------------------------------------------------
    ⏱️ TIME & SPACE COMPLEXITY
    ------------------------------------------------------------
    Time Complexity:
        O(n log (maxValue - minValue))

    Space Complexity:
        O(1)

    ------------------------------------------------------------
    ✔ Optimal solution using binary search on value
      Very common FAANG / GFG matrix problem
    ============================================================
*/

import java.util.*;

public class KthSmallestElementInAMatrix {

    public int kthSmallest(int[][] mat, int k) {

        int n = mat.length;

        int low = mat[0][0];
        int high = mat[n - 1][n - 1];
        int ans = high;

        // Binary search on value range
        while (low <= high) {

            int mid = low + (high - low) / 2;

            // Count elements <= mid
            int count = countSmaller(mat, mid);

            if (count < k) {
                low = mid + 1;   // need bigger values
            } else {
                ans = mid;       // potential answer
                high = mid - 1;
            }
        }

        return ans;
    }

    // Counts how many elements in matrix are <= x
    int countSmaller(int[][] mat, int x) {

        int n = mat.length;
        int row = 0;
        int col = n - 1;
        int count = 0;

        // Traverse from top-right corner
        while (row < n && col >= 0) {

            if (mat[row][col] <= x) {
                // All elements to the left are <= x
                count += (col + 1);
                row++;
            } else {
                col--;
            }
        }

        return count;
    }

    // ============================================================
    //                         MAIN METHOD
    // ============================================================
    public static void main(String[] args) {

        KthSmallestElementInAMatrix solver =
                new KthSmallestElementInAMatrix();

        int[][] mat1 = {
                {16, 28, 60, 64},
                {22, 41, 63, 91},
                {27, 50, 87, 93},
                {36, 78, 87, 94}
        };
        int k1 = 3;
        System.out.println(
                "Kth smallest = " +
                        solver.kthSmallest(mat1, k1)
        ); // 27

        int[][] mat2 = {
                {10, 20, 30, 40},
                {15, 25, 35, 45},
                {24, 29, 37, 48},
                {32, 33, 39, 50}
        };
        int k2 = 7;
        System.out.println(
                "Kth smallest = " +
                        solver.kthSmallest(mat2, k2)
        ); // 30
    }
}
