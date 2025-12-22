/*
    ============================================================
                        ROW WITH MAX 1s
    ============================================================

    🧠 INTUITION
    ------------------------------------------------------------
    Each row of the matrix is SORTED (0s followed by 1s).
    The number of 1s in a row depends on the position of
    the FIRST occurrence of 1.

    Earlier the first 1 appears → more number of 1s in that row.

    So, for each row:
        - Find the index of first 1 using Binary Search
        - Keep track of the minimum such index

    ------------------------------------------------------------
    🧩 KEY OBSERVATION
    ------------------------------------------------------------
    If a row has first 1 at index `idx`,
    then number of 1s = (number of columns - idx)

    We only need to compare `idx` values.
    Smaller idx → more 1s.

    ------------------------------------------------------------
    🧮 APPROACH
    ------------------------------------------------------------
    1️⃣ Initialize:
         - minIndex = +∞
         - answerRow = -1
    2️⃣ For each row:
         - Use lowerBound to find first occurrence of 1
         - If index < minIndex, update result
    3️⃣ Return the row index with maximum 1s

    ------------------------------------------------------------
    ⏱️ TIME & SPACE COMPLEXITY
    ------------------------------------------------------------
    Time Complexity:
        O(R × log C)
        where R = number of rows
              C = number of columns

    Space Complexity:
        O(1)

    ------------------------------------------------------------
    ✔ Efficient solution using Binary Search
      Common GFG / Interview problem
    ============================================================
*/

import java.util.*;

public class RowWithMax1s {

    public int rowWithMax1s(int[][] arr) {

        int minIndex = Integer.MAX_VALUE;
        int maxRow = -1;
        int cols = arr[0].length;

        // Traverse each row
        for (int i = 0; i < arr.length; i++) {

            // Find index of first 1 using binary search
            int idx = lowerBound(arr[i], 1);

            // Check if this row has more 1s
            if (idx < cols && idx < minIndex) {
                minIndex = idx;
                maxRow = i;
            }
        }

        return maxRow;
    }

    // Binary search to find first index where value >= x
    int lowerBound(int[] a, int x) {

        int l = 0;
        int h = a.length - 1;

        while (l <= h) {
            int m = l + (h - l) / 2;

            if (a[m] >= x) {
                h = m - 1;
            } else {
                l = m + 1;
            }
        }

        return l;
    }

    // ============================================================
    //                         MAIN METHOD
    // ============================================================
    public static void main(String[] args) {

        RowWithMax1s solver = new RowWithMax1s();

        int[][] matrix = {
                {0, 0, 0, 1},
                {0, 1, 1, 1},
                {0, 0, 1, 1},
                {0, 0, 0, 0}
        };

        System.out.println(
                "Row with maximum 1s = " +
                        solver.rowWithMax1s(matrix)
        );
    }
}
