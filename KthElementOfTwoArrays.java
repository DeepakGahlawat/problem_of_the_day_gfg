/*
    ============================================================
                K-TH ELEMENT OF TWO SORTED ARRAYS
    ============================================================

    🧠 INTUITION
    ------------------------------------------------------------
    We are given two SORTED arrays a[] and b[].
    If we merge them, we need the element that appears at
    the k-th position (1-based indexing).

    Merging fully would take O(n1 + n2), which is too slow
    for large inputs.

    ------------------------------------------------------------
    🧩 KEY OBSERVATION
    ------------------------------------------------------------
    We don’t need the full merged array.
    We only need to know how many elements are taken from each
    array before the k-th position.

    This can be solved using a PARTITION-BASED BINARY SEARCH,
    similar to the "Median of Two Sorted Arrays" problem.

    ------------------------------------------------------------
    🧮 APPROACH (Binary Search on Smaller Array)
    ------------------------------------------------------------
    1️⃣ Always apply binary search on the smaller array
    2️⃣ Choose cut1 elements from array a[]
    3️⃣ Then cut2 = k - cut1 elements come from array b[]
    4️⃣ Validate partition:
         max(leftA, leftB) ≤ min(rightA, rightB)
    5️⃣ If valid → answer is max(leftA, leftB)
    6️⃣ Else adjust binary search boundaries

    ------------------------------------------------------------
    ⏱️ TIME & SPACE COMPLEXITY
    ------------------------------------------------------------
    Time Complexity:
        O(log(min(n1, n2)))

    Space Complexity:
        O(1)

    ------------------------------------------------------------
    ✔ Optimal solution for very large arrays
      Classic FAANG / GFG interview problem
    ============================================================
*/

import java.util.*;

public class KthElementOfTwoArrays {

    public int kthElement(int[] a, int[] b, int k) {

        int n1 = a.length;
        int n2 = b.length;

        // Always binary search on the smaller array
        if (n1 > n2) return kthElement(b, a, k);

        // Search boundaries
        int low = Math.max(0, k - n2);
        int high = Math.min(k, n1);

        while (low <= high) {

            int cut1 = low + (high - low) / 2;
            int cut2 = k - cut1;

            // Left and right boundary elements
            int l1 = (cut1 == 0) ? Integer.MIN_VALUE : a[cut1 - 1];
            int l2 = (cut2 == 0) ? Integer.MIN_VALUE : b[cut2 - 1];

            int r1 = (cut1 == n1) ? Integer.MAX_VALUE : a[cut1];
            int r2 = (cut2 == n2) ? Integer.MAX_VALUE : b[cut2];

            // Valid partition found
            if (l1 <= r2 && l2 <= r1) {
                return Math.max(l1, l2);
            }
            // Too many elements taken from a[]
            else if (l1 > r2) {
                high = cut1 - 1;
            }
            // Too few elements taken from a[]
            else {
                low = cut1 + 1;
            }
        }

        return -1; // Should never reach here for valid input
    }

    // ============================================================
    //                         MAIN METHOD
    // ============================================================
    public static void main(String[] args) {

        KthElementOfTwoArrays solver =
                new KthElementOfTwoArrays();

        int[] a1 = {2, 3, 6, 7, 9};
        int[] b1 = {1, 4, 8, 10};
        int k1 = 5;

        System.out.println(
                "Kth element = " +
                        solver.kthElement(a1, b1, k1)
        ); // Output: 6

        int[] a2 = {1, 4, 8, 10, 12};
        int[] b2 = {5, 7, 11, 15, 17};
        int k2 = 6;

        System.out.println(
                "Kth element = " +
                        solver.kthElement(a2, b2, k2)
        ); // Output: 10
    }
}
