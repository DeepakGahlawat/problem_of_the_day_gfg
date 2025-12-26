/*
    ============================================================
        KTH MISSING POSITIVE NUMBER IN A SORTED ARRAY
    ============================================================

    🧠 INTUITION
    ------------------------------------------------------------
    We are given a SORTED array of DISTINCT positive integers.
    Some positive numbers are missing from this array.

    We need to find the K-th missing positive number.

    ------------------------------------------------------------
    🧩 KEY OBSERVATION
    ------------------------------------------------------------
    For any index `i` (0-based):
        - Expected value at index i = i + 1
        - Actual value = arr[i]

    Number of missing positives BEFORE index i:
        missing(i) = arr[i] - (i + 1)

    This value is MONOTONICALLY increasing, so we can apply
    Binary Search.

    ------------------------------------------------------------
    🧮 APPROACH (BINARY SEARCH)
    ------------------------------------------------------------
    1️⃣ Perform binary search on indices
    2️⃣ If missing(mid) < k:
         → move right
    3️⃣ Else:
         → move left
    4️⃣ After search ends, `low` gives the position where
       exactly `k` missing numbers should appear

    Final Answer:
        low + k

    ------------------------------------------------------------
    ⏱️ TIME & SPACE COMPLEXITY
    ------------------------------------------------------------
    Time Complexity:
        O(log n)

    Space Complexity:
        O(1)

    ------------------------------------------------------------
    ✔ Optimal solution using binary search
      Very common interview problem
    ============================================================
*/

import java.util.*;

public class KthMissingPositiveNumberInSortedArray {

    public int kthMissing(int[] arr, int k) {

        int low = 0;
        int high = arr.length - 1;

        // Binary search on indices
        while (low <= high) {

            int mid = low + (high - low) / 2;

            // Count of missing numbers before index mid
            int missing = arr[mid] - (mid + 1);

            if (missing < k) {
                low = mid + 1;   // need more missing numbers
            } else {
                high = mid - 1;
            }
        }

        // low is the position where k-th missing lies
        return low + k;
    }

    // ============================================================
    //                         MAIN METHOD
    // ============================================================
    public static void main(String[] args) {

        KthMissingPositiveNumberInSortedArray solver =
                new KthMissingPositiveNumberInSortedArray();

        int[] arr1 = {2, 3, 4, 7, 11};
        int k1 = 5;
        System.out.println(
                "Kth missing = " + solver.kthMissing(arr1, k1)
        ); // 9

        int[] arr2 = {1, 2, 3};
        int k2 = 2;
        System.out.println(
                "Kth missing = " + solver.kthMissing(arr2, k2)
        ); // 5

        int[] arr3 = {3, 5, 9, 10, 11, 12};
        int k3 = 2;
        System.out.println(
                "Kth missing = " + solver.kthMissing(arr3, k3)
        ); // 2
    }
}
