/*
    ============================================================
        COUNT X IN RANGE OF A SORTED ARRAY
    ============================================================

    🧠 INTUITION
    ------------------------------------------------------------
    We are given:
        - A SORTED array `arr`
        - Multiple queries of form [L, R, X]

    For each query, we need to count how many times
    value X appears in the subarray arr[L..R].

    Since the array is already sorted, binary search
    is the most efficient approach.

    ------------------------------------------------------------
    🧩 KEY OBSERVATION
    ------------------------------------------------------------
    Count of X in range [L, R] =
        upperBound(X) - lowerBound(X)

    Where:
        - lowerBound → first index ≥ X
        - upperBound → first index > X

    ------------------------------------------------------------
    🧮 APPROACH (BINARY SEARCH)
    ------------------------------------------------------------
    For each query:
        1️⃣ Find lower bound of X in range [L, R]
        2️⃣ Find upper bound of X in range [L, R]
        3️⃣ Answer = upperBound - lowerBound

    ------------------------------------------------------------
    ⏱️ TIME & SPACE COMPLEXITY
    ------------------------------------------------------------
    Time Complexity:
        O(Q × log N)
        where Q = number of queries

    Space Complexity:
        O(1) extra (excluding output list)

    ------------------------------------------------------------
    ✔ Efficient solution using binary search
      Very common GFG / interview problem
    ============================================================
*/

import java.util.*;

public class CountXInRangeOfSortedArray {

    public ArrayList<Integer> countXInRange(int[] arr, int[][] queries) {

        ArrayList<Integer> result = new ArrayList<>();

        // Process each query
        for (int[] query : queries) {

            int l = query[0];
            int r = query[1];
            int x = query[2];

            // Find lower and upper bounds of x in [l, r]
            int start = lowerBound(arr, l, r, x);
            int end = upperBound(arr, l, r, x);

            // Number of occurrences
            result.add(end - start);
        }

        return result;
    }

    // Finds first index >= x in range [l, h]
    int lowerBound(int[] arr, int l, int h, int x) {
        while (l <= h) {
            int mid = l + (h - l) / 2;

            if (arr[mid] >= x) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    // Finds first index > x in range [l, h]
    int upperBound(int[] arr, int l, int h, int x) {
        while (l <= h) {
            int mid = l + (h - l) / 2;

            if (arr[mid] <= x) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }
        return l;
    }

    // ============================================================
    //                         MAIN METHOD
    // ============================================================
    public static void main(String[] args) {

        CountXInRangeOfSortedArray solver =
                new CountXInRangeOfSortedArray();

        int[] arr = {1, 2, 2, 2, 3, 4, 5};
        int[][] queries = {
                {0, 6, 2},
                {1, 4, 3},
                {2, 5, 2}
        };

        System.out.println(
                "Occurrences of X in each range = " +
                        solver.countXInRange(arr, queries)
        );
    }
}
