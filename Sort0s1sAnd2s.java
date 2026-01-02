/*
    ============================================================
                SORT 0s, 1s AND 2s
    ============================================================

    🧠 INTUITION
    ------------------------------------------------------------
    The array contains only three distinct values:
        0, 1, and 2

    We need to sort the array in ascending order WITHOUT using
    any built-in sorting function and ideally in ONE PASS
    using constant extra space.

    ------------------------------------------------------------
    🧩 KEY IDEA (Dutch National Flag Algorithm)
    ------------------------------------------------------------
    Maintain three pointers:
        low   → boundary for 0s
        mid   → current element under inspection
        high  → boundary for 2s

    Invariants maintained during traversal:
        - arr[0 ... low-1]     → all 0s
        - arr[low ... mid-1]   → all 1s
        - arr[mid ... high]    → unknown
        - arr[high+1 ... n-1]  → all 2s

    ------------------------------------------------------------
    🧮 APPROACH
    ------------------------------------------------------------
    1️⃣ Initialize low = 0, mid = 0, high = n - 1
    2️⃣ While mid <= high:
         - If arr[mid] == 0 → swap with low, increment both
         - If arr[mid] == 1 → just move mid
         - If arr[mid] == 2 → swap with high, decrement high
    3️⃣ Continue until all elements are processed

    ------------------------------------------------------------
    ⏱️ TIME & SPACE COMPLEXITY
    ------------------------------------------------------------
    Time Complexity:
        O(n)   (single pass)

    Space Complexity:
        O(1)   (constant extra space)

    ------------------------------------------------------------
    ✔ Classic one-pass algorithm
      Very common interview problem
    ============================================================
*/

import java.util.*;

public class Sort0s1sAnd2s {

    public void sort012(int[] arr) {

        int n = arr.length;

        int low = 0;      // boundary for 0s
        int mid = 0;      // current index
        int high = n - 1; // boundary for 2s

        // Process elements until mid crosses high
        while (mid <= high) {

            if (arr[mid] == 0) {
                // Place 0 in correct region
                swap(mid, low, arr);
                mid++;
                low++;
            }
            else if (arr[mid] == 1) {
                // 1 is already in correct region
                mid++;
            }
            else { // arr[mid] == 2
                // Place 2 in correct region
                swap(mid, high, arr);
                high--;
            }
        }
    }

    // Utility function to swap elements
    void swap(int a, int b, int[] arr) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    // ============================================================
    //                         MAIN METHOD
    // ============================================================
    public static void main(String[] args) {

        Sort0s1sAnd2s solver = new Sort0s1sAnd2s();

        int[] arr1 = {0, 1, 2, 0, 1, 2};
        solver.sort012(arr1);
        System.out.println(Arrays.toString(arr1));
        // Output: [0, 0, 1, 1, 2, 2]

        int[] arr2 = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
        solver.sort012(arr2);
        System.out.println(Arrays.toString(arr2));
        // Output: [0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2]
    }
}
